package cn.stylefeng.guns.modular.sbdsys.controller;


import cn.stylefeng.guns.modular.sbdsys.model.params.AccountParam;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ResponseData;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 统计
 *
 * @author Admin
 * @Date 2019-09-06 10:19:01
 */
@Controller
@RequestMapping("/census")
public class CensusController extends BaseController {

    private String PREFIX = "/census";

    /**
     * 活动统计报表
     *
     * @author Admin
     * @Date 2019-09-06
     */
    @RequestMapping("/acitvity")
    public String activity(){
        return PREFIX + "/activity.html";
    }


    /**
     * 党支部 排行与统计
     *
     * @author Admin
     * @Date 2019-09-06
     */
    @RequestMapping("/branch")
    public String branch(){
        return PREFIX + "/branch.html";
    }

    /**
     * 心愿统计报表
     *
     * @author Admin
     * @Date 2019-09-06
     */
    @RequestMapping("/wish")
    public String wish(){
        return PREFIX + "/wish.html";
    }

    /**
     * 活动统计报表 数据
     *
     * @author Admin
     * @Date 2019-09-06
     */
    @RequestMapping("/acitvity_data")
    @ResponseBody
    public ResponseData addItem(AccountParam accountParam) {
        return ResponseData.success();
    }

}
