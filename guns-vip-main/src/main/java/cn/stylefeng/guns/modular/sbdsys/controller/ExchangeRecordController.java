package cn.stylefeng.guns.modular.sbdsys.controller;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.sbdsys.entity.ExchangeRecord;
import cn.stylefeng.guns.modular.sbdsys.model.params.ExchangeRecordParam;
import cn.stylefeng.guns.modular.sbdsys.service.ExchangeRecordService;
import cn.stylefeng.guns.sys.core.shiro.ShiroKit;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 消费记录控制器
 *
 * @author Admin
 * @Date 2019-09-06 10:19:01
 */
@Controller
@RequestMapping("/exchangeRecord")
public class ExchangeRecordController extends BaseController {
    private String PREFIX = "//exchangeRecord";

    @Autowired
    private ExchangeRecordService exchangeRecordService;

    /**
     * 跳转到主页面
     *
     * @author Admin
     * @Date 2019-09-06
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/exchangeRecord.html";
    }

    /**
     * 新增页面
     *
     * @author Admin
     * @Date 2019-09-06
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/exchangeRecord_add.html";
    }

    /**
     * 编辑页面
     *
     * @author Admin
     * @Date 2019-09-06
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/exchangeRecord_edit.html";
    }

    /**
     * 新增接口
     *
     * @author Admin
     * @Date 2019-09-06
     */
    @RequestMapping("/addItem")
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public ResponseData addItem(ExchangeRecordParam exchangeRecordParam) throws Exception {
        this.exchangeRecordService.add(exchangeRecordParam);
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
    public ResponseData editItem(ExchangeRecordParam exchangeRecordParam) {
        this.exchangeRecordService.update(exchangeRecordParam);
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
    public ResponseData delete(ExchangeRecordParam exchangeRecordParam) {
        this.exchangeRecordService.delete(exchangeRecordParam);
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
    public ResponseData detail(ExchangeRecordParam exchangeRecordParam) {
        ExchangeRecord detail = this.exchangeRecordService.getById(exchangeRecordParam.getId());
        return ResponseData.success(detail);
    }

    /**
     * 查询商品兑换列表
     *
     * @author Admin
     * @Date 2019-09-06
     */
    @ResponseBody
    @RequestMapping("/list")
    public LayuiPageInfo list(ExchangeRecordParam exchangeRecordParam) {
        return this.exchangeRecordService.findPageBySpec(exchangeRecordParam);
    }

    /**
     * 查询个人的待兑换列表
     *
     * @author Admin
     * @Date 2019-09-06
     */
    @ResponseBody
    @RequestMapping("/list4PartyMember")
    public LayuiPageInfo list4PartyMember(ExchangeRecordParam exchangeRecordParam) {
        Long userId = ShiroKit.getUser().getId();
        exchangeRecordParam.setUserId(userId);
        return this.exchangeRecordService.findPageBySpec(exchangeRecordParam);
    }


}


