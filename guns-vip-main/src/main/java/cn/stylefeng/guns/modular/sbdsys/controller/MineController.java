package cn.stylefeng.guns.modular.sbdsys.controller;


import cn.stylefeng.guns.base.shiro.ShiroUser;
import cn.stylefeng.guns.modular.sbdsys.entity.*;
import cn.stylefeng.guns.modular.sbdsys.service.MineService;
import cn.stylefeng.guns.sys.core.exception.enums.BizExceptionEnum;
import cn.stylefeng.guns.sys.core.properties.GunsProperties;
import cn.stylefeng.guns.sys.core.shiro.ShiroKit;
import cn.stylefeng.guns.sys.modular.system.entity.FileInfo;
import cn.stylefeng.guns.sys.modular.system.entity.User;
import cn.stylefeng.guns.util.*;
import cn.stylefeng.guns.util.wechat.WechatUtils;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ResponseData;
import cn.stylefeng.roses.kernel.model.exception.ServiceException;
import cn.stylefeng.roses.kernel.model.exception.enums.CoreExceptionEnum;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ClassUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 个人中心 控制器
 *
 * @author Admin
 * @Date 2019-09-06 10:19:01
 */
@Controller
@RequestMapping("/mine")
public class MineController extends BaseController {

    private String PREFIX = "/mine";

    @Autowired
    WechatUtils wechatUtils;

    @Autowired
    MineService mineService;


    @GetMapping("/getUserInfo")
    @ResponseBody
    public ResponseData userInfo(Model model){
        ShiroUser user = ShiroKit.getUser();
        long userId = user.getId();
        List<Long> roleList = user.getRoleList();

        Census paramCensus = new Census();
        paramCensus.setUserId(userId);
        // 获取 用户对应 角色
        Census census = mineService.selectUserRoleInfo(paramCensus);
        if (census == null) {
            census = new Census();
        }
        System.out.println(user);
        System.out.println(roleList);
        if (null != roleList) {
            for (long roleid : roleList){

                switch ((int)roleid ) {
                    case 1:
                        census.setManager(1);
//                        census.setCommitteeManager(1);
//                        census.setBranchManager(1);
//                        census.setUserManager(1);
//                        census.setCommunityManager(1);
//                        census.setStoreManager(1);
                        break;
                    case 2:
                        census.setManager(1);
//                        census.setCommitteeManager(1);
//                        census.setBranchManager(1);
//                        census.setUserManager(1);
//                        census.setCommunityManager(1);
//                        census.setStoreManager(1);
                        break;
                    case 201:
                        census.setCommitteeManager(1);
                        break;
                    case 202:
                        census.setBranchManager(1);
                        break;
                    case 203:
                        census.setUserManager(1);
                        break;
                    case 204:
                        census.setCommunityManager(1);
                        break;
                    case 205:
                        census.setStoreManager(1);
                        break;
                }
            }
        }

        List<Census> branchCensusList = mineService.selectUserBranchInfo(paramCensus);

        Census branch = new Census();
        if (null != branchCensusList && branchCensusList.size() > 0){
            branch =  branchCensusList.get(0);

            census.setBranchId(branch.getBranchId());
        }



        return ResponseData.success(census);
    }



    @RequestMapping("/")
    public String mine(Model model){
        ShiroUser user = ShiroKit.getUser();
        long userId = user.getId();

        Census paramCensus = new Census();
        paramCensus.setUserId(userId);
        // 获取 用户对应 角色
        Census census = mineService.selectUserRoleInfo(paramCensus);
        if (census == null) {
            census = new Census();
        }
        model.addAttribute("info", census);

        List<Census> branchCensusList = mineService.selectUserBranchInfo(paramCensus);

        Census branch = new Census();
        if (null != branchCensusList && branchCensusList.size() > 0){
            branch =  branchCensusList.get(0);
        }
        model.addAttribute("branch", branch);

        return PREFIX + "/mine.html";
    }

//    @RequestMapping("/account")
//    public String account(){
//        return PREFIX + "/account.html";
//    }
//
//
//    @RequestMapping("/community")
//    public String community(){
//        return PREFIX + "/community.html";
//    }
//
//
//    @RequestMapping("/branch")
//    public String branch(){
//        return PREFIX + "/branch.html";
//    }
//
//    @RequestMapping("/party")
//    public String party(){
//        return PREFIX + "/party.html";
//    }
//
//
//    @RequestMapping("/admin")
//    public String admin(){
//        return PREFIX + "/admin.html";
//    }

    // 页面跳转

    // 建议反馈


    @RequestMapping("/my_manual")
    public String myManual(){
        return PREFIX + "/manual/manual.html";
    }


    // 个人中心 模块
    // 我的 活动
    @RequestMapping("/myActivity")
    public String myActivity(){
        return PREFIX + "/memberActivity.html";
    }

    // 我的支部
    @RequestMapping("/myBranch")
    public String myBranch(){
        return PREFIX + "/member/member_branch.html";
    }

    // 我的数据统计
    @RequestMapping("/myCensus")
    public String myCensus(){
        return PREFIX + "/member/member_census.html";
    }

    // 我的商品 - 待换商品
    @RequestMapping("/myCommodity2")
    public String myCommodity2(){
        return PREFIX + "/memberCommodity2.html";
    }

    // 我的商品 - 收藏商品
    @RequestMapping("/myFavorite")
    public String myFavorite(){
        return PREFIX + "/memberFavorite.html";
    }

