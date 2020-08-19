package cn.stylefeng.guns.modular.common.controller;

import cn.stylefeng.guns.modular.common.service.WechatTokenService;
import cn.stylefeng.roses.core.reqres.response.ResponseData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;

/**
 * @Description: TODO
 * @Duthor: lichenfeng
 * @Date: 2019/9/12 16:37
 * @Version 1.0
 */
@Controller
@RequestMapping("/wechat")
public class WechatController {

    @Resource
    private WechatTokenService wechatTokenService;

    @RequestMapping("")
    public String index(Model model) {
        model.addAttribute("title", "您的操作有误");
        return "/scanTip/scanTip.html";
    }

    /**
     * @Description 获取jssdk confing
     * @Author lichenfeng
     * @Date 2019/9/12 16:50
     **/
    @RequestMapping("/jssdkConfig")
    @ResponseBody
    public ResponseData jssdkConfig(@RequestParam("wholeUrl")String wholeUrl) {
        return ResponseData.success(wechatTokenService.getJSSDKConf(wholeUrl,true));
    }
}
