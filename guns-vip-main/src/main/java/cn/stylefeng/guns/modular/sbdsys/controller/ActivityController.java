package cn.stylefeng.guns.modular.sbdsys.controller;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.base.shiro.ShiroUser;
import cn.stylefeng.guns.modular.sbdsys.entity.*;
import cn.stylefeng.guns.modular.sbdsys.model.params.ActivityParam;
import cn.stylefeng.guns.modular.sbdsys.model.result.ActivityResult;
import cn.stylefeng.guns.modular.sbdsys.service.ActivityRecordService;
import cn.stylefeng.guns.modular.sbdsys.service.ActivityService;
import cn.stylefeng.guns.modular.sbdsys.service.MyFileService;
import cn.stylefeng.guns.modular.sbdsys.service.UserExtService;
import cn.stylefeng.guns.sys.core.shiro.ShiroKit;
import cn.stylefeng.guns.util.DateUtils;
import cn.stylefeng.guns.util.TextUtils;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ResponseData;
import cn.stylefeng.roses.kernel.model.exception.ServiceException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;


/**
 * 活动表控制器
 *
 * @author Admin
 * @Date 2019-09-06 10:19:01
 */
@Controller
@RequestMapping("/activity")
@Slf4j
public class ActivityController extends BaseController {

    private String PREFIX = "/activity";

    @Autowired
    private ActivityService activityService;
    @Autowired
    private UserExtService userExtService;
    @Autowired
    private MyFileService myFileService;
    @Autowired
    private ActivityRecordService activityRecordService;