    // 我的账单
    @RequestMapping("/myCoinRecord")
    public String myCoinRecord(){
        return PREFIX + "/memberCoinRecord.html";
    }

    // 我的排行
    @RequestMapping("/myRank")
    public String myRank(){
        return PREFIX + "/member/member_rank.html";
    }


    // 我的排行
    @RequestMapping("/doubleRelation")
    public String doubleRelation(){
        return PREFIX + "/double_relation.html";
    }
    // 党委模块

    // 支部成员
    @RequestMapping("/committee_branch_list")
    public String committeeBranchList(){
        return PREFIX + "/committee/committee_branch_list.html";
    }

    // 支部成员
    @RequestMapping("/committee_branch")
    public String committeeBranch(@RequestParam(value = "branchId", required = true) String branchId, Model model){
        System.out.println("committee_branch::: branchId "+branchId);
        model.addAttribute("branchId", branchId);
        return PREFIX + "/committee/committee_branch_with_id.html";
    }

    // 成员列表
    @RequestMapping("/committee_branch_member")
    public String committeeBranchMember(@RequestParam(value = "branchId", required = true) String branchId, Model model){
        model.addAttribute("branchId", branchId);
        return PREFIX + "/committee/committee_branch_member.html";
    }


    @RequestMapping("/committee_census")
    public String committeeCensus(){
        return PREFIX + "/committee/committee_census.html";
    }

    // 全区支部排行 和 全区人员排行
    @RequestMapping("/all_branch_rank")
    public String all_branch_rank(){
        return PREFIX + "/admin/all_branch_rank.html";
    }
    @RequestMapping("/all_member_rank")
    public String all_member_rank(){
        return PREFIX + "/admin/all_member_rank.html";
    }


    // 全区支部排行 和 全区人员排行
    @RequestMapping("/all_census")
    public String all_census(){
        return PREFIX + "/admin/all_census.html";
    }


    // 党支部模块
    // 成员列表
    @RequestMapping("/branch_member_rank")
    public String branchMemberRank(){

        return PREFIX + "/branch/branch_member_rank.html";
    }

    // 成员列表
    @RequestMapping("/branch_member")
    public String branchMember(){

        return PREFIX + "/branch/branch_member.html";
    }

    // 添加成员
    @RequestMapping("/branch_add_member")
    public String branchAddMember(){
        return PREFIX + "/branch/branch_add_member.html";
    }

    // 数据分析
    @RequestMapping("/branch_census")
    public String branchCensus(@RequestParam(value = "branchId", required = true) String branchId, Model model){
        if (  TextUtils.isEmpty(branchId) ) {
            return PREFIX + "/branch/branch_census.html";
        }

        model.addAttribute("branchId", branchId);
        return PREFIX + "/branch/branch_census.html";
    }

    // 支部排行 支部
    @RequestMapping("/branch_rank")
    public String branchRank(@RequestParam(value = "branchId", required = true) String branchId, Model model){
        if (  TextUtils.isEmpty(branchId) ) {
            return PREFIX + "/branch/branch_census.html";
        }

        model.addAttribute("branchId", branchId);
        return PREFIX + "/branch/branch_rank.html";
    }


    // 社区模块
    // 社区数据分析
    @RequestMapping("/community_census")
    public String communityCensus(){
        return PREFIX + "/community/community_census.html";
    }




    @ResponseBody
    @RequestMapping("/mydata")
    public ResponseData myData(@RequestParam(value = "userId", required = true) String userId) {
        System.out.println("userId:"+userId);
        return ResponseData.success();
    }


    /***
     * 党员个人中心数据
     *
     * 此数据包含  （页面从上至下 ）
     *  1、 个人信息 -  名称（电话）  头像 、 所在组
     *  2、 个人金币， 个人积分， 待换商品
     *  3、 最近要参加的活动或者心愿（3条）
     * @return
     */
    @ResponseBody
    @RequestMapping("/getAccountInfo")
    public ResponseData getAccountInfo() {
        long userId = ShiroKit.getUser().getId();

        if ( userId == 0 ) {
            return ResponseData.error("用户没有权限");
        }

        MineAccount paramUser = new MineAccount();
        paramUser.setUserId(userId);
        // 查询用户信息
        MineAccount userInfo = mineService.selectUserInfo(paramUser);

        if (null != userInfo){
            int score = userInfo.getScoreWish() + userInfo.getScoreActivity();
            userInfo.setScore(score);
        }

        HashMap rsMap = new HashMap<>();
        rsMap.put("userinfo", userInfo);

        return ResponseData.success(rsMap);
    }

