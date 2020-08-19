package cn.stylefeng.guns.modular.sbdsys.controller;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.sbdsys.entity.PartyBranchPerson;
import cn.stylefeng.guns.modular.sbdsys.model.params.PartyBranchPersonParam;
import cn.stylefeng.guns.modular.sbdsys.model.params.UserExtParam;
import cn.stylefeng.guns.modular.sbdsys.model.result.UserExtResult;
import cn.stylefeng.guns.modular.sbdsys.service.PartyBranchPersonService;
import cn.stylefeng.guns.modular.sbdsys.service.UserExtService;
import cn.stylefeng.guns.sys.modular.system.entity.User;
import cn.stylefeng.guns.sys.modular.system.model.UserDto;
import cn.stylefeng.guns.sys.modular.system.service.UserService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


/**
 * 党支部人员表控制器
 *
 * @author Admin
 * @Date 2019-09-06 10:19:01
 */
@Controller
@RequestMapping("/partyBranchPerson")
public class PartyBranchPersonController extends BaseController {

    private String PREFIX = "//partyBranchPerson";

    @Autowired
    private UserExtService userExtService;
    @Autowired
    private UserService userService;

    @Autowired
    private PartyBranchPersonService partyBranchPersonService;

    /**
     * 跳转到主页面
     *
     * @author Admin
     * @Date 2019-09-06
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/partyBranchPerson.html";
    }

    /**
     * 新增页面
     *
     * @author Admin
     * @Date 2019-09-06
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/partyBranchPerson_add.html";
    }

    /**
     * 编辑页面
     *
     * @author Admin
     * @Date 2019-09-06
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/partyBranchPerson_edit.html";
    }

    /**
     * 新增接口
     *
     * @author Admin
     * @Date 2019-09-06
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(PartyBranchPersonParam partyBranchPersonParam) {
        //根据account获得一个人账号，如果存在则更新，如果不存在则新增
        User user = userService.getByAccount(partyBranchPersonParam.getPhone());
        String uName = partyBranchPersonParam.getName();
        if(uName.length()>15){
            uName = uName.substring(0,15) ;
        }
        if(user == null){
            //添加
            UserDto ud = new UserDto() ;
            ud.setName(uName);
            ud.setPassword("111111");
            ud.setAccount(partyBranchPersonParam.getPhone());
            ud.setDeptId(0l);
            ud.setStatus("ENABLE");
            ud.setSex("M");
            ud.setRoleId(",203");
            ud.setPassword("111111");
            ud.setPhone(partyBranchPersonParam.getPhone());
            this.userService.addUser(ud);
        }else{
            user.setName(uName);
            user.setRoleId(user.getRoleId()+",203");
            this.userService.saveOrUpdate(user) ;
        }
        User userHasAdd = userService.getByAccount(partyBranchPersonParam.getPhone());

        PartyBranchPerson person = this.partyBranchPersonService.getByUserId(userHasAdd.getUserId());
        if(person!=null){
            partyBranchPersonParam.setId(person.getId());
        }
        partyBranchPersonParam.setPerson(userHasAdd.getUserId());
        this.partyBranchPersonService.saveOrUpdate(partyBranchPersonParam);
        return ResponseData.success();
    }
//
//    /**
//     * 编辑接口
//     *
//     * @author Admin
//     * @Date 2019-09-06
//     */
//    @RequestMapping("/editItem")
//    @ResponseBody
//    public ResponseData editItem(PartyBranchPersonParam partyBranchPersonParam) {
//        this.partyBranchPersonService.update(partyBranchPersonParam);
//        return ResponseData.success();
//    }

    /**
     * 删除接口
     *
     * @author Admin
     * @Date 2019-09-06
     */
    @RequestMapping("/delete")
    @ResponseBody
    public ResponseData delete(PartyBranchPersonParam partyBranchPersonParam) {
        this.partyBranchPersonService.delete(partyBranchPersonParam);
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
    public ResponseData detail(PartyBranchPersonParam partyBranchPersonParam) {
        PartyBranchPerson detail = this.partyBranchPersonService.getById(partyBranchPersonParam.getId());
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
    public LayuiPageInfo list(PartyBranchPersonParam partyBranchPersonParam) {
        return this.partyBranchPersonService.findPageBySpec(partyBranchPersonParam);
    }



    /**
     * 删除接口
     *
     * @author Admin
     * @Date 2019-09-06
     */
    @RequestMapping("/deleteBranchPerson")
    @ResponseBody
    public ResponseData deleteBranchPerson(PartyBranchPersonParam partyBranchPersonParam) {
        this.partyBranchPersonService.deleteBranchPerson(partyBranchPersonParam);
        return ResponseData.success();
    }

}


