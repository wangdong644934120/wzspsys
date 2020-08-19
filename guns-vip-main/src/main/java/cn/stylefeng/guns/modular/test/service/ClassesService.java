package cn.stylefeng.guns.modular.test.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.test.entity.Classes;
import cn.stylefeng.guns.modular.test.mapper.ClassesMapper;
import cn.stylefeng.guns.modular.test.model.params.ClassesParam;
import cn.stylefeng.guns.modular.test.model.result.ClassesResult;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 班级表
 服务实现类
 * </p>
 *
 * @author wangdong
 * @since 2019-12-15
 */
@Service
public class ClassesService extends ServiceImpl<ClassesMapper, Classes> {

    //新增
    public void add(ClassesParam param){
        this.save(param);
    }
    //删除
    public void delete(ClassesParam param){
        this.removeById(getKey(param));
    }
    //更新
    public void update(ClassesParam param){
        this.updateById(param);
    }
    //查询单条数据，Specification模式
    public ClassesResult findBySpec(ClassesParam param){
        List<ClassesResult> list=this.findListBySpec(param);
        if(list==null||list.isEmpty()){
            return null;
        }
        return list.get(0);
    }
    //查询列表，Specification模式
    public List<ClassesResult> findListBySpec(ClassesParam param){
        return this.baseMapper.customList(param);
    }
    //查询分页数据，Specification模式
    public LayuiPageInfo findPageBySpec(ClassesParam param){
        Page pageContext = LayuiPageFactory.defaultPage();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(ClassesParam param){
        return param.getId();
    }

}