    /***
     * 党员个人中心数据
     *
     * 此数据包含  （页面从上至下 ）
     *  1、 个人信息 -  名称（电话）  头像 、 所在组
     *  2、 个人金币， 个人积分， 待换商品
     *  3、 最近要参加的活动或者心愿（3条）
     * @return
     */
    @ResponseBody
    @RequestMapping("/get/account_data")
    public ResponseData getAccountData() {
        long userId = ShiroKit.getUser().getId();

        if ( userId == 0 ) {
            return ResponseData.error("用户没有权限");
        }

        MineAccount paramUser = new MineAccount();
        paramUser.setUserId(userId);
        // 查询用户信息
        MineAccount userInfo = mineService.selectUserInfo(paramUser);

        if (null != userInfo){
            int score = userInfo.getScoreWish() + userInfo.getScoreActivity();
            userInfo.setScore(score);
        }

        Census paramCensus = new Census();

        String month = DateUtils.getMonth();
        String year = DateUtils.getYear();

        paramCensus.setUserId(userId);
        paramCensus.setMonth(month);
        paramCensus.setYear(year);

        Census activityCensus = mineService.selectUserActivityScore(paramCensus);
        Census wishCensus = mineService.selectUserWishScore(paramCensus);

        System.out.println(activityCensus);
        System.out.println(wishCensus);

        HashMap rsMap = new HashMap<>();

        rsMap.put("userinfo", userInfo);
        rsMap.put("activity", activityCensus);
        rsMap.put("wish", wishCensus);

        return ResponseData.success(rsMap);
    }

    @ResponseBody
    @RequestMapping("/get/account_census")
    public ResponseData getAccountCensus() {
        long userId = ShiroKit.getUser().getId();

        if ( userId == 0 ) {
            return ResponseData.error("用户没有权限");
        }
        Census paramCensus = new Census();
        String month = DateUtils.getMonth();
        String year = DateUtils.getYear();

        paramCensus.setUserId(userId);
        paramCensus.setMonth(month);
        paramCensus.setYear(year);
        Census activityCensus = mineService.selectUserActivityScore(paramCensus);
        Census wishCensus = mineService.selectUserWishScore(paramCensus);

        HashMap rsMap = new HashMap<>();

        rsMap.put("activity", activityCensus);
        rsMap.put("wish", wishCensus);

        return ResponseData.success(rsMap);
    }
    // 个人数据图表
    @ResponseBody
    @RequestMapping("/get/account_chart")
    public ResponseData accountChart() {
        long userId = ShiroKit.getUser().getId();

        if ( userId == 0 ) {
            return ResponseData.error("用户没有权限");
        }
        Census paramCensus = new Census();
        String month = DateUtils.getMonth();
        String year = DateUtils.getYear();

        paramCensus.setUserId(userId);
        paramCensus.setMonth(month);
        paramCensus.setYear(year);
        List<Census> activityList = mineService.selectUserActivityCensusChartByMonth(paramCensus);
        List<Census> wishyList = mineService.selectUserWishCensusChartByMonth(paramCensus);


        int[] activityData = {0,0,0,0,0,0,0,0,0,0,0,0};
        int[] wishData = {0,0,0,0,0,0,0,0,0,0,0,0};

        if (null != activityList) {
            for (Census census :activityList){
                String monthStr = census.getMonth();
                int monthInt = Integer.valueOf(monthStr);
                activityData [monthInt -1] = census.getMonthCount();
            }
        }

        if (null != wishyList) {
            for (Census census :wishyList){
                String monthStr = census.getMonth();
                int monthInt = Integer.valueOf(monthStr);
                wishData [monthInt -1] = census.getMonthCount();
            }
        }
        HashMap rsMap = new HashMap<>();

        rsMap.put("activityData", activityData);
        rsMap.put("wishData", wishData);

        return ResponseData.success(rsMap);
    }
    /***
     * 社区
     *
     * 此数据包含  （页面从上至下 ）
     *  1、 党支部信息 -  名称（电话）  头像 、 所在组
     *  2、 党支部积分
     *  3、 排行
     * @param communityId
     * @return
     */
    @ResponseBody
    @RequestMapping("/get/community_data")
    public ResponseData getCommunityData(@RequestParam(value = "communityId", required = true) String communityId) {
        System.out.println("userId:"+communityId);

        if (  TextUtils.isEmpty(communityId) ) {
            return ResponseData.error("用户没有权限");
        }

        MineCommunity paramCommunity = new MineCommunity();
        paramCommunity.setCommunityId(communityId);
        // 查询用户信息
        MineCommunity communityInfo = mineService.selectBaseCommunityInfo(paramCommunity);



        return ResponseData.success(communityInfo);
    }


    @ResponseBody
    @RequestMapping("/get/community_census")
    public ResponseData getCommunityCensus(@RequestParam(value = "communityId", required = true) String communityId) {
        System.out.println("userId:"+communityId);

        if (  TextUtils.isEmpty(communityId) ) {
            return ResponseData.error("用户没有权限");
        }
//
//        MineCommunity paramCommunity = new MineCommunity();
//        paramCommunity.setCommunityId(communityId);
//        // 查询用户信息
//        MineCommunity communityInfo = mineService.selectBaseCommunityInfo(paramCommunity);
        String month = DateUtils.getMonth();
        String year = DateUtils.getYear();
        Census paramCensus = new Census();
        paramCensus.setCommunityId(communityId);
        paramCensus.setMonth(month);
        paramCensus.setYear(year);

        Census activityCensus = mineService.selectCommunityActivityScore(paramCensus);


        Census activityPerson = mineService.selectCommunityActivityCensusPerson(paramCensus);

        if (null == activityCensus){
            activityCensus = new Census();
        }
        if (null == activityPerson){
            activityPerson = new Census();
            activityPerson.setC(0);
        }
        activityCensus.setMonthScore(activityPerson.getMonthCount());
        activityCensus.setYearScore(activityPerson.getYearCount());
        activityCensus.setScore(activityPerson.getC());

        Census wishCensus = mineService.selectCommunityWishScore(paramCensus);
        Census wishPerson = mineService.selectCommunityWishCensusPerson(paramCensus);


        if (null == wishCensus){
            wishCensus = new Census();
        }
        if (null == wishPerson){
            wishPerson = new Census();
            wishPerson.setC(0);
        }
        wishCensus.setMonthScore(wishPerson.getMonthCount());
        wishCensus.setYearScore(wishPerson.getYearCount());
        wishCensus.setScore(wishPerson.getC());
        HashMap rsMap = new HashMap();
        rsMap.put("activity", activityCensus);
        rsMap.put("wish", wishCensus);



        return ResponseData.success(rsMap);
    }


