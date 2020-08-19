package cn.stylefeng.guns.modular.sbdsys.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.sbdsys.entity.WishRecord;
import cn.stylefeng.guns.modular.sbdsys.mapper.WishRecordMapper;
import cn.stylefeng.guns.modular.sbdsys.model.params.WishRecordParam;
import cn.stylefeng.guns.modular.sbdsys.model.result.WishRecordResult;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import java.io.Serializable;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Admin
 * @since 2019-09-06
 */
@Service
public class WishRecordService extends ServiceImpl<WishRecordMapper, WishRecord> {

    /**
     * 新增
     *
     * @author Admin
     * @Date 2019-09-06
     */
    public void add(WishRecordParam param){
        WishRecord entity = getEntity(param);
        this.save(entity);
    }
    /**
     * 删除
     *
     * @author Admin
     * @Date 2019-09-06
     */
    public void delete(WishRecordParam param){
        this.removeById(getKey(param));
    }
    /**
     * 更新
     *
     * @author Admin
     * @Date 2019-09-06
     */
    public void update(WishRecord param){
        this.updateById(param);
    }
    /**
     * 查询单条数据，Specification模式
     *
     * @author Admin
     * @Date 2019-09-06
     */
    public WishRecordResult findBySpec(WishRecordParam param){
        return null;
    }
    /**
     * 查询列表，Specification模式
     *
     * @author Admin
     * @Date 2019-09-06
     */
    public List<WishRecordResult> findListBySpec(WishRecordParam param){
        return null;
    }
    /**
     * 查询分页数据，Specification模式
     *
     * @author Admin
     * @Date 2019-09-06
     */
    public LayuiPageInfo findPageBySpec(WishRecordParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(WishRecordParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private WishRecord getOldEntity(WishRecordParam param) {
        return this.getById(getKey(param));
    }

    private WishRecord getEntity(WishRecordParam param) {
        WishRecord entity = new WishRecord();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

    public WishRecordResult getWishRecordDetial(String id){
        return this.baseMapper.getWishRecordDetial(id);
    }

}
