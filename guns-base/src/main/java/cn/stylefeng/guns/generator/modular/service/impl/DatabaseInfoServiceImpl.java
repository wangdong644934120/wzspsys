package cn.stylefeng.guns.generator.modular.service.impl;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.generator.modular.entity.DatabaseInfo;
import cn.stylefeng.guns.generator.modular.mapper.DatabaseInfoMapper;
import cn.stylefeng.guns.generator.modular.model.params.DatabaseInfoParam;
import cn.stylefeng.guns.generator.modular.model.result.DatabaseInfoResult;
import cn.stylefeng.guns.generator.modular.service.DatabaseInfoService;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 数据库信息表 服务实现类
 * </p>
 *
 * @author stylefeng
 * @since 2019-05-11
 */
@Service
public class DatabaseInfoServiceImpl extends ServiceImpl<DatabaseInfoMapper, DatabaseInfo> implements DatabaseInfoService {

    @Override
    public void add(DatabaseInfoParam param) {
        DatabaseInfo entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(DatabaseInfoParam param) {
        this.removeById(getKey(param));
    }

    @Override
    public void update(DatabaseInfoParam param) {
        DatabaseInfo oldEntity = getOldEntity(param);
        DatabaseInfo newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public DatabaseInfoResult findBySpec(DatabaseInfoParam param) {
        return null;
    }

    @Override
    public List<DatabaseInfoResult> findListBySpec(DatabaseInfoParam param) {
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(DatabaseInfoParam param) {
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(DatabaseInfoParam param) {
        return param.getDbId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private DatabaseInfo getOldEntity(DatabaseInfoParam param) {
        return this.getById(getKey(param));
    }

    private DatabaseInfo getEntity(DatabaseInfoParam param) {
        DatabaseInfo entity = new DatabaseInfo();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