    @ResponseBody
    @RequestMapping("/get/community_chart")
    public ResponseData getCommunityChart(@RequestParam(value = "communityId", required = true) String communityId) {
        System.out.println("userId:"+communityId);

        if (  TextUtils.isEmpty(communityId) ) {
            return ResponseData.error("用户没有权限");
        }

        Census paramCensus = new Census();
        String month = DateUtils.getMonth();
        String year = DateUtils.getYear();

        paramCensus.setCommunityId(communityId);
        paramCensus.setMonth(month);
        paramCensus.setYear(year);
        List<Census> activityList = mineService.selectCommunityActivityCensusChartByMonth(paramCensus);
        List<Census> wishyList = mineService.selectCommunityWishCensusChartByMonth(paramCensus);


        int[] activityData = {0,0,0,0,0,0,0,0,0,0,0,0};
        int[] wishData = {0,0,0,0,0,0,0,0,0,0,0,0};

        if (null != activityList) {
            for (Census census :activityList){
                String monthStr = census.getMonth();
                int monthInt = Integer.valueOf(monthStr);
                activityData [monthInt -1] = census.getMonthCount();
            }
        }

        if (null != wishyList) {
            for (Census census :wishyList){
                String monthStr = census.getMonth();
                int monthInt = Integer.valueOf(monthStr);
                wishData [monthInt -1] = census.getMonthCount();
            }
        }
        HashMap rsMap = new HashMap<>();

        rsMap.put("activityData", activityData);
        rsMap.put("wishData", wishData);



        return ResponseData.success(rsMap);
    }

    /***
     * 党支部
     *
     * 此数据包含  （页面从上至下 ）
     *  1、 党支部信息 -  名称（电话）  头像 、 所在组
     *  2、 党支部积分
     *  3、 排行
     * @param branchId
     * @return
     */
    @ResponseBody
    @RequestMapping("/get/branch_data")
    public ResponseData getBranchData(@RequestParam(value = "branchId", required = true) String branchId
                                      ) {

        long userId = ShiroKit.getUser().getId();

        if (  TextUtils.isEmpty(branchId) ) {
            return ResponseData.error("用户没有权限");
        }

        MineBranch paramBranch = new MineBranch();
        paramBranch.setBranchId(branchId);
        // 查询用户信息
        MineBranch branchInfo = mineService.selectBranchInfo(paramBranch);

        Census paramCensus = new Census();
        paramCensus.setBranchId(branchId);

        String month = DateUtils.getMonth();
        String year = DateUtils.getYear();
        paramCensus.setMonth(month);
        paramCensus.setYear(year);
        HashMap rsMap = new HashMap();
//
//        List<Census> memberRankList = mineService.selectBranchMemberRank(paramCensus);
//
//
//        List<Census> branchRankList = mineService.selectBranchRank(paramCensus);
//
//        HashMap rsMap = new HashMap();
//
//
//        CensusUtils.dsort(memberRankList, time);
//        // 人员排行 所在名次
//        int  memberRank = 1;
//        int  branchRank = 1;
//        int i = 1;
//        if (null != memberRankList ){
//
//            for (Census rankCensus : memberRankList){
//
//                if ( rankCensus.getUserId() == userId ){
//                    memberRank = i;
//                    break;
//                }
//                i ++;
//            }
//        }
//
//        CensusUtils.dsort(branchRankList, time);
//
//        // 支部排行 所在名次
//        int j = 1;
//        if (null != branchRankList ){
//
//            for (Census rankCensus : branchRankList){
//
//                if ( TextUtils.equals(rankCensus.getBranchId(), branchId )){
//                    branchRank = j;
//                    break;
//                }
//                j ++;
//            }
//        }
        rsMap.put("branchInfo", branchInfo);
        return ResponseData.success(rsMap);
    }

    /**
     * 支部内 所有党员信息
     */
    @ResponseBody
    @RequestMapping("/get/branch_member")
    public ResponseData getBranchMember(@RequestParam(value = "branchId", required = true) String branchId) {
        if (  TextUtils.isEmpty(branchId) ) {
            return ResponseData.error("用户没有权限");
        }
        Census paramCensus = new Census();
        paramCensus.setBranchId(branchId);

        List<Census> list = mineService.selectBranchMemberList(paramCensus);

        HashMap rsMap = new HashMap();
        rsMap.put("list", list);

        return ResponseData.success(rsMap);
//        ResponseData responseData = new ResponseData();
//        responseData.setCode(0);
//        responseData.setData(list);
//        return responseData;
    }


