package cn.stylefeng.guns.modular.sbdsys.controller;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.base.shiro.ShiroUser;
import cn.stylefeng.guns.modular.sbdsys.entity.ActivityRecord;
import cn.stylefeng.guns.modular.sbdsys.entity.UserExt;
import cn.stylefeng.guns.modular.sbdsys.entity.Wish;
import cn.stylefeng.guns.modular.sbdsys.entity.WishRecord;
import cn.stylefeng.guns.modular.sbdsys.model.params.WishParam;
import cn.stylefeng.guns.modular.sbdsys.model.result.ActivityResult;
import cn.stylefeng.guns.modular.sbdsys.model.result.WishRecordResult;
import cn.stylefeng.guns.modular.sbdsys.model.result.WishResult;
import cn.stylefeng.guns.modular.sbdsys.service.UserExtService;
import cn.stylefeng.guns.modular.sbdsys.service.WishRecordService;
import cn.stylefeng.guns.modular.sbdsys.service.WishService;
import cn.stylefeng.guns.sys.core.shiro.ShiroKit;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ResponseData;
import cn.stylefeng.roses.kernel.model.exception.ServiceException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;


/**
 * 微心愿表控制器
 *
 * @author Admin
 * @Date 2019-09-06 10:19:02
 */
@Controller
@RequestMapping("/wish")
public class WishController extends BaseController {

    private String PREFIX = "/wish";

    @Autowired
    private WishService wishService;
    @Autowired
    private UserExtService userExtService;
    @Autowired
    private WishRecordService wishRecordService;

    /**
     * 跳转到主页面
     *
     * @author Admin
     * @Date 2019-09-06
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/wish.html";
    }

    /**
     * 新增页面
     *
     * @author Admin
     * @Date 2019-09-06
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/wish_add.html";
    }

    /**
     * 编辑页面
     *
     * @author Admin
     * @Date 2019-09-06
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/wish_edit.html";
    }

    /**
     * 新增接口
     *
     * @author Admin
     * @Date 2019-09-06
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(WishParam wishParam) {
        UserExt userExt=userExtService.getById(ShiroKit.getUserNotNull().getId());
        if(userExt==null||userExt.getCommunity()==null){
            throw new ServiceException(400,"您不是社区管理员，无法发布微心愿");
        }
        wishParam.setStatus(Wish.STATUS_YFB);
        wishParam.setCommunity(userExt.getCommunity());//关联社区ID
        wishParam.setCreateTime(new Date());
        wishParam.setCreateUser(ShiroKit.getUserNotNull().getId());
        this.wishService.add(wishParam);
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
    public ResponseData editItem(WishParam wishParam) {
        UserExt userExt=userExtService.getById(ShiroKit.getUser().getId());
        if(userExt==null||userExt.getCommunity()==null){
            throw new ServiceException(400,"您不是社区管理员，无法发布微心愿");
        }
        wishParam.setCommunity(userExt.getCommunity());//关联社区ID
        wishParam.setUpdateTime(new Date());
        wishParam.setUpdateUser(ShiroKit.getUser().getId());
        this.wishService.update(wishParam);
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
    public ResponseData delete(WishParam wishParam) {
        this.wishService.delete(wishParam);
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
    public ResponseData detail(WishParam wishParam) {
        WishResult detail = this.wishService.selectByPrimaryId(wishParam.getId());
        ShiroUser user=ShiroKit.getUser();
        //判断是否具备修改权限：是否为当前人创建
        if(user.getId().longValue()==detail.getCreateUser().longValue()){
            detail.setCanEdit(1);
        }
        //获取心愿参与信息
        WishRecordResult record=wishRecordService.getWishRecordDetial(detail.getId());
        if(record!=null){
            detail.setActionTime(record.getActionTime());
            detail.setExecuteTime(record.getExecuteTime());
            detail.setExecuteNote(record.getNote());
            detail.setUserName(record.getUserName());
            detail.setUserPhone(record.getUserPhone());
        }
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
    public LayuiPageInfo list(WishParam wishParam) {
        return this.wishService.findPageBySpec(wishParam);
    }

    /**
     * @Description 查询当前社区
     * @Author lichenfeng
     * @Date 2019/9/10 15:52
     **/
    @ResponseBody
    @RequestMapping("/listOfCommunity")
    public LayuiPageInfo listOfCommunity(WishParam wishParam) {
        UserExt userExt=userExtService.getById(ShiroKit.getUser().getId());
        if(userExt==null||userExt.getCommunity()==null){
            return new LayuiPageInfo();
        }
        wishParam.setCommunity(userExt.getCommunity());//关联社区ID
        return this.wishService.findPageBySpec(wishParam);
    }

    /**
     * @Description 获取当前登录用户的心愿列表
     * @Author lichenfeng
     * @Date 2019/9/11 11:10
     **/
    @ResponseBody
    @RequestMapping("/listOfUser")
    public LayuiPageInfo listOfUser(@RequestParam(name = "status")int status) {
        return this.wishService.findPageByUser(ShiroKit.getUserNotNull().getId(),status);
    }

    /**
     * @Description 领取心愿
     * @Author lichenfeng
     * @Date 2019/9/11 10:55
     **/
    @RequestMapping("/receive")
    @ResponseBody
    public ResponseData receive(@RequestParam(name = "wishId")String wishId) {
        return this.wishService.dealReceive(wishId);
    }

    /**
     * @Description 执行心愿
     * @Author lichenfeng
     * @Date 2019/9/11 10:56
     **/
    @RequestMapping("/execute")
    @ResponseBody
    public ResponseData execute(@RequestParam(name = "wishId")String wishId, @RequestParam("note")String note) {
        return this.wishService.dealExecute(wishId,note);
    }

    /**
     * @Description 核实执行
     * @Author lichenfeng
     * @Date 2019/9/12 14:43
     **/
    @RequestMapping("/confirm")
    @ResponseBody
    public ResponseData confirm(@RequestParam(name = "wishId")String wishId) {
        return this.wishService.dealConfirm(wishId);
    }

}


