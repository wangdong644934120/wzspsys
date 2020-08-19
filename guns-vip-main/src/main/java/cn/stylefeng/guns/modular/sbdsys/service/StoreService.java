package cn.stylefeng.guns.modular.sbdsys.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.sbdsys.entity.Store;
import cn.stylefeng.guns.modular.sbdsys.mapper.StoreMapper;
import cn.stylefeng.guns.modular.sbdsys.model.params.StoreParam;
import cn.stylefeng.guns.modular.sbdsys.model.result.StoreResult;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 商家表 服务实现类
 * </p>
 *
 * @author Admin
 * @since 2019-09-06
 */
@Service
public class StoreService extends ServiceImpl<StoreMapper, Store> {

    /**
     * 新增
     *
     * @author Admin
     * @Date 2019-09-06
     */
    public void add(StoreParam param){
        Store entity = getEntity(param);
        this.save(entity);
    }
    /**
     * 删除
     *
     * @author Admin
     * @Date 2019-09-06
     */
    public void delete(StoreParam param){
        this.removeById(getKey(param));
    }
    /**
     * 更新
     *
     * @author Admin
     * @Date 2019-09-06
     */
    public void update(StoreParam param){
        Store oldEntity = getOldEntity(param);
        Store newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }
    /**
     * 查询单条数据，Specification模式
     *
     * @author Admin
     * @Date 2019-09-06
     */
    public StoreResult findBySpec(StoreParam param){
        return null;
    }
    /**
     * 查询列表，Specification模式
     *
     * @author Admin
     * @Date 2019-09-06
     */
    public List<StoreResult> findListBySpec(StoreParam param){
        return null;
    }
    /**
     * 查询分页数据，Specification模式
     *
     * @author Admin
     * @Date 2019-09-06
     */
    public LayuiPageInfo findPageBySpec(StoreParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(StoreParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private Store getOldEntity(StoreParam param) {
        return this.getById(getKey(param));
    }

    private Store getEntity(StoreParam param) {
        Store entity = new Store();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