    /***
     * 党支部 内部排行
     *
     * 此数据包含  （页面从上至下 ）
     *  2、 党支部积分
     *  3、 排行
     * @param branchId
     * @return
     */ @ResponseBody
    @RequestMapping("/get/branch_member_rank")
    public ResponseData getBranchMemberRank(@RequestParam(value = "branchId", required = true) String branchId ,
                                            @RequestParam(value = "time", required = true) String time,
                                            @RequestParam(value = "sort", required = false) String sort ) {


        if (  TextUtils.isEmpty(branchId) ) {
            return ResponseData.error("用户没有权限");
        }

        Census paramCensus = new Census();
        paramCensus.setBranchId(branchId);


        String month = DateUtils.getMonth();
        String year = DateUtils.getYear();
        paramCensus.setMonth(month);
        paramCensus.setYear(year);

        List<Census>  memberRank = mineService.selectBranchMemberRank(paramCensus);


        if (TextUtils.equals("asc", sort)){
            CensusUtils.asort(memberRank, time);
        } else {

            CensusUtils.dsort(memberRank, time);
        }



        HashMap rsMap = new HashMap();
        rsMap.put("list", memberRank);
//
//        ResponseData responseData = new ResponseData();
//        responseData.setCode(0);
//        responseData.setData(memberRank);
        return ResponseData.success(rsMap);
    }

    @ResponseBody
//    @RequestMapping("/get/branch_rank")
    public ResponseData getBranchRank(@RequestParam(value = "branchId", required = true) String branchId) {


        if (  TextUtils.isEmpty(branchId) ) {
            return ResponseData.error("用户没有权限");
        }

//        MineBranch paramBranch = new MineBranch();
//        paramBranch.setBranchId(branchId);
//        // 查询用户信息
//        MineBranch branchInfo = mineService.selectBranchInfo(paramBranch);
        Census paramCensus = new Census();
        paramCensus.setBranchId(branchId);
        List<Census>  memberRank = mineService.selectBranchMemberRank(paramCensus);


        List<Census>  brankRank = mineService.selectBranchRank(paramCensus);

        HashMap rsMap = new HashMap();
        rsMap.put("member", memberRank);
        rsMap.put("brank", brankRank);

        return ResponseData.success(rsMap);
    }

    @ResponseBody
    @RequestMapping("/get/branch_chart")
    public ResponseData getBranchChart(@RequestParam(value = "branchId", required = true) String branchId) {
        Census paramCensus = new Census();
        String month = DateUtils.getMonth();
        String year = DateUtils.getYear();

        paramCensus.setBranchId(branchId);
        paramCensus.setMonth(month);
        paramCensus.setYear(year);
        List<Census> activityList = mineService.selectBranchActivityCensusChartByMonth(paramCensus);
        List<Census> wishyList = mineService.selectBranchWishCensusChartByMonth(paramCensus);


        int[] activityData = {0,0,0,0,0,0,0,0,0,0,0,0};
        int[] wishData = {0,0,0,0,0,0,0,0,0,0,0,0};

        if (null != activityList) {
            for (Census census :activityList){
                String monthStr = census.getMonth();
                int monthInt = Integer.valueOf(monthStr);
                activityData [monthInt -1] = census.getMonthCount();
            }
        }

        if (null != wishyList) {
            for (Census census :wishyList){
                String monthStr = census.getMonth();
                int monthInt = Integer.valueOf(monthStr);
                wishData [monthInt -1] = census.getMonthCount();
            }
        }
        HashMap rsMap = new HashMap<>();

        rsMap.put("activityData", activityData);
        rsMap.put("wishData", wishData);


        return ResponseData.success(rsMap);
    }

    @ResponseBody
    @RequestMapping("/get/branch_census")
    public ResponseData getbranchCensus(@RequestParam(value = "branchId", required = true) String branchId) {
        System.out.println("userId:"+branchId);

        if (  TextUtils.isEmpty(branchId) ) {
            return ResponseData.error("用户没有权限");
        }
//
//        MineCommunity paramCommunity = new MineCommunity();
//        paramCommunity.setCommunityId(communityId);
//        // 查询用户信息
//        MineCommunity communityInfo = mineService.selectBaseCommunityInfo(paramCommunity);
        String month = DateUtils.getMonth();
        String year = DateUtils.getYear();
        Census paramCensus = new Census();
        paramCensus.setBranchId(branchId);
        paramCensus.setMonth(month);
        paramCensus.setYear(year);

        HashMap rsMap = new HashMap();
        Census branchScore = mineService.selectBranchScore(paramCensus);

        rsMap.put("branchScore", branchScore);
        return ResponseData.success(rsMap);
    }
    /***
     * 党委
     *
     * 此数据包含  （页面从上至下 ）
     *  1、 党支部信息 -  名称（电话）  头像 、 所在组
     *  2、 党支部积分
     *  3、 排行
     * @param committeeId
     * @return
     */
    @ResponseBody
    @RequestMapping("/get/committee_data")
    public ResponseData getCommitteeData(@RequestParam(value = "committeeId", required = true) String committeeId) {
        System.out.println("userId:"+committeeId);

        if ( TextUtils.isEmpty(committeeId)) {
            return ResponseData.error("用户没有权限");
        }

        MineCommittee paramCommittee = new MineCommittee();
        paramCommittee.setCommitteeId(committeeId);
        // 查询用户信息
        MineCommittee committeeInfo = mineService.selectCommitteeInfo(paramCommittee);

        HashMap rsMap = new HashMap();
        rsMap.put("committeeInfo", committeeInfo);

        return ResponseData.success(rsMap);
    }

