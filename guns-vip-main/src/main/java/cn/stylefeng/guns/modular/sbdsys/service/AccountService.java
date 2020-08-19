package cn.stylefeng.guns.modular.sbdsys.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.base.shiro.ShiroUser;
import cn.stylefeng.guns.modular.sbdsys.entity.Account;
import cn.stylefeng.guns.modular.sbdsys.mapper.AccountMapper;
import cn.stylefeng.guns.modular.sbdsys.model.params.AccountParam;
import cn.stylefeng.guns.modular.sbdsys.model.result.AccountResult;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 账户表 服务实现类
 * </p>
 *
 * @author Admin
 * @since 2019-09-06
 */
@Service
public class AccountService extends ServiceImpl<AccountMapper, Account> {

    public void delete(AccountParam param){
        this.removeById(getKey(param));
    }

    /**
     * 查询单条数据，Specification模式
     *
     * @author Admin
     * @Date 2019-09-06
     */
    public AccountResult findBySpec(AccountParam param){
        return null;
    }
    /**
     * 查询列表，Specification模式
     *
     * @author Admin
     * @Date 2019-09-06
     */
    public List<AccountResult> findListBySpec(AccountParam param){
        return null;
    }
    /**
     * 查询分页数据，Specification模式
     *
     * @author Admin
     * @Date 2019-09-06
     */
    public LayuiPageInfo findPageBySpec(AccountParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(AccountParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }



    /**
     * @Description 参与活动或微心愿增加积分
     * @Author lichenfeng
     * @Date 2019/9/10 11:22
     **/
    public synchronized void addScoreAccount(long userId,int scoreActivity,int scoreWish){
        Account entity = this.getById(userId);
        if(entity==null){
            entity = new Account();
            entity.setId(userId);
            entity.setScoreActivity(scoreActivity);
            entity.setScoreWish(scoreWish);
            entity.setCoin(scoreActivity+scoreWish);
            this.save(entity);
        }else{
            entity.setScoreActivity(entity.getScoreActivity()+scoreActivity);
            entity.setScoreWish(entity.getScoreWish()+scoreWish);
            entity.setCoin(entity.getCoin()+scoreActivity+scoreWish);
            this.updateById(entity);
        }
    }

}
