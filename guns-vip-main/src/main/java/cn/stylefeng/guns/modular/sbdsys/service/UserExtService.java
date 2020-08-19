package cn.stylefeng.guns.modular.sbdsys.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.sbdsys.entity.UserExt;
import cn.stylefeng.guns.modular.sbdsys.mapper.UserExtMapper;
import cn.stylefeng.guns.modular.sbdsys.model.params.UserExtParam;
import cn.stylefeng.guns.modular.sbdsys.model.result.UserExtResult;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 联系人表 服务实现类
 * </p>
 *
 * @author Admin
 * @since 2019-09-06
 */
@Service
public class UserExtService extends ServiceImpl<UserExtMapper, UserExt> {

    /**
     * 新增
     *
     * @author Admin
     * @Date 2019-09-06
     */
    public void add(UserExtParam param){
        UserExt entity = getEntity(param);
        this.saveOrUpdate(entity);
    }
    /**
     * 删除
     *
     * @author Admin
     * @Date 2019-09-06
     */
    public void delete(UserExtParam param){
        this.removeById(getKey(param));
    }
    /**
     * 更新
     *
     * @author Admin
     * @Date 2019-09-06
     */
    public void update(UserExtParam param){
        UserExt oldEntity = getOldEntity(param);
        UserExt newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }
    /**
     * 查询单条数据，Specification模式
     *
     * @author Admin
     * @Date 2019-09-06
     */
    public UserExtResult findBySpec(UserExtParam param){
        return null;
    }
    /**
     * 查询列表，Specification模式
     *
     * @author Admin
     * @Date 2019-09-06
     */
    public List<UserExtResult> findListBySpec(UserExtParam param){
        return null;
    }
    /**
     * 查询分页数据，Specification模式
     *
     * @author Admin
     * @Date 2019-09-06
     */
    public LayuiPageInfo findPageBySpec(UserExtParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(UserExtParam param){
        return param.getUserId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private UserExt getOldEntity(UserExtParam param) {
        return this.getById(getKey(param));
    }

    private UserExt getEntity(UserExtParam param) {
        UserExt entity = new UserExt();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }


    public List<UserExtResult> getByUserID(UserExtParam userExtParam) {
        return this.baseMapper.customList(userExtParam);
    }

    public List<UserExtResult> getByPhone(String phone) {
        UserExtParam userExtParam = new UserExtParam();
        userExtParam.setPhone(phone);
        return this.baseMapper.getByPhone(userExtParam);
    }
}