    @ResponseBody
    @RequestMapping("/get/committee_branch_list_data")
    public ResponseData getCommitteeBranchListData(@RequestParam(value = "committeeId", required = true) String committeeId) {
        System.out.println("userId:"+committeeId);

        if ( TextUtils.isEmpty(committeeId)) {
            return ResponseData.error("用户没有权限");
        }
        Census paramCensus = new Census();
        String month = DateUtils.getMonth();
        String year = DateUtils.getYear();

        paramCensus.setCommitteeId(committeeId);
        paramCensus.setMonth(month);
        paramCensus.setYear(year);
        List<Census> list = mineService.selectBranchList(paramCensus);

        HashMap rsMap = new HashMap();
        rsMap.put("list", list);

        return ResponseData.success(rsMap);
    }

    /***
     * 党支部 内部排行
     *
     * 此数据包含  （页面从上至下 ）
     *  2、 党支部积分
     *  3、 排行
     * @param
     * @return
     */
    @ResponseBody
    @RequestMapping("/get/committee_branch_rank")
    public ResponseData getCommitteeBranchRank(@RequestParam(value = "committeeId", required = false) String committeeId,
                                               @RequestParam(value = "time", required = true) String time,
                                               @RequestParam(value = "sort", required = false) String sort) {


        Census paramCensus = new Census();
        String month = DateUtils.getMonth();
        String year = DateUtils.getYear();
        String orderby = "";
        if (TextUtils.equals("asc", sort)){
            orderby = " score asc, spb.code+0 ";
        } else {
            orderby = " score desc, spb.code+0  ";
        }
        paramCensus.setOrderby(orderby);



        if (TextUtils.equals("month", time)){
            paramCensus.setMonth(month);
        } else if (TextUtils.equals("year", time)){
            paramCensus.setYear(year);
        }

        List<Census>  brankRank = mineService.selectBranchRankSingle(paramCensus);

        HashMap rsMap = new HashMap();
//        rsMap.put("member", memberRank);
        rsMap.put("brank", brankRank);

        return ResponseData.success(rsMap);
    }


    /**
     * 全区 数据统计
     * @return
     */
    @ResponseBody
    @RequestMapping("/get/all_census")
    public ResponseData getAllCensus() {
        long userId = ShiroKit.getUser().getId();

        if ( userId == 0 ) {
            return ResponseData.error("用户没有权限");
        }
        Census paramCensus = new Census();
        String month = DateUtils.getMonth();
        String year = DateUtils.getYear();

        paramCensus.setMonth(month);
        paramCensus.setYear(year);
        Census activityCensus = mineService.selectActivityAllCensusCommunity(paramCensus);
        Census activityJoinCensus = mineService.selectActivityAllCensusPerson(paramCensus);

        activityCensus.setMonthScore(activityJoinCensus.getMonthCount());
        activityCensus.setYearScore(activityJoinCensus.getYearCount());
        activityCensus.setScore(activityJoinCensus.getC());

        Census wishCensus = mineService.selectWishAllCensusCommunity(paramCensus);
        Census wishJoinCensus = mineService.selectWishAllCensusPerson(paramCensus);

        wishCensus.setMonthScore(wishJoinCensus.getMonthCount());
        wishCensus.setYearScore(wishJoinCensus.getYearCount());
        wishCensus.setScore(wishJoinCensus.getC());


        HashMap rsMap = new HashMap<>();

        rsMap.put("activity", activityCensus);
        rsMap.put("wish", wishCensus);

        return ResponseData.success(rsMap);
    }

    // 全区图表数据
    @ResponseBody
    @RequestMapping("/get/all_chart")
    public ResponseData allChart() {

        Census paramCensus = new Census();
        String month = DateUtils.getMonth();
        String year = DateUtils.getYear();

        paramCensus.setMonth(month);
        paramCensus.setYear(year);
        List<Census> activityList = mineService.selectUserActivityCensusChartByMonth(paramCensus);
        List<Census> wishyList = mineService.selectUserWishCensusChartByMonth(paramCensus);


        int[] activityData = {0,0,0,0,0,0,0,0,0,0,0,0};
        int[] wishData = {0,0,0,0,0,0,0,0,0,0,0,0};

        if (null != activityList) {
            for (Census census :activityList){
                String monthStr = census.getMonth();
                int monthInt = Integer.valueOf(monthStr);
                activityData [monthInt -1] = census.getMonthCount();
            }
        }

        if (null != wishyList) {
            for (Census census :wishyList){
                String monthStr = census.getMonth();
                int monthInt = Integer.valueOf(monthStr);
                wishData [monthInt -1] = census.getMonthCount();
            }
        }
        HashMap rsMap = new HashMap<>();

        rsMap.put("activityData", activityData);
        rsMap.put("wishData", wishData);

        return ResponseData.success(rsMap);
    }

