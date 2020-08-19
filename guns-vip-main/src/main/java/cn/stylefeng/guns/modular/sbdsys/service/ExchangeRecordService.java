package cn.stylefeng.guns.modular.sbdsys.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.sbdsys.entity.Account;
import cn.stylefeng.guns.modular.sbdsys.entity.CoinRecord;
import cn.stylefeng.guns.modular.sbdsys.entity.Commodity;
import cn.stylefeng.guns.modular.sbdsys.entity.ExchangeRecord;
import cn.stylefeng.guns.modular.sbdsys.mapper.ExchangeRecordMapper;
import cn.stylefeng.guns.modular.sbdsys.model.params.ExchangeRecordParam;
import cn.stylefeng.guns.modular.sbdsys.model.result.ExchangeRecordResult;
import cn.stylefeng.guns.sys.core.exception.enums.BizExceptionEnum;
import cn.stylefeng.guns.sys.core.shiro.ShiroKit;
import cn.stylefeng.roses.core.util.ToolUtil;
import cn.stylefeng.roses.kernel.model.exception.ServiceException;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * <p>
 * 消费记录 服务实现类
 * </p>
 *
 * @author Admin
 * @since 2019-09-06
 */
@Service
public class ExchangeRecordService extends ServiceImpl<ExchangeRecordMapper, ExchangeRecord> {

    /**
     * 新增
     *
     * @author Admin
     * @Date 2019-09-06
     */
    public int add(ExchangeRecordParam param) {
        //获取商品
        Commodity c = this.baseMapper.getCommodityWithTransaction(param.getCommodityId());
        //商品兑换 判断1、用户账户余额是否足够
        Long userId = ShiroKit.getUser().getId();
        param.setUserId(userId);
        //获取用户
        Account account = this.baseMapper.getAccountWithTransaction(userId);
        if (account == null) {
            //账户尚未生成，视为账户余额为0
            //todo 返回账户余额不足
            throw new ServiceException(BizExceptionEnum.BANLANCE_NOT_ENOUGH);
        } else {
            if (account.getCoin() < c.getCostCoin()) {
                //todo  返回账户余额不足
                throw new ServiceException(BizExceptionEnum.BANLANCE_NOT_ENOUGH);
            }
        }
        //2、商品是否有库存、是否可用
        if (c.getSurplus() > 0) {
            //有库存
        } else {
            //todo 库存不足
            throw new ServiceException(BizExceptionEnum.COMMODITY_NOT_ENOUGH);
        }
        Date date = new Date();
        //都没有问题 减库存 减先锋币 添加兑换记录 添加收支记录
        c.setSurplus(1);//在此传输1，表示sql中-1，不代表将数据库数量设置为1；防止多个读造成脏数据
        c.setUpdateTime(date);
        c.setUpdateUser(userId);
        //todo 更新库存
        this.baseMapper.updateCommodity(c);
        account.setCoin(c.getCostCoin());//在此设置为商品消耗先锋币数量，在sql中减去，防止多个读造成脏数据
        //todo 更新账户
        this.baseMapper.updateAccount(account);
        ExchangeRecord er = new ExchangeRecord();
        er.setId(UUID.randomUUID().toString());
        er.setUserId(userId);
        er.setCostCoin(c.getCostCoin());
        er.setCommodityId(c.getId());
        er.setIsExchange(0);
        er.setOrderTime(date);
        er.setCreateTime(date);
        //todo 添加兑换记录
        this.baseMapper.insertExchangeRecord(er);
        CoinRecord cr = new CoinRecord();
        cr.setId(UUID.randomUUID().toString());
        cr.setUserId(userId);
        cr.setChangeType(1);
        cr.setChangeDesc("兑换商品" + c.getName());
        cr.setCreateTime(date);
        //todo 添加收支记录
        this.baseMapper.insertCoinRecord(cr);
        return 1;
    }

    private int insert(ExchangeRecord entity) {
        return 0;
    }

    /**
     * 删除
     *
     * @author Admin
     * @Date 2019-09-06
     */
    public void delete(ExchangeRecordParam param) {
        this.removeById(getKey(param));
    }

    /**
     * 更新
     *
     * @author Admin
     * @Date 2019-09-06
     */
    public void update(ExchangeRecordParam param) {
        ExchangeRecord oldEntity = getOldEntity(param);
        Long userId = ShiroKit.getUser().getId();
        //判断商品是否是自己商铺的
        Commodity  c = this.baseMapper.getCommodityByUserId(oldEntity.getCommodityId(),userId);
        if(c == null){
            throw new ServiceException(BizExceptionEnum.COMMODITY_NOT_STORE);
        }else{
            if(oldEntity.getIsExchange() == 1){
                //已经兑换过，抛出异常
                throw new ServiceException(BizExceptionEnum.COMMODITY_HAS_EXCHANGED);
            }else {
                ExchangeRecord newEntity = getEntity(param);
                ToolUtil.copyProperties(newEntity, oldEntity);
                //扫码确认兑换，更新兑换状态、时间、及兑换人
                newEntity.setIsExchange(1);
                newEntity.setUpdateTime(new Date());
                newEntity.setExchangeTime(new Date());
                newEntity.setExchangeUser(ShiroKit.getUser().getId());
                this.updateById(newEntity);
            }
        }
    }

    /**
     * 查询单条数据，Specification模式
     *
     * @author Admin
     * @Date 2019-09-06
     */
    public ExchangeRecordResult findBySpec(ExchangeRecordParam param) {
        return null;
    }

    /**
     * 查询列表，Specification模式
     *
     * @author Admin
     * @Date 2019-09-06
     */
    public List<ExchangeRecordResult> findListBySpec(ExchangeRecordParam param) {
        return null;
    }

    /**
     * 查询分页数据，Specification模式
     *
     * @author Admin
     * @Date 2019-09-06
     */
    public LayuiPageInfo findPageBySpec(ExchangeRecordParam param) {
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(ExchangeRecordParam param) {
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private ExchangeRecord getOldEntity(ExchangeRecordParam param) {
        return this.getById(getKey(param));
    }

    private ExchangeRecord getEntity(ExchangeRecordParam param) {
        ExchangeRecord entity = new ExchangeRecord();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
