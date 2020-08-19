package cn.stylefeng.guns.modular.test.controller;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.test.entity.Order;
import cn.stylefeng.guns.modular.test.model.params.OrderParam;
import cn.stylefeng.guns.modular.test.service.OrderService;
import cn.stylefeng.guns.sys.core.util.CacheUtil;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 订单表控制器
 *
 * @author stylefeng
 * @Date 2019-11-29 19:09:44
 */
@Controller
@RequestMapping("/order")
public class OrderController extends BaseController {

    private String PREFIX = "//order";

    @Autowired
    private OrderService orderService;

    /**
     * 跳转到主页面
     *
     * @author stylefeng
     * @Date 2019-11-29
     */
    @RequestMapping("")
    public String index()
    {
        return PREFIX + "/order.html";
    }

    /**
     * 新增页面
     *
     * @author stylefeng
     * @Date 2019-11-29
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/order_add.html";
    }

    /**
     * 编辑页面
     *
     * @author stylefeng
     * @Date 2019-11-29
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/order_edit.html";
    }

    /**
     * 新增接口
     *
     * @author stylefeng
     * @Date 2019-11-29
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(OrderParam orderParam) {
        this.orderService.add(orderParam);
        return ResponseData.success();
    }

    /**
     * 编辑接口
     *
     * @author stylefeng
     * @Date 2019-11-29
     */
    @RequestMapping("/editItem")
    @ResponseBody
    public ResponseData editItem(OrderParam orderParam) {
        this.orderService.update(orderParam);
        return ResponseData.success();
    }

    /**
     * 删除接口
     *
     * @author stylefeng
     * @Date 2019-11-29
     */
    @RequestMapping("/delete")
    @ResponseBody
    public ResponseData delete(OrderParam orderParam) {
        this.orderService.delete(orderParam);
        return ResponseData.success();
    }

    /**
     * 查看详情接口
     *
     * @author stylefeng
     * @Date 2019-11-29
     */
    @RequestMapping("/detail")
    @ResponseBody
    public ResponseData detail(OrderParam orderParam) {
        Order detail = this.orderService.getById(orderParam.getId());
        return ResponseData.success(detail);
    }

    /**
     * 查询列表
     *
     * @author stylefeng
     * @Date 2019-11-29
     */
    @ResponseBody
    @RequestMapping("/list")
    public LayuiPageInfo list(OrderParam orderParam) {
        return this.orderService.findPageBySpec(orderParam);
    }

}