    /***
     * @param
     * @return
     */
    @ResponseBody
    @RequestMapping("/get/all_branch_rank")
    public ResponseData getAllBranchRank(@RequestParam(value = "committeeId", required = false) String committeeId,
                                         @RequestParam(value = "time", required = true) String time,
                                         @RequestParam(value = "sort", required = false) String sort) {


        Census paramCensus = new Census();
        String month = DateUtils.getMonth();
        String year = DateUtils.getYear();
        String orderby = "";
        if (TextUtils.equals("asc", sort)){
            orderby = " score asc, spb.code+0 ";
        } else {
            orderby = " score desc, spb.code+0  ";
        }
        paramCensus.setOrderby(orderby);



        if (TextUtils.equals("month", time)){
            paramCensus.setMonth(month);
        } else if (TextUtils.equals("year", time)){
            paramCensus.setYear(year);
        }

        List<Census>  brankRank = mineService.selectBranchRankSingle(paramCensus);


        HashMap rsMap = new HashMap();
        rsMap.put("list", brankRank);

        return ResponseData.success(rsMap);
    }


    /***
     * @param
     * @return
     */
    @ResponseBody
//    @RequestMapping("/get/all_member_rank")
    public ResponseData getAllMemberRank(@RequestParam(value = "time", required = true) String time,
                                         @RequestParam(value = "sort", required = false) String sort) {

        Census paramCensus = new Census();
        String month = DateUtils.getMonth();
        String year = DateUtils.getYear();
        paramCensus.setMonth(month);
        paramCensus.setYear(year);

        List<Census> memberRankList = mineService.selectMemberRank(paramCensus);

        if (TextUtils.equals("asc", sort)){
            CensusUtils.asort(memberRankList, time);
        } else {

            CensusUtils.dsort(memberRankList, time);
        }


        HashMap rsMap = new HashMap();
        rsMap.put("list", memberRankList);

        return ResponseData.success(rsMap);
    }


    @ResponseBody
    @RequestMapping("/get/all_member_rank_single")
    public ResponseData getAllMemberRankSingle(@RequestParam(value = "time", required = true) String time,
                                         @RequestParam(value = "sort", required = false) String sort) {

        Census paramCensus = new Census();
        String month = DateUtils.getMonth();
        String year = DateUtils.getYear();
        String orderby = "";
        if (TextUtils.equals("asc", sort)){
            orderby = " score asc, name";
        } else {
            orderby = " score desc, name ";
        }
        paramCensus.setOrderby(orderby);



        if (TextUtils.equals("month", time)){
            paramCensus.setMonth(month);
        } else if (TextUtils.equals("year", time)){
            paramCensus.setYear(year);
        }



        List<Census> memberRankList = mineService.selectMemberRankSingle(paramCensus);

        HashMap rsMap = new HashMap();
        rsMap.put("list", memberRankList);

        return ResponseData.success(rsMap);
    }