    /**
     * 跳转到主页面
     *
     * @author Admin
     * @Date 2019-09-06
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/activity.html";
    }

    /**
     * 新增页面
     *
     * @author Admin
     * @Date 2019-09-06
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/activity_add.html";
    }

    /**
     * 编辑页面
     *
     * @author Admin
     * @Date 2019-09-06
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/activity_edit.html";
    }

    /**
     * 新增接口
     *
     * @author lichenfeng
     * @Date 2019-09-06
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(ActivityParam activityParam) {
        //获取人员所在社区
        UserExt userExt=userExtService.getById(ShiroKit.getUser().getId());
        if(userExt==null||StringUtils.isEmpty(userExt.getCommunity())){
            throw new ServiceException(400,"您不是社区管理员，无法发布活动");
        }
        activityParam.setCommunity(userExt.getCommunity());//关联社区ID
        activityParam.setId(IdWorker.get32UUID());
        activityParam.setCreateTime(new Date());
        activityParam.setCreateUser(ShiroKit.getUser().getId());
        activityParam.setState(Activity.STATE_JXZ);
        this.activityService.add(activityParam);
        return ResponseData.success(activityParam.getId());
    }

    /**
     * 编辑接口
     *
     * @author Admin
     * @Date 2019-09-06
     */
    @RequestMapping("/editItem")
    @ResponseBody
    public ResponseData editItem(ActivityParam activityParam) {
        //获取人员所在社区
        UserExt userExt=userExtService.getById(ShiroKit.getUser().getId());
        if(userExt==null||userExt.getCommunity()==null){
            return ResponseData.error(400,"您不是社区管理员，无法编辑活动");
        }
        activityParam.setCommunity(userExt.getCommunity());//关联社区ID
        activityParam.setUpdateTime(new Date());
        activityParam.setUpdateUser(ShiroKit.getUser().getId());
        this.activityService.update(activityParam);
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
    public ResponseData delete(ActivityParam activityParam) {
        this.activityService.delete(activityParam);
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
    public ResponseData detail(String id) {
        ActivityResult detail = this.activityService.selectByPrimaryId(id);
        ShiroUser user=ShiroKit.getUser();
        //判断是否具备修改权限：是否为当前人创建
        if(user.getId().longValue()==detail.getCreateUser().longValue()){
            detail.setCanEdit(1);
        }
        //判断是否展示二维码：是否为发布社区负责人
        UserExt userExt=userExtService.getById(ShiroKit.getUser().getId());
        if(userExt!=null&&userExt.getCommunity()!=null&&userExt.getCommunity().equals(detail.getCommunity())){
            detail.setQrcode("ACTIVITY:"+detail.getId());
        }
        //判断是否已参加该活动
        List<ActivityRecord> records=activityRecordService.list(new QueryWrapper<ActivityRecord>()
                .eq("activity_id", detail.getId())
                .eq("user_id", user.getId()));
        if(records!=null&&!records.isEmpty()){
            detail.setActionTime(records.get(0).getActionTime());
        }
        //获取活动的参加人数
        detail.setActionCount(activityRecordService.count(new QueryWrapper<ActivityRecord>()
                .eq("activity_id", detail.getId())));
        return ResponseData.success(detail);
    }

    /**
     * @Description 扫码功能
     * @Author lichenfeng
     * @Date 2019/9/9 17:19
     **/
    @RequestMapping("/scan")
    @ResponseBody
    public ResponseData scan(@RequestParam(name = "activityId")String activityId,@RequestParam(name = "coordinate",required = false)String coordinate) {
        try{
            return this.activityService.dealScan(activityId,coordinate);
        }catch(Exception ex){
            log.error("扫码处理接口异常",ex);
            return ResponseData.error(400, "网络异常请重试");
        }
    }

    /**
     * 查询列表
     * state:是否完成
     * @author Admin
     * @Date 2019-09-06
     */
    @ResponseBody
    @RequestMapping("/list")
    public LayuiPageInfo list(ActivityParam activityParam) {
        return this.activityService.findPageBySpec(activityParam);
    }

    /**
     * @Description 查询当前社区
     * @Author lichenfeng
     * @Date 2019/9/10 15:52
     **/
    @ResponseBody
    @RequestMapping("/listOfCommunity")
    public LayuiPageInfo listOfCommunity(ActivityParam activityParam) {
        UserExt userExt=userExtService.getById(ShiroKit.getUser().getId());
        if(userExt==null||StringUtils.isEmpty(userExt.getCommunity())){
            return new LayuiPageInfo();
        }
        activityParam.setCommunity(userExt.getCommunity());//关联社区ID
        return this.activityService.findPageBySpec(activityParam);
    }

    /**
     * @Description 获取报道社区的活动列表
     * @Author lichenfeng
     * @Date 2019/10/11 16:55
     **/
    @ResponseBody
    @RequestMapping("/listOfBranchCommunity")
    public LayuiPageInfo listOfBranchCommunity(ActivityParam activityParam) {
        List<String> communitys=this.activityService.getBranchCommunityIds(ShiroKit.getUser().getId());
        if(communitys==null||communitys.isEmpty()){
            return new LayuiPageInfo();
        }
        activityParam.setCommunityIds(communitys);//关联社区ID
        return this.activityService.findPageBySpec(activityParam);
    }

    /**
     * @Description 获取用户可参加的活动列表
     * @Author lichenfeng
     * @Date 2019/9/10 14:17
     **/
    @ResponseBody
    @RequestMapping("/getJoinListByUser")
    public LayuiPageInfo getJoinListByUser() {
        return this.activityService.getJoinListByUser();
    }

    /**
     * @Description 获取已参加的活动列表
     * @Author lichenfeng
     * @Date 2019/9/10 15:16
     **/
    @ResponseBody
    @RequestMapping("/getDoneListByUser")
    public LayuiPageInfo getDoneListByUser() {
        return this.activityService.getDoneListByUser();
    }


    /****
     * 活动总结  --
     */
    @RequestMapping("/sumUpActivity")
    public String sumUpActivity(@RequestParam(name = "activityId")String activityId,
                                Model model) {
        ActivitySumup paramActivitySumup = new ActivitySumup();
        paramActivitySumup.setActivityId(activityId);
        ActivitySumup detail = this.activityService.selectActivityDetailWithSumUp(paramActivitySumup);
        ShiroUser user=ShiroKit.getUser();
        long userId = user.getId();
//        //判断是否具备修改权限：是否为当前人创建
//        if(user.getId().longValue()==detail.getCreateUser(){
//            detail.setCanEdit(1);
//        }
        System.out.println(" activityId :" +detail.getActivityId());

        int sumUpId = detail.getId();
        String content = detail.getContent();
        long createUser = detail.getCreateUser();


        String tempId = TextUtils.getUserTempToken(userId);

        int hasImage = 0;

        // 没有数据
        if ( sumUpId == 0){
            content = "";
        } else {
            content = TextUtils.getHtmlStringToTextArea(content);
            hasImage = 1;
//            MyFile paramFile = new MyFile();
//            String objId = String.valueOf(sumUpId);
//            paramFile.setObjId(objId);;
//            MyFile file = myFileService.selectFileTempIdFromObj(paramFile);
//            if (null != file ){
//               String fileTempId = file.getTempId();
//               if (TextUtils.isNotEmpty( fileTempId)){
//                   tempId = fileTempId;
//                   hasImage = 1;
//               }
//            }
        }

        detail.setContent(content);


        System.out.println(" activityId :" +detail.getActivityId());

        model.addAttribute("detail", detail);
        model.addAttribute("tempId", tempId);
        model.addAttribute("hasImage", hasImage);

        return PREFIX + "/activity_sumup.html";
    }

    @ResponseBody
    @GetMapping("/getActivitySumUp")
    public ResponseData getActivitySumup(@RequestParam(value = "activityId", required = true) String activityId ){
        ActivitySumup paramActivitySumup = new ActivitySumup();
        paramActivitySumup.setActivityId(activityId);
        ActivitySumup detail = this.activityService.selectActivityDetailWithSumUp(paramActivitySumup);

        ShiroUser user=ShiroKit.getUser();

        int sumUpId = detail.getId();
        String content = detail.getContent();

        int hasImage = 0;
        // 没有数据
        if ( sumUpId == 0){
            content = "";
        } else {
            content = TextUtils.getHtmlStringToTextArea(content);
            hasImage = 1;

        }

        detail.setContent(content);

        HashMap rsMap = new HashMap();
        rsMap.put("detail", detail);
        rsMap.put("hasImage", hasImage);
        rsMap.put("sumUpId", sumUpId);

        return ResponseData.success(rsMap);
    }

    @ResponseBody
    @PostMapping("/saveActivitySumUp")
    public ResponseData saveSuggestion(@RequestParam(value = "content", required = true) String content ,
                                       @RequestParam(value = "tempId", required = true) String tempId,
                                       @RequestParam(value = "activityId", required = true) String activityId){
        long userId = ShiroKit.getUser().getId();

        ActivitySumup activitySumup = new ActivitySumup();

        String currentDate = DateUtils.getCurrentDate();


        content = TextUtils.getHtmlStringFromTextArea(content);

        activitySumup.setContent(content);
        activitySumup.setCreateDate(currentDate);
        activitySumup.setCreateUser(userId);
        activitySumup.setUpdateDate(currentDate);
        activitySumup.setUpdateUser(userId);
        activitySumup.setRemark("");
        activitySumup.setIsShow(0);
        activitySumup.setIsDel(0);
        activitySumup.setStatus(1);
        activitySumup.setActivityId(activityId);

        ActivitySumup existsActivitySumpup = activityService.selectActivitySumUpExists(activitySumup);

        int sumUpId = 0;
        if (null != existsActivitySumpup) {
            sumUpId = existsActivitySumpup.getId();
        }

        if (sumUpId == 0){
            activityService.insertActivitySumUp(activitySumup);
            sumUpId = activitySumup.getId();
        } else {

            activitySumup.setId(sumUpId);
            activityService.updateActivitySumUp(activitySumup);
        }





        if (sumUpId > 0){
            MyFile myFile = new MyFile();
            myFile.setObjId(String.valueOf(sumUpId));
            myFile.setTempId(tempId);
            myFileService.updateFileWith(myFile);
        }


        return ResponseData.success();

    }

}


