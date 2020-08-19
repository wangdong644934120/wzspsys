package cn.stylefeng.guns.modular.sbdsys.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.sbdsys.entity.PartyCommittee;
import cn.stylefeng.guns.modular.sbdsys.mapper.PartyCommitteeMapper;
import cn.stylefeng.guns.modular.sbdsys.model.params.PartyCommitteeParam;
import cn.stylefeng.guns.modular.sbdsys.model.result.PartyCommitteeResult;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 党委表 服务实现类
 * </p>
 *
 * @author Admin
 * @since 2019-09-06
 */
@Service
public class PartyCommitteeService extends ServiceImpl<PartyCommitteeMapper, PartyCommittee> {

    /**
     * 新增
     *
     * @author Admin
     * @Date 2019-09-06
     */
    public void add(PartyCommitteeParam param){
        PartyCommittee entity = getEntity(param);
        this.save(entity);
    }
    /**
     * 删除
     *
     * @author Admin
     * @Date 2019-09-06
     */
    public void delete(PartyCommitteeParam param){
        this.removeById(getKey(param));
    }
    /**
     * 更新
     *
     * @author Admin
     * @Date 2019-09-06
     */
    public void update(PartyCommitteeParam param){
        PartyCommittee oldEntity = getOldEntity(param);
        PartyCommittee newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }
    /**
     * 查询单条数据，Specification模式
     *
     * @author Admin
     * @Date 2019-09-06
     */
    public PartyCommitteeResult findBySpec(PartyCommitteeParam param){
        return null;
    }
    /**
     * 查询列表，Specification模式
     *
     * @author Admin
     * @Date 2019-09-06
     */
    public List<PartyCommitteeResult> findListBySpec(PartyCommitteeParam param){
        return null;
    }
    /**
     * 查询分页数据，Specification模式
     *
     * @author Admin
     * @Date 2019-09-06
     */
    public LayuiPageInfo findPageBySpec(PartyCommitteeParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(PartyCommitteeParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private PartyCommittee getOldEntity(PartyCommitteeParam param) {
        return this.getById(getKey(param));
    }

    private PartyCommittee getEntity(PartyCommitteeParam param) {
        PartyCommittee entity = new PartyCommittee();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
