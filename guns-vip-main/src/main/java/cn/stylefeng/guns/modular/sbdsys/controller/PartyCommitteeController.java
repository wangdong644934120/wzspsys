package cn.stylefeng.guns.modular.sbdsys.controller;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.sbdsys.entity.PartyCommittee;
import cn.stylefeng.guns.modular.sbdsys.model.params.PartyCommitteeParam;
import cn.stylefeng.guns.modular.sbdsys.service.PartyCommitteeService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;


/**
 * 党委表控制器
 *
 * @author Admin
 * @Date 2019-09-06 10:19:01
 */
@Controller
@RequestMapping("/partyCommittee")
public class PartyCommitteeController extends BaseController {

    private String PREFIX = "//partyCommittee";

    @Autowired
    private PartyCommitteeService partyCommitteeService;

    /**
     * 跳转到主页面
     *
     * @author Admin
     * @Date 2019-09-06
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/partyCommittee.html";
    }

    /**
     * 新增页面
     *
     * @author Admin
     * @Date 2019-09-06
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/partyCommittee_add.html";
    }

    /**
     * 编辑页面
     *
     * @author Admin
     * @Date 2019-09-06
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/partyCommittee_edit.html";
    }

    /**
     * 新增接口
     *
     * @author Admin
     * @Date 2019-09-06
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(PartyCommitteeParam partyCommitteeParam) {
        partyCommitteeParam.setCreateTime(new Date());
        partyCommitteeParam.setUpdateTime(new Date());
        this.partyCommitteeService.add(partyCommitteeParam);
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
    public ResponseData editItem(PartyCommitteeParam partyCommitteeParam) {
        partyCommitteeParam.setCreateTime(new Date());
        partyCommitteeParam.setUpdateTime(new Date());
        this.partyCommitteeService.update(partyCommitteeParam);
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
    public ResponseData delete(PartyCommitteeParam partyCommitteeParam) {
        this.partyCommitteeService.delete(partyCommitteeParam);
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
    public ResponseData detail(PartyCommitteeParam partyCommitteeParam) {
        PartyCommittee detail = this.partyCommitteeService.getById(partyCommitteeParam.getId());
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
    public LayuiPageInfo list(PartyCommitteeParam partyCommitteeParam) {
        return this.partyCommitteeService.findPageBySpec(partyCommitteeParam);
    }

}


