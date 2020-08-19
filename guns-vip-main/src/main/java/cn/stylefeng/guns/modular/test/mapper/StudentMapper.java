package cn.stylefeng.guns.modular.test.mapper;

import cn.stylefeng.guns.modular.test.entity.Student;
import cn.stylefeng.guns.modular.test.model.params.StudentParam;
import cn.stylefeng.guns.modular.test.model.result.StudentResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 学生表
 Mapper 接口
 * </p>
 *
 * @author wangdong
 * @since 2019-12-24
 */
public interface StudentMapper extends BaseMapper<Student> {

    /**
     * 获取列表
     *
     * @author wangdong
     * @Date 2019-12-24
     */
    List<StudentResult> customList(@Param("paramCondition") StudentParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author wangdong
     * @Date 2019-12-24
     */
    Page<StudentResult> customPageList(@Param("page") Page page, @Param("paramCondition") StudentParam paramCondition);


}
