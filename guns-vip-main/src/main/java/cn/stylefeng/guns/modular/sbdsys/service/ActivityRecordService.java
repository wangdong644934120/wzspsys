package cn.stylefeng.guns.modular.sbdsys.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.sbdsys.entity.ActivityRecord;
import cn.stylefeng.guns.modular.sbdsys.mapper.ActivityRecordMapper;
import cn.stylefeng.guns.modular.sbdsys.model.params.ActivityRecordParam;
import cn.stylefeng.guns.modular.sbdsys.model.result.ActivityRecordResult;
import cn.stylefeng.guns.sys.core.shiro.ShiroKit;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 活动记录表 服务实现类
 * </p>
 *
 * @author LICHENFENG
 * @since 2019-09-10
 */
@Service
public class ActivityRecordService extends ServiceImpl<ActivityRecordMapper, ActivityRecord> {

    //新增
    public void add(ActivityRecordParam param){
        param.setCreateTime(new Date());
        param.setCreateUser(ShiroKit.getUser().getId());
        this.save(param);
    }
    //删除
    public void delete(ActivityRecordParam param){
        this.removeById(getKey(param));
    }
    //更新
    public void update(ActivityRecordParam param){
        this.updateById(param);
    }
    //查询单条数据，Specification模式
    public ActivityRecordResult findBySpec(ActivityRecordParam param){
        List<ActivityRecordResult> list=this.findListBySpec(param);
        if(list==null||list.isEmpty()){
            return null;
        }
        return list.get(0);
    }
    //查询列表，Specification模式
    public List<ActivityRecordResult> findListBySpec(ActivityRecordParam param){
        return this.baseMapper.customList(param);
    }
    //查询分页数据，Specification模式
    public LayuiPageInfo findPageBySpec(ActivityRecordParam param){
        Page pageContext = LayuiPageFactory.defaultPage();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(ActivityRecordParam param){
        return param.getId();
    }
}
