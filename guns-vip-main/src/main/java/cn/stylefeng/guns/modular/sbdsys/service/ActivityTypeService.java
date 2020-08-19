package cn.stylefeng.guns.modular.sbdsys.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.sbdsys.entity.ActivityType;
import cn.stylefeng.guns.modular.sbdsys.mapper.ActivityTypeMapper;
import cn.stylefeng.guns.modular.sbdsys.model.params.ActivityTypeParam;
import cn.stylefeng.guns.modular.sbdsys.model.result.ActivityTypeResult;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 活动类别信息表 服务实现类
 * </p>
 *
 * @author Admin
 * @since 2019-09-06
 */
@Service
public class ActivityTypeService extends ServiceImpl<ActivityTypeMapper, ActivityType> {

    /**
     * 新增
     *
     * @author Admin
     * @Date 2019-09-06
     */
    public void add(ActivityTypeParam param){
        ActivityType entity = getEntity(param);
        this.save(entity);
    }
    /**
     * 删除
     *
     * @author Admin
     * @Date 2019-09-06
     */
    public void delete(ActivityTypeParam param){
        this.removeById(getKey(param));
    }
    /**
     * 更新
     *
     * @author Admin
     * @Date 2019-09-06
     */
    public void update(ActivityTypeParam param){
        ActivityType oldEntity = getOldEntity(param);
        ActivityType newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }
    /**
     * 查询单条数据，Specification模式
     *
     * @author Admin
     * @Date 2019-09-06
     */
    public ActivityTypeResult findBySpec(ActivityTypeParam param){
        return null;
    }
    /**
     * 查询列表，Specification模式
     *
     * @author Admin
     * @Date 2019-09-06
     */
    public List<ActivityTypeResult> findListBySpec(ActivityTypeParam param){
        return this.baseMapper.customList(param);
    }
    /**
     * 查询分页数据，Specification模式
     *
     * @author Admin
     * @Date 2019-09-06
     */
    public LayuiPageInfo findPageBySpec(ActivityTypeParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(ActivityTypeParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private ActivityType getOldEntity(ActivityTypeParam param) {
        return this.getById(getKey(param));
    }

    private ActivityType getEntity(ActivityTypeParam param) {
        ActivityType entity = new ActivityType();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
