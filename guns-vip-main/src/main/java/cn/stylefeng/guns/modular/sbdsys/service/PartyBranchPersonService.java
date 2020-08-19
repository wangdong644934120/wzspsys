package cn.stylefeng.guns.modular.sbdsys.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.sbdsys.entity.PartyBranchPerson;
import cn.stylefeng.guns.modular.sbdsys.mapper.PartyBranchPersonMapper;
import cn.stylefeng.guns.modular.sbdsys.model.params.PartyBranchPersonParam;
import cn.stylefeng.guns.modular.sbdsys.model.result.PartyBranchPersonResult;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 党支部人员表 服务实现类
 * </p>
 *
 * @author Admin
 * @since 2019-09-06
 */
@Service
public class PartyBranchPersonService extends ServiceImpl<PartyBranchPersonMapper, PartyBranchPerson> {

    /**
     * 新增
     *
     * @author Admin
     * @Date 2019-09-06
     */
    public void add(PartyBranchPersonParam param){
        PartyBranchPerson entity = getEntity(param);
        this.save(entity);
    }
    /**
     * 删除
     *
     * @author Admin
     * @Date 2019-09-06
     */
    public void delete(PartyBranchPersonParam param){
        this.removeById(getKey(param));
    }
    /**
     * 更新
     *
     * @author Admin
     * @Date 2019-09-06
     */
    public void update(PartyBranchPersonParam param){
        PartyBranchPerson oldEntity = getOldEntity(param);
        PartyBranchPerson newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }
    /**
     * 查询单条数据，Specification模式
     *
     * @author Admin
     * @Date 2019-09-06
     */
    public PartyBranchPersonResult findBySpec(PartyBranchPersonParam param){
        return null;
    }
    /**
     * 查询列表，Specification模式
     *
     * @author Admin
     * @Date 2019-09-06
     */
    public List<PartyBranchPersonResult> findListBySpec(PartyBranchPersonParam param){
        return null;
    }
    /**
     * 查询分页数据，Specification模式
     *
     * @author Admin
     * @Date 2019-09-06
     */
    public LayuiPageInfo findPageBySpec(PartyBranchPersonParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(PartyBranchPersonParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private PartyBranchPerson getOldEntity(PartyBranchPersonParam param) {
        return this.getById(getKey(param));
    }

    private PartyBranchPerson getEntity(PartyBranchPersonParam param) {
        PartyBranchPerson entity = new PartyBranchPerson();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

    public PartyBranchPerson getByUserId(Long userId) {
        PartyBranchPersonParam param = new PartyBranchPersonParam ();
        param.setPerson( userId);
        List<PartyBranchPerson> list = this.baseMapper.getByUserId(param);
        if(list.size()>0){
            return list.get(0) ;
        }else{
            return null ;
        }
    }

    /**
     * 删除 党员
     *
     * @author Admin
     * @Date 2019-10-14
     */
    public void deleteBranchPerson(PartyBranchPersonParam param){
        this.baseMapper.deleteBranchPerson(param);

    }
}
