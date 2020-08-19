package cn.stylefeng.guns.modular.sbdsys.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.base.shiro.ShiroUser;
import cn.stylefeng.guns.modular.sbdsys.entity.Wish;
import cn.stylefeng.guns.modular.sbdsys.entity.WishRecord;
import cn.stylefeng.guns.modular.sbdsys.mapper.WishMapper;
import cn.stylefeng.guns.modular.sbdsys.model.params.CoinRecordParam;
import cn.stylefeng.guns.modular.sbdsys.model.params.WishParam;
import cn.stylefeng.guns.modular.sbdsys.model.params.WishRecordParam;
import cn.stylefeng.guns.modular.sbdsys.model.result.WishResult;
import cn.stylefeng.guns.sys.core.shiro.ShiroKit;
import cn.stylefeng.roses.core.reqres.response.ResponseData;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 微心愿表 服务实现类
 * </p>
 *
 * @author Admin
 * @since 2019-09-06
 */
@Service
public class WishService extends ServiceImpl<WishMapper, Wish> {

    @Autowired
    private WishRecordService wishRecordService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private CoinRecordService coinRecordService;
    /**
     * 新增
     *
     * @author Admin
     * @Date 2019-09-06
     */
    public void add(WishParam param){
        this.save(param);
    }
    /**
     * 删除
     *
     * @author Admin
     * @Date 2019-09-06
     */
    public void delete(WishParam param){
        this.removeById(getKey(param));
    }
    /**
     * 更新
     *
     * @author Admin
     * @Date 2019-09-06
     */
    public void update(WishParam param){
        this.updateById(param);
    }
    /**
     * 查询单条数据，Specification模式
     *
     * @author Admin
     * @Date 2019-09-06
     */
    public WishResult findBySpec(WishParam param){
        return null;
    }
    /**
     * 查询列表，Specification模式
     *
     * @author Admin
     * @Date 2019-09-06
     */
    public List<WishResult> findListBySpec(WishParam param){
        return null;
    }
    /**
     * 查询分页数据，Specification模式
     *
     * @author Admin
     * @Date 2019-09-06
     */
    public LayuiPageInfo findPageBySpec(WishParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    public LayuiPageInfo findPageByUser(Long userId,int status){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageListByUser(pageContext, userId,status);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(WishParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    public WishResult selectByPrimaryId(String id){
        return this.baseMapper.selectByPrimaryId(id);
    }

    /**
     * @Description 领取心愿
     * @Author lichenfeng
     * @Date 2019/9/11 11:07
     **/
    @Transactional(rollbackFor = Exception.class)
    public ResponseData dealReceive(String wishId){
        //如下规则
        //1.0当前人员必须为党员
        //2.0活心愿状态必须为1
        WishResult detail = this.selectByPrimaryId(wishId);
        ShiroUser user= ShiroKit.getUser();
        boolean hasPower=false;
        for(String roleName : user.getRoleNames()){
            if(roleName.indexOf("党员")>=0){
                hasPower=true;
                break;
            }
        }
        if(!hasPower){
            return ResponseData.error(400,"您在系统的身份不是党员，无法参与活动");
        }
        if(detail.getStatus()!=WishParam.STATUS_YFB){
            return ResponseData.error(400,"该心愿已被他人领取");
        }
        //1.0添加参与记录
        WishRecordParam param=new WishRecordParam();
        param.setId(IdWorker.get32UUID());
        param.setUserId(user.getId());
        param.setWishId(wishId);
        param.setActionTime(new Date());
        param.setScore(detail.getScore());
        this.wishRecordService.add(param);
        //2.0修改心愿状态
        WishParam wishParam=new WishParam();
        wishParam.setId(wishId);
        wishParam.setStatus(WishParam.STATUS_YLQ);
        this.update(wishParam);
        return ResponseData.success();
    }

    /**
     * @Description 执行心愿
     * @Author lichenfeng
     * @Date 2019/9/11 15:33
     **/
    @Transactional(rollbackFor = Exception.class)
    public ResponseData dealExecute(String wishId,String note){
        //获取参与记录
        List<WishRecord> records=wishRecordService.list(new QueryWrapper<WishRecord>()
                .eq("wish_id", wishId)
                .eq("user_id", ShiroKit.getUser().getId()));
        if(records==null||records.isEmpty()){
            return ResponseData.error(400,"您未领取该心愿，无法执行");
        }
        WishRecord record=records.get(0);
        record.setExecuteTime(new Date());
        record.setNote(note);
        //更新记录note
        wishRecordService.update(record);
        //更新心愿状态
        WishParam wishParam=new WishParam();
        wishParam.setId(wishId);
        wishParam.setStatus(WishParam.STATUS_YZX);
        this.update(wishParam);
        return ResponseData.success();
    }

    /**
     * @Description 处理执行请求
     * @Author lichenfeng
     * @Date 2019/9/12 15:03
     **/
    @Transactional(rollbackFor = Exception.class)
    public ResponseData dealConfirm(String wishId){
        //获取参与记录
        List<WishRecord> records=wishRecordService.list(new QueryWrapper<WishRecord>()
                .eq("wish_id", wishId));
        if(records==null||records.isEmpty()){
            return ResponseData.error(400,"该心愿未被领取，无法执行对应操作");
        }
        WishRecord record=records.get(0);
        Long actionUserId=record.getUserId();
        //1.0更新心愿状态
        WishParam wishParam=new WishParam();
        wishParam.setId(wishId);
        wishParam.setStatus(WishParam.STATUS_YSH);
        wishParam.setUpdateTime(new Date());
        wishParam.setUpdateUser(ShiroKit.getUser().getId());
        this.update(wishParam);
        //2.0调用增加积分、先锋币接口
        this.accountService.addScoreAccount(actionUserId,0,record.getScore());
        //3.0添加先锋币收支记录
        CoinRecordParam coinRecordParam=new CoinRecordParam();
        coinRecordParam.setChangeType(0);
        coinRecordParam.setUserId(actionUserId);
        coinRecordParam.setScore(record.getScore());
        coinRecordParam.setCoin(record.getScore());
        coinRecordParam.setChangeDesc("增加先锋币："+record.getScore()+"，参与微心愿："+this.getById(wishId).getTitle());
        this.coinRecordService.add(coinRecordParam);
        return ResponseData.success();
    }

}
