package cn.stylefeng.guns.modular.test.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.test.entity.Student;
import cn.stylefeng.guns.modular.test.mapper.StudentMapper;
import cn.stylefeng.guns.modular.test.model.params.StudentParam;
import cn.stylefeng.guns.modular.test.model.result.StudentResult;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 学生表
 服务实现类
 * </p>
 *
 * @author wangdong
 * @since 2019-12-24
 */
@Service
public class StudentService extends ServiceImpl<StudentMapper, Student> {

    //新增
    public void add(StudentParam param){
        this.save(param);
    }
    //删除
    public void delete(StudentParam param){
        this.removeById(getKey(param));
    }
    //更新
    public void update(StudentParam param){
        this.updateById(param);
    }
    //查询单条数据，Specification模式
    public StudentResult findBySpec(StudentParam param){
        List<StudentResult> list=this.findListBySpec(param);
        if(list==null||list.isEmpty()){
            return null;
        }
        return list.get(0);
    }
    //查询列表，Specification模式
    public List<StudentResult> findListBySpec(StudentParam param){
        return this.baseMapper.customList(param);
    }
    //查询分页数据，Specification模式
    public LayuiPageInfo findPageBySpec(StudentParam param){
        Page pageContext = LayuiPageFactory.defaultPage();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(StudentParam param){
        return param.getId();
    }


}
