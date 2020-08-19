package cn.stylefeng.guns.modular.sbdsys.controller;

import cn.stylefeng.guns.base.log.BussinessLog;
import cn.stylefeng.guns.base.pojo.node.ZTreeNode;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.base.shiro.annotion.Permission;
import cn.stylefeng.guns.modular.sbdsys.entity.BranchCommunity;
import cn.stylefeng.guns.modular.sbdsys.entity.PartyBranch;
import cn.stylefeng.guns.modular.sbdsys.model.params.PartyBranchParam;
import cn.stylefeng.guns.modular.sbdsys.service.BranchCommunityService;
import cn.stylefeng.guns.modular.sbdsys.service.PartyBranchService;
import cn.stylefeng.guns.sys.core.constant.Const;
import cn.stylefeng.guns.sys.core.constant.dictmap.UserDict;
import cn.stylefeng.guns.sys.core.exception.enums.BizExceptionEnum;
import cn.stylefeng.guns.sys.modular.system.entity.User;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ResponseData;
import cn.stylefeng.roses.core.util.ToolUtil;
import cn.stylefeng.roses.kernel.model.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


/**
 * 党支部表控制器
 *
 * @author Admin
 * @Date 2019-09-06 10:19:01
 */
@Controller
@RequestMapping("/partyBranch")
public class PartyBranchController extends BaseController {

    private String PREFIX = "//partyBranch";

    @Autowired
    private PartyBranchService partyBranchService;
    @Autowired
    private BranchCommunityService branchCommunityService;

    /**
     * 跳转到主页面
     *
     * @author Admin
     * @Date 2019-09-06
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/partyBranch.html";
    }

    /**
     * 新增页面
     *
     * @author Admin
     * @Date 2019-09-06
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/partyBranch_add.html";
    }

    /**
     * 编辑页面
     *
     * @author Admin
     * @Date 2019-09-06
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/partyBranch_edit.html";
    }

    /**
     * 新增接口
     *
     * @author Admin
     * @Date 2019-09-06
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(PartyBranchParam partyBranchParam) {
        this.partyBranchService.add(partyBranchParam);
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
    public ResponseData editItem(PartyBranchParam partyBranchParam) {
        this.partyBranchService.update(partyBranchParam);
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
    public ResponseData delete(PartyBranchParam partyBranchParam) {
        this.partyBranchService.delete(partyBranchParam);
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
    public ResponseData detail(PartyBranchParam partyBranchParam) {
        PartyBranch detail = this.partyBranchService.getById(partyBranchParam.getId());
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
    public LayuiPageInfo list(PartyBranchParam partyBranchParam) {
        return this.partyBranchService.findPageBySpec(partyBranchParam);
    }


    @RequestMapping(value = "/communityByBranch/{branch}")
    @ResponseBody
    public List<ZTreeNode> roleTreeListByUserId(@PathVariable String branch) {

        List<ZTreeNode> treeList = this.partyBranchService.communityTreeListByBranch(branch);
        return treeList;
    }

    @RequestMapping("/toCommunity")
    public String toCommunity() {
        return PREFIX + "/toCommunity.html";
    }

    /**
     * 分配社区
     *
     * @author fengshuonan
     * @Date 2018/12/24 22:44
     */
    @RequestMapping("/setCommunity")
    @BussinessLog(value = "分配社区", key = "branch,community")
    @ResponseBody
    public ResponseData setCommunity(@RequestParam("branch") String branch, @RequestParam("community") String community) {
        this.branchCommunityService.deleteByBranch(branch);
        for(String communityID : community.split("\\,")){
            BranchCommunity bc = new BranchCommunity();
            bc.setCommunity(communityID);
            bc.setBranch(branch);
            this.branchCommunityService.save(bc);
        }
        return SUCCESS_TIP;
    }
}


