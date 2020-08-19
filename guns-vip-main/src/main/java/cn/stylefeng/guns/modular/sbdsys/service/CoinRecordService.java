package cn.stylefeng.guns.modular.sbdsys.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.sbdsys.entity.CoinRecord;
import cn.stylefeng.guns.modular.sbdsys.mapper.CoinRecordMapper;
import cn.stylefeng.guns.modular.sbdsys.model.params.CoinRecordParam;
import cn.stylefeng.guns.modular.sbdsys.model.result.CoinRecordResult;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 先锋币收支记录 服务实现类
 * </p>
 *
 * @author Admin
 * @since 2019-09-06
 */
@Service
public class CoinRecordService extends ServiceImpl<CoinRecordMapper, CoinRecord> {

    /**
     * 新增
     *
     * @author Admin
     * @Date 2019-09-06
     */
    public void add(CoinRecordParam param){
        param.setCreateTime(new Date());
        CoinRecord entity = getEntity(param);
        this.save(entity);
    }
    /**
     * 删除
     *
     * @author Admin
     * @Date 2019-09-06
     */
    public void delete(CoinRecordParam param){
        this.removeById(getKey(param));
    }
    /**
     * 更新
     *
     * @author Admin
     * @Date 2019-09-06
     */
    public void update(CoinRecordParam param){
        CoinRecord oldEntity = getOldEntity(param);
        CoinRecord newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }
    /**
     * 查询单条数据，Specification模式
     *
     * @author Admin
     * @Date 2019-09-06
     */
    public CoinRecordResult findBySpec(CoinRecordParam param){
        return null;
    }
    /**
     * 查询列表，Specification模式
     *
     * @author Admin
     * @Date 2019-09-06
     */
    public List<CoinRecordResult> findListBySpec(CoinRecordParam param){
        return null;
    }
    /**
     * 查询分页数据，Specification模式
     *
     * @author Admin
     * @Date 2019-09-06
     */
    public LayuiPageInfo findPageBySpec(CoinRecordParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(CoinRecordParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private CoinRecord getOldEntity(CoinRecordParam param) {
        return this.getById(getKey(param));
    }

    private CoinRecord getEntity(CoinRecordParam param) {
        CoinRecord entity = new CoinRecord();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
