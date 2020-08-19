package cn.stylefeng.guns.modular.sbdsys.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.sbdsys.entity.BranchCommunity;
import cn.stylefeng.guns.modular.sbdsys.mapper.BranchCommunityMapper;
import cn.stylefeng.guns.modular.sbdsys.model.params.BranchCommunityParam;
import cn.stylefeng.guns.modular.sbdsys.model.result.BranchCommunityResult;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 社区党支部关联表 服务实现类
 * </p>
 *
 * @author Admin
 * @since 2019-09-06
 */
@Service
public class BranchCommunityService extends ServiceImpl<BranchCommunityMapper, BranchCommunity> {

    /**
     * 新增
     *
     * @author Admin
     * @Date 2019-09-06
     */
    public void add(BranchCommunityParam param){
        BranchCommunity entity = getEntity(param);
        this.save(entity);
    }
    /**
     * 删除
     *
     * @author Admin
     * @Date 2019-09-06
     */
    public void delete(BranchCommunityParam param){
        this.removeById(getKey(param));
    }
    /**
     * 更新
     *
     * @author Admin
     * @Date 2019-09-06
     */
    public void update(BranchCommunityParam param){
        BranchCommunity oldEntity = getOldEntity(param);
        BranchCommunity newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }
    /**
     * 查询单条数据，Specification模式
     *
     * @author Admin
     * @Date 2019-09-06
     */
    public BranchCommunityResult findBySpec(BranchCommunityParam param){
        return null;
    }
    /**
     * 查询列表，Specification模式
     *
     * @author Admin
     * @Date 2019-09-06
     */
    public List<BranchCommunityResult> findListBySpec(BranchCommunityParam param){
        return null;
    }
    /**
     * 查询分页数据，Specification模式
     *
     * @author Admin
     * @Date 2019-09-06
     */
    public LayuiPageInfo findPageBySpec(BranchCommunityParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(BranchCommunityParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private BranchCommunity getOldEntity(BranchCommunityParam param) {
        return this.getById(getKey(param));
    }

    private BranchCommunity getEntity(BranchCommunityParam param) {
        BranchCommunity entity = new BranchCommunity();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

    public void deleteByBranch(String branch) {
        this.baseMapper.deleteByBranch(branch);
    }
}