    @RequestMapping("/mine/getAvatar")
    @ResponseBody
    public Object previewAvatar(@RequestParam(value = "avatar", required = false) String avatar   ,HttpServletResponse response) {
//        String path = ClassUtils.getDefaultClassLoader().getResource("").getPath();
        String path = null;
        try {
            path = ResourceUtils.getURL("classpath:").getPath();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // 获取路径
        String filepath = "\\images\\default-images-3.png";

        filepath = filepath.substring(1);



        if (TextUtils.isNotEmpty(avatar)){
            FileInfo fileInfo = this.mineService.getAvatarFileById(avatar);
            if (null != fileInfo) {
                filepath = fileInfo.getFilePath();
            }
        }

        File nf = new File(filepath);
        System.out.println(filepath);;
        System.out.println(nf.isFile());
        System.out.println(nf.exists());
        System.out.println(nf.getAbsolutePath());
        //输出图片的文件流
        try {
            response.setContentType("image/jpeg");
            byte[] decode = AvatarUtils.getAvatarImageFromFilePath(filepath);
            if (null != decode){
                response.getOutputStream().write(decode);
            }

        } catch (IOException e) {
            throw new ServiceException(CoreExceptionEnum.SERVICE_ERROR);
        }

        return null;
    }

    @RequestMapping("/get/contact")
    @ResponseBody
    public ResponseData getContactor(@RequestParam(value = "branchId", required = true) String branchId   ,
                                     @RequestParam(value = "contactType", required = true) String contactType   ) {
        Census paramCensus = new Census();

        if (TextUtils.equals(contactType, "branch")){
            paramCensus.setBranchId(branchId);
        } else if (TextUtils.equals(contactType, "committee")){
            paramCensus.setCommitteeId(branchId);
        } else if (TextUtils.equals(contactType, "community")){
            paramCensus.setCommunityId(branchId);
        } else if (TextUtils.equals(contactType, "store")){
            paramCensus.setStoreId(branchId);
        }


        List<Census>  userList = mineService.selectUserContactList(paramCensus);
        HashMap rsMap = new HashMap();
        rsMap.put("list", userList);

        return ResponseData.success(rsMap);
    }


    @RequestMapping("/get/doubleRelationBranchCommunity")
    @ResponseBody
    public ResponseData getDoubleRelationBranchCommunity(@RequestParam(value = "branchId", required = true) String branchId ){
        if (TextUtils.isEmpty(branchId)){
            return ResponseData.error("参数错误");
        }

        Census paramCensus = new Census();

        paramCensus.setBranchId(branchId);

        List<Census> communityList = mineService.selectRelationBranchAndCommunityByBranchId(paramCensus);

        //** 对数据进行整理

        List<MineDoubleRelation> rsList = new ArrayList<>();

        HashMap<String, MineDoubleRelation> tempMap = new HashMap<String, MineDoubleRelation>();
        if (null != communityList){
            for (Census census: communityList){
                String communityId = census.getCommunityId();
                String communityName = census.getCommunityName();

                String userName = census.getUserName();

                String phone = census.getPhone();


                MineDoubleRelation userDoubleRelation = new MineDoubleRelation();
                userDoubleRelation.setUserName(userName);
                userDoubleRelation.setPhone(phone);


                MineDoubleRelation community = tempMap.get(communityId);

                if (community == null){
                    community = new MineDoubleRelation();
                    community.setCommunityId(communityId);
                    community.setCommunityName(communityName);
                    community.setUserList(new ArrayList<>());
                }
                List<MineDoubleRelation> userList = community.getUserList();


                if (userList == null ){
                    userList = new ArrayList<>();
                }

                userList.add(userDoubleRelation);


                community.setUserList(userList);


                tempMap.put(communityId, community);
            }
        }

        if (tempMap.size() > 0){
            for (Map.Entry entry : tempMap.entrySet()){
                rsList.add( (MineDoubleRelation)entry.getValue());
            }
        }

        HashMap rsMap = new HashMap();
        rsMap.put("list", rsList);

        return ResponseData.success(rsMap);
    }


    @RequestMapping("/get/doubleRelation")
    @ResponseBody
    public ResponseData getDoubleRelation(){
        List<Census> branchList = mineService.selectRelationBranch();
        List<Census> communityList = mineService.selectRelationCommunity();
        List<Census> branchAndCommunity = mineService.selectRelationBranchAndCommunity();


        // 对数据进行整理
        // 查询的 支部 和社区 都是多条 对应多个联系人
        // 整理成  list(联系人) 的 1：N 形式

        List<MineDoubleRelation> rsList = new ArrayList<>();


        HashMap<String, MineDoubleRelation> tempBranchMap = new HashMap<String, MineDoubleRelation>();
        if (null != branchList){

            for (Census census:branchList) {

                MineDoubleRelation  mineDoubleRelation = null;


                MineDoubleRelation  userDoubleRelation = new MineDoubleRelation();
                String branchId = census.getBranchId();
                String branchName = census.getBranchName();
                int personCount = census.getC();

                String userName = census.getUserName();
                String phone = census.getPhone();


                userDoubleRelation.setUserName(userName);
                userDoubleRelation.setPhone(phone);

                if (! tempBranchMap.containsKey(branchId)) {

                }

                mineDoubleRelation = tempBranchMap.get(branchId);

                if (null == mineDoubleRelation) {
                    mineDoubleRelation = new MineDoubleRelation();
                    mineDoubleRelation.setBranchId(branchId);
                    mineDoubleRelation.setBranchName(branchName);
                    mineDoubleRelation.setPersonCount(personCount);
                }

                List<MineDoubleRelation> userList = mineDoubleRelation.getUserList();

                if (userList == null ){
                    userList = new ArrayList<>();
                }

                userList.add(userDoubleRelation);


                mineDoubleRelation.setUserList(userList);

                tempBranchMap.put(branchId, mineDoubleRelation);
            }
        }
//        System.out.println("整理完成的 branchMap");
//        System.out.println(tempBranchMap);
        // 社区整理成 hashmap ( "社区ID", 社区信息+list<联系人>)格式


        HashMap<String, MineDoubleRelation> tempCommunityMap = new HashMap<String, MineDoubleRelation>();
        if (null != communityList){

            for (Census census:communityList) {

                MineDoubleRelation  mineDoubleRelation = null;


                MineDoubleRelation  userDoubleRelation = new MineDoubleRelation();
                String communityId = census.getCommunityId();
                String communityName = census.getCommunityName();


                String userName = census.getUserName();
                String phone = census.getPhone();


                userDoubleRelation.setUserName(userName);
                userDoubleRelation.setPhone(phone);



                mineDoubleRelation = tempCommunityMap.get(communityId);

                if (null == mineDoubleRelation) {
                    mineDoubleRelation = new MineDoubleRelation();
                    mineDoubleRelation.setCommunityId(communityId);
                    mineDoubleRelation.setCommunityName(communityName);
                }

                List<MineDoubleRelation> userList = mineDoubleRelation.getUserList();

                if (userList == null ){
                    userList = new ArrayList<>();
                }

                userList.add(userDoubleRelation);


                mineDoubleRelation.setUserList(userList);


                tempCommunityMap.put(communityId, mineDoubleRelation);
            }
        }


        // 循环 支部信息 （以支部为基础单位 ，查找对应数据关系）
        if (tempBranchMap.size() > 0) {
            for (Map.Entry entry : tempBranchMap.entrySet()){

                String branchId = (String) entry.getKey();

                MineDoubleRelation mineDoubleRelation = (MineDoubleRelation) entry.getValue();

                if (null != branchAndCommunity ){
                    for (Census census: branchAndCommunity) {
                        String censusBranchId = census.getBranchId();
                        String censusCommunityId = census.getCommunityId();

                        if ( TextUtils.equals(branchId, censusBranchId)) {
                            List<MineDoubleRelation> childlist = mineDoubleRelation.getCommunityList();

                            if (childlist == null) {
                                childlist = new ArrayList<>();
                            }
                            MineDoubleRelation communityRelation = tempCommunityMap.get(censusCommunityId);

                            childlist.add(communityRelation);

                            mineDoubleRelation.setCommunityList(childlist);
                        }
                    }
                }


                rsList.add(mineDoubleRelation);

            }
        }


        return ResponseData.success(rsList);

    }



}
