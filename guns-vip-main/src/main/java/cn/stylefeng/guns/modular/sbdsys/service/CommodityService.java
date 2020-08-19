package cn.stylefeng.guns.modular.sbdsys.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.sbdsys.entity.Commodity;
import cn.stylefeng.guns.modular.sbdsys.mapper.CommodityMapper;
import cn.stylefeng.guns.modular.sbdsys.model.params.CommodityParam;
import cn.stylefeng.guns.modular.sbdsys.model.result.CommodityResult;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 商品信息 服务实现类
 * </p>
 *
 * @author Admin
 * @since 2019-09-06
 */
@Service
public class CommodityService extends ServiceImpl<CommodityMapper, Commodity> {

    /**
     * 新增
     *
     * @author Admin
     * @Date 2019-09-06
     */
    public void add(CommodityParam param){
        Commodity entity = getEntity(param);
        this.save(entity);
    }
    /**
     * 删除
     *
     * @author Admin
     * @Date 2019-09-06
     */
    public void delete(CommodityParam param){
        this.removeById(getKey(param));
    }
    /**
     * 更新
     *
     * @author Admin
     * @Date 2019-09-06
     */
    public void update(CommodityParam param){
        Commodity oldEntity = getOldEntity(param);
        Commodity newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }
    /**
     * 查询单条数据，Specification模式
     *
     * @author Admin
     * @Date 2019-09-06
     */
    public CommodityResult findBySpec(CommodityParam param){
        return null;
    }
    /**
     * 查询列表，Specification模式
     *
     * @author Admin
     * @Date 2019-09-06
     */
    public List<CommodityResult> findListBySpec(CommodityParam param){
        return null;
    }
    /**
     * 查询分页数据，Specification模式
     *
     * @author Admin
     * @Date 2019-09-06
     */
    public LayuiPageInfo findPageBySpec(CommodityParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    public LayuiPageInfo findPageBySpec4Phone(CommodityParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList4Phone(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }
    public LayuiPageInfo findPageBySpec4PartMember(CommodityParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList4PartMember(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(CommodityParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private Commodity getOldEntity(CommodityParam param) {
        return this.getById(getKey(param));
    }

    private Commodity getEntity(CommodityParam param) {
        Commodity entity = new Commodity();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

    public CommodityResult getResult(CommodityParam commodityParam){
        return this.baseMapper.getResult(commodityParam);
    }

}
