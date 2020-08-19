package cn.stylefeng.guns.modular.common.controller;

import cn.stylefeng.guns.sys.core.properties.GunsProperties;
import cn.stylefeng.roses.core.base.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLDecoder;
import java.net.URLEncoder;

@Controller
@RequestMapping("/file")
public class FileController extends BaseController {

    private String PREFIX = "/file";

    @Autowired
    private GunsProperties gunsProperties;

    @RequestMapping("/**")
    public void file(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String uri = request.getRequestURI();
        uri= uri.split(",")[0];
        String path = uri.substring(uri.indexOf(PREFIX) + PREFIX.length());
        path= URLDecoder.decode(path, "UTF-8");
        File file = new File(gunsProperties.getFileUploadPath() + path);
        if (file.exists()&&!file.isDirectory()) {
            if(file.getName().toLowerCase().endsWith(".svg")){
                response.setContentType("image/svg+xml");
            }else if(file.getName().toLowerCase().endsWith(".json")){
                response.setContentType("application/json");
            }else{
                response.setContentType("application/x-download");//设置为下载application/x-download
            }
            try {
                response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(URLDecoder.decode(file.getName(), "UTF-8"), "UTF-8"));
            } catch (UnsupportedEncodingException ex) {
                ex.printStackTrace();
            }
            FileInputStream fis = null;
            OutputStream os = null;
            try {
                fis = new FileInputStream(file);
                os = response.getOutputStream();
                byte[] buffer = new byte[1024 * 4];
                int count = 0;
                while ((count = fis.read(buffer)) > 0) {
                    os.write(buffer, 0, count);
                }
                os.flush();
            } catch (IOException ex) {
                throw new Exception("访问的文件不存在");
            } finally {
                try {
                    fis.close();
                    os.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
