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
import java.util.HashMap;

@Controller
@RequestMapping("/qrCode")
public class QrCodeController extends BaseController {
    static HashMap<String, Long> qrCodeMap = new HashMap<>();

    @ResponseBody
    @RequestMapping("/add")
    public ResponseData add(String type, String id) throws InterruptedException {
        //缓存中没有数据，将id放入
        qrCodeMap.put(id, System.currentTimeMillis());
        //循环检测是否二维码被扫描
        int second = 60;
        for(int i = 0 ; i < second;i++){
            Thread.sleep(1000);
            Long lastTime = qrCodeMap.get(id);
            if(lastTime== null ){
                //扫描完成，返回1,提前退出
                return ResponseData.success(true);
            }
        }
        //未扫描，也将id删除
        Long l = qrCodeMap.get(id);
        if((System.currentTimeMillis()-l) >=second*1000){
            qrCodeMap.remove(id);
        }else{
            System.out.println("在一个周期内被打开多次，本次不删除");
        }
        return ResponseData.success(false);
    }

    @ResponseBody
    @RequestMapping("/delete")
    public ResponseData delete(String type, String id) throws InterruptedException {
        Long lastTime = qrCodeMap.get(id);
        if(lastTime != null ){
            //删除id缓存
            qrCodeMap.remove(id);
        }
        //扫码完成，删除二维码缓存
        return ResponseData.success();
    }
}
