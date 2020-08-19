package cn.stylefeng.guns.modular.common.controller;

import cn.stylefeng.roses.core.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/ref")
public class StaticController  extends BaseController {

    private String PREFIX = "/ref";

    @RequestMapping("/**")
    public String router(HttpServletRequest request, HttpServletResponse response) {
        String path = request.getRequestURI().substring(request.getRequestURI().indexOf(PREFIX) + PREFIX.length());
        return path;
    }
}
