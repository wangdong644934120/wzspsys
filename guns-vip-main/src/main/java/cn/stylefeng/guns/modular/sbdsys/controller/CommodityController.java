package cn.stylefeng.guns.modular.sbdsys.controller;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.sbdsys.entity.Commodity;
import cn.stylefeng.guns.modular.sbdsys.model.params.CommodityParam;
import cn.stylefeng.guns.modular.sbdsys.model.result.CommodityResult;
import cn.stylefeng.guns.modular.sbdsys.service.CommodityService;
import cn.stylefeng.guns.sys.core.shiro.ShiroKit;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ResponseData;
import cn.stylefeng.roses.core.util.HttpContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;


/**
 * 商品信息控制器
 *
 * @author Admin
 * @Date 2019-09-06 10:19:01
 */
@Controller
@RequestMapping("/commodity")
public class CommodityController extends BaseController {

    private String PREFIX = "//commodity";

    @Autowired
    private CommodityService commodityService;

    /**
     * 跳转到主页面
     *
     * @author Admin
     * @Date 2019-09-06
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/commodity.html";
    }

    /**
     * 新增页面
     *
     * @author Admin
     * @Date 2019-09-06
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/commodity_add.html";
    }

    /**
     * 编辑页面
     *
     * @author Admin
     * @Date 2019-09-06
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/commodity_edit.html";
    }

    @RequestMapping("/myCommodity2")
    public String myCommodity2() {
        return "/demo/myCommodity2.html";
    }
    @RequestMapping("/myCoinRecord")
    public String myCoinRecord() {
        return "/demo/myCoinRecord.html";
    }
    @RequestMapping("/myCommodity")
    public String myCommodity() {
        return "/demo/myCommodity.html";
    }

    /**
     * 新增接口
     *
     * @author Admin
     * @Date 2019-09-06
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(CommodityParam commodityParam) {
        commodityParam.setCreateTime(new Date());
        commodityParam.setEnable(1);
        commodityParam.setSurplus(commodityParam.getTotal());
        this.commodityService.add(commodityParam);
        return ResponseData.success();
    }

    /**
     * 编辑接口
     *
     * @author Admin
     * @Date 2019-09-06
     */
    @RequestMapping("/editItem")
    @ResponseBody
    public ResponseData editItem(CommodityParam commodityParam) {
        this.commodityService.update(commodityParam);
        return ResponseData.success();
    }

    /**
     * 删除接口
     *
     * @author Admin
     * @Date 2019-09-06
     */
    @RequestMapping("/delete")
    @ResponseBody
    public ResponseData delete(CommodityParam commodityParam) {
        //this.commodityService.delete(commodityParam);
        commodityParam.setEnable(-1);
        commodityParam.setUpdateTime(new Date());
        this.commodityService.update(commodityParam);
        return ResponseData.success();
    }

    /**
     * 查看详情接口
     *
     * @author Admin
     * @Date 2019-09-06
     */
    @RequestMapping("/detail")
    @ResponseBody
    public ResponseData detail(CommodityParam commodityParam) {
       // Commodity detail = this.commodityService.getById(commodityParam.getId());
        CommodityResult detail = this.commodityService.getResult(commodityParam);
        return ResponseData.success(detail);
    }

    /**
     * 查询列表
     *
     * @author Admin
     * @Date 2019-09-06
     */
    @ResponseBody
    @RequestMapping("/list")
    public LayuiPageInfo list(CommodityParam commodityParam) {
        return this.commodityService.findPageBySpec(commodityParam);
    }

    /**
     * 查询列表
     *
     * @author Admin
     * @Date 2019-09-06
     */
    @ResponseBody
    @RequestMapping("/list4Phone")
    public LayuiPageInfo list4Phone(CommodityParam commodityParam) {
        HttpServletRequest request = HttpContext.getRequest();
        String showAll = request.getParameter("showAll");
        if (showAll.equals("true")) {
            //显示所有商品
        }else {
            //显示登录人商品
            String  storeId = ShiroKit.getUser().getId().toString();
            commodityParam.setStoreUser(storeId);
        }
        return this.commodityService.findPageBySpec4Phone(commodityParam);
    }
    /**
     * @Description 查询党员手机端可见的商品
     * @Author 于鹏
     * @Date 2019/9/10 14:44
     **/
    @ResponseBody
    @RequestMapping("/list4partMember")
    public LayuiPageInfo list4partMember(CommodityParam commodityParam) {
        HttpServletRequest request = HttpContext.getRequest();
        String showAll = request.getParameter("showAll");
        if(showAll == null){
            commodityParam.setShowAll("false");
        }else if(showAll.equals("true")) {
            //显示所有商品
            commodityParam.setShowAll(showAll);
        }
        return this.commodityService.findPageBySpec4PartMember(commodityParam);
    }

}


