package cn.stylefeng.guns.modular.sbdsys.controller;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.sbdsys.entity.UserExt;
import cn.stylefeng.guns.modular.sbdsys.model.params.UserExtParam;
import cn.stylefeng.guns.modular.sbdsys.model.result.UserExtResult;
import cn.stylefeng.guns.modular.sbdsys.service.UserExtService;
import cn.stylefeng.guns.sys.core.shiro.ShiroKit;
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
 * 联系人表控制器
 *
 * @author Admin
 * @Date 2019-09-06 10:19:01
 */
@Controller
@RequestMapping("/userExt")
public class UserExtController extends BaseController {

    private String PREFIX = "//userExt";

    @Autowired
    private UserExtService userExtService;
    @Autowired
    private UserService userService;

    /**
     * 跳转到主页面
     *
     * @author Admin
     * @Date 2019-09-06
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/userExt.html";
    }

    /**
     * 新增页面
     *
     * @author Admin
     * @Date 2019-09-06
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/userExt_add.html";
    }

    /**
     * 编辑页面
     *
     * @author Admin
     * @Date 2019-09-06
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/userExt_edit.html";
    }

    /**
     * 新增接口
     *
     * @author Admin
     * @Date 2019-09-06
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(UserExtParam userExtParam) {
        //根据account获得一个人账号，如果存在则更新，如果不存在则新增
        User user = userService.getByAccount(userExtParam.getPhone());
        if(user == null){
            //添加
            String salt = ShiroKit.getRandomSalt(5);
            String password = ShiroKit.md5("111111", salt);
            user = new User();
            user.setSalt(salt);
            user.setPassword(password);
            user.setAccount(userExtParam.getPhone());
            user.setDeptId(0l);
            user.setStatus("ENABLE");
            user.setSex("M");
            user.setRoleId("");
            user.setPhone(userExtParam.getPhone());
        }
        user.setName(userExtParam.getName());
        switch(userExtParam.getType()){
            case "committee":
                user.setRoleId(user.getRoleId()+",201");
                break ;
            case "branch":
                user.setRoleId(user.getRoleId()+",202");
                break ;
            case "community":
                user.setRoleId(user.getRoleId()+",204");
                break ;
            case "store":
                user.setRoleId(user.getRoleId()+",205");
                break ;
        }
        this.userService.saveOrUpdate(user);
        User userHasAdd = userService.getByAccount(userExtParam.getPhone());
        List<UserExtResult> ueList = userExtService.getByPhone(userExtParam.getPhone());


        if(ueList.size()>0){
            UserExtResult ueR = ueList.get(0);
            userExtParam.setStore(ueR.getStore());
            userExtParam.setBranch(ueR.getBranch());
            userExtParam.setCommittee(ueR.getCommittee());
            userExtParam.setCommunity(ueR.getCommunity());
        }


        userExtParam.setUserId(userHasAdd.getUserId());
        switch(userExtParam.getType()){
            case "community":
                userExtParam.setCommunity(userExtParam.getBaseID());
                break ;
            case "committee":
                userExtParam.setCommittee(userExtParam.getBaseID());
                break ;
            case "branch":
                userExtParam.setBranch(userExtParam.getBaseID());
                break ;
            case "store":
                userExtParam.setStore(userExtParam.getBaseID());
                break ;
        }
        this.userExtService.saveOrUpdate(userExtParam);
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
    public ResponseData editItem(UserExtParam userExtParam) {
        this.userExtService.update(userExtParam);
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
    public ResponseData delete(UserExtParam userExtParam) {
        List<UserExtResult> ueList = userExtService.getByUserID(userExtParam);
        if(ueList.size()>0){
            UserExtResult ueR = ueList.get(0) ;
            userExtParam.setPhone(ueR.getPhone());
            userExtParam.setStore(ueR.getStore());
            userExtParam.setBranch(ueR.getBranch());
            userExtParam.setCommittee(ueR.getCommittee());
            userExtParam.setCommunity(ueR.getCommunity());
        }
        switch(userExtParam.getType()){
            case "community":
                userExtParam.setCommunity("");
                break ;
            case "committee":
                userExtParam.setCommittee("");
                break ;
            case "branch":
                userExtParam.setBranch("");
                break ;
            case "store":
                userExtParam.setStore("");
                break ;
        }
        this.userExtService.saveOrUpdate(userExtParam);
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
    public ResponseData detail(UserExtParam userExtParam) {
        UserExt detail = this.userExtService.getById(userExtParam.getUserId());
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
    public LayuiPageInfo list(UserExtParam userExtParam) {
        return this.userExtService.findPageBySpec(userExtParam);
    }

}


