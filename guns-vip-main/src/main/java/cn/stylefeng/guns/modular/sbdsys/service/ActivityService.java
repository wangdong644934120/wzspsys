package cn.stylefeng.guns.modular.sbdsys.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.base.shiro.ShiroUser;
import cn.stylefeng.guns.modular.sbdsys.entity.Activity;
import cn.stylefeng.guns.modular.sbdsys.entity.ActivityRecord;
import cn.stylefeng.guns.modular.sbdsys.entity.ActivitySumup;
import cn.stylefeng.guns.modular.sbdsys.mapper.ActivityMapper;
import cn.stylefeng.guns.modular.sbdsys.mapper.ActivitySumUpMapper;
import cn.stylefeng.guns.modular.sbdsys.model.params.ActivityParam;
import cn.stylefeng.guns.modular.sbdsys.model.params.ActivityRecordParam;
import cn.stylefeng.guns.modular.sbdsys.model.params.CoinRecordParam;
import cn.stylefeng.guns.modular.sbdsys.model.result.ActivityResult;
import cn.stylefeng.guns.sys.core.shiro.ShiroKit;
import cn.stylefeng.roses.core.reqres.response.ResponseData;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 活动表 服务实现类
 * </p>
 *
 * @author Admin
 * @since 2019-09-06
 */
@Service
public class ActivityService extends ServiceImpl<ActivityMapper, Activity> {

    @Autowired
    private AccountService accountService;
    @Autowired
    private ActivityRecordService activityRecordService;
    @Autowired
    private CoinRecordService coinRecordService;

    @Resource
    private ActivitySumUpMapper activitySumUpMapper;

    /**
     * 新增
     *
     * @author Admin
     * @Date 2019-09-06
     */
    public void add(ActivityParam param) {
        this.save(param);
    }

    /**
     * 删除
     *
     * @author Admin
     * @Date 2019-09-06
     */
    public void delete(ActivityParam param) {
        this.removeById(getKey(param));
    }

    /**
     * 更新
     *
     * @author Admin
     * @Date 2019-09-06
     */
    public void update(ActivityParam param) {
//        Activity oldEntity = this.getById(getKey(param));
//        ToolUtil.copyProperties(param, oldEntity);
        this.updateById(param);
    }

    /**
     * 查询单条数据，Specification模式
     *
     * @author Admin
     * @Date 2019-09-06
     */
    public ActivityResult findBySpec(ActivityParam param) {
        return null;
    }

    /**
     * 查询列表，Specification模式
     *
     * @author Admin
     * @Date 2019-09-06
     */
    public List<ActivityResult> findListBySpec(ActivityParam param) {
        return null;
    }

    /**
     * 查询分页数据，Specification模式
     *
     * @author Admin
     * @Date 2019-09-06
     */
    public LayuiPageInfo findPageBySpec(ActivityParam param) {
        Page pageContext = LayuiPageFactory.defaultPage();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    /**
     * @Description 可参加活动列表
     * @Author lichenfeng
     * @Date 2019/9/10 14:40
     **/
    public LayuiPageInfo getJoinListByUser() {
        Page pageContext = LayuiPageFactory.defaultPage();
        IPage page = this.baseMapper.getJoinListByUser(pageContext, ShiroKit.getUser().getId());
        return LayuiPageFactory.createPageInfo(page);
    }

    /**
     * @Description 已参加活动列表
     * @Author lichenfeng
     * @Date 2019/9/10 15:16
     **/
    public LayuiPageInfo getDoneListByUser() {
        Page pageContext = LayuiPageFactory.defaultPage();
        IPage page = this.baseMapper.getDoneListByUser(pageContext, ShiroKit.getUser().getId());
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(ActivityParam param) {
        return param.getId();
    }


    public ActivityResult selectByPrimaryId(String id) {
        return this.baseMapper.selectByPrimaryId(id);
    }

    /**
     * @Description 处理扫码
     * @Author lichenfeng
     * @Date 2019/9/10 13:46
     **/
    @Transactional(rollbackFor = Exception.class)
    public ResponseData dealScan(String activityId, String coordinate) {
        //扫码，如下规则
        //1.0当前人员必须为党员
        //2.0活动已大于开始时间
        //3.0活动未结束
        //4.0未参加过该活动
        ActivityResult detail = this.selectByPrimaryId(activityId);
        if (detail == null) {
            return ResponseData.error(400, "无法识别的二维码");
        }
        ShiroUser user = ShiroKit.getUser();
        boolean hasPower = false;
        for (String roleName : user.getRoleNames()) {
            if (roleName.indexOf("党员") >= 0) {
                hasPower = true;
                break;
            }
        }
        if (!hasPower) {
            return ResponseData.error(400, "您在系统的身份不是党员，无法参与活动");
        }
//        if(new Date().compareTo(detail.getStartTime())<0){
//            return ResponseData.error(400,"活动仍未开始，无法参与活动");
//        }
        if (detail.getState() == 2) {
            return ResponseData.error(400, "活动已结束，无法参与活动");
        }
        List<ActivityRecord> records = activityRecordService.list(new QueryWrapper<ActivityRecord>()
                .eq("activity_id", activityId)
                .eq("user_id", user.getId()));
        if (records != null && !records.isEmpty()) {
            return ResponseData.error(400, "您已参加该活动");
        }
        //1.0添加参与活动记录
        ActivityRecordParam param = new ActivityRecordParam();
        param.setId(IdWorker.get32UUID());
        param.setUserId(user.getId());
        param.setActivityId(activityId);
        if (StringUtils.isNotEmpty(coordinate)) {
            param.setLongitude(coordinate.split(",")[0]);
            param.setLatitude(coordinate.split(",")[1]);
        }
        param.setActionTime(new Date());
        param.setScore(detail.getScore());
        this.activityRecordService.add(param);
        //2.0调用增加积分、先锋币接口
        this.accountService.addScoreAccount(user.getId(), detail.getScore(), 0);
        //3.0添加先锋币收支记录
        CoinRecordParam coinRecordParam = new CoinRecordParam();
        coinRecordParam.setId(IdWorker.get32UUID());
        coinRecordParam.setChangeType(0);
        coinRecordParam.setUserId(user.getId());
        coinRecordParam.setScore(detail.getScore());
        coinRecordParam.setCoin(detail.getScore());
        coinRecordParam.setChangeDesc("增加先锋币：" + detail.getScore() + "，参与活动：" + detail.getTitle());
        this.coinRecordService.add(coinRecordParam);
        return ResponseData.success();
    }


    /*********
     *  活动总结 部分
     *
     */

    public ActivitySumup selectActivitySumUpExists(ActivitySumup activitySumup) {
        return activitySumUpMapper.selectActivitySumUpExists(activitySumup);
    }

    public int insertActivitySumUp(ActivitySumup activitySumup) {
        return activitySumUpMapper.insertActivitySumUp(activitySumup);
    }


    public int updateActivitySumUp(ActivitySumup activitySumup) {
        return activitySumUpMapper.updateActivitySumUp(activitySumup);
    }

    public int selectActivitySumUpDetail(ActivitySumup activitySumup) {
        return activitySumUpMapper.updateActivitySumUp(activitySumup);
    }


    public ActivitySumup selectActivityDetailWithSumUp(ActivitySumup activitySumup) {
        return activitySumUpMapper.selectActivityDetailWithSumUp(activitySumup);
    }

    /**
     * @Description 获取党员的报道社区ID列表
     * @Author lichenfeng
     * @Date 2019/10/11 16:38
     **/
    public List<String> getBranchCommunityIds(Long userId){
        return this.baseMapper.getBranchCommunityIds(userId);
    }
}