package cn.stylefeng.guns.modular.sbdsys.service;

import cn.stylefeng.guns.base.pojo.node.ZTreeNode;
import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.sbdsys.entity.PartyBranch;
import cn.stylefeng.guns.modular.sbdsys.mapper.PartyBranchMapper;
import cn.stylefeng.guns.modular.sbdsys.model.params.PartyBranchParam;
import cn.stylefeng.guns.modular.sbdsys.model.result.PartyBranchResult;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 党支部表 服务实现类
 * </p>
 *
 * @author Admin
 * @since 2019-09-06
 */
@Service
public class PartyBranchService extends ServiceImpl<PartyBranchMapper, PartyBranch> {

    /**
     * 新增
     *
     * @author Admin
     * @Date 2019-09-06
     */
    public void add(PartyBranchParam param){
        PartyBranch entity = getEntity(param);
        this.save(entity);
    }
    /**
     * 删除
     *
     * @author Admin
     * @Date 2019-09-06
     */
    public void delete(PartyBranchParam param){
        this.removeById(getKey(param));
    }
    /**
     * 更新
     *
     * @author Admin
     * @Date 2019-09-06
     */
    public void update(PartyBranchParam param){
        PartyBranch oldEntity = getOldEntity(param);
        PartyBranch newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }
    /**
     * 查询单条数据，Specification模式
     *
     * @author Admin
     * @Date 2019-09-06
     */
    public PartyBranchResult findBySpec(PartyBranchParam param){
        return null;
    }
    /**
     * 查询列表，Specification模式
     *
     * @author Admin
     * @Date 2019-09-06
     */
    public List<PartyBranchResult> findListBySpec(PartyBranchParam param){
        return null;
    }
    /**
     * 查询分页数据，Specification模式
     *
     * @author Admin
     * @Date 2019-09-06
     */
    public LayuiPageInfo findPageBySpec(PartyBranchParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(PartyBranchParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private PartyBranch getOldEntity(PartyBranchParam param) {
        return this.getById(getKey(param));
    }

    private PartyBranch getEntity(PartyBranchParam param) {
        PartyBranch entity = new PartyBranch();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

    public List<ZTreeNode> communityTreeListByBranch(String branch) {
        return this.baseMapper.communityTreeListByBranch(branch);
    }
}
