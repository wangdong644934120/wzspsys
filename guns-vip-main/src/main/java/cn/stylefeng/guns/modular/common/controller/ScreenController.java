package cn.stylefeng.guns.modular.common.controller;
import cn.hutool.core.io.FileUtil;
import cn.stylefeng.guns.sys.core.properties.GunsProperties;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;

@Controller
@RequestMapping("/screen")
public class ScreenController extends BaseController {

    @Autowired
    private GunsProperties gunsProperties;

    @RequestMapping("/save")
    @ResponseBody
    public ResponseData save(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String dataTxt = request.getParameter("dataTxt");
        String name = request.getParameter("name");
        if (dataTxt != null && !dataTxt.isEmpty()) {
            try {
                String path=gunsProperties.getFileUploadPath()+"/uidata/"+name;
                FileUtil.writeString(dataTxt,path,"UTF-8");
                return ResponseData.success();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return ResponseData.error("格式错误");
    }

    @RequestMapping("/read")
    @ResponseBody
    public String read(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String path=gunsProperties.getFileUploadPath()+"/uidata/"+request.getParameter("name");
        String txt=FileUtil.readString(path,"UTF-8");
        return txt;
    }
}
