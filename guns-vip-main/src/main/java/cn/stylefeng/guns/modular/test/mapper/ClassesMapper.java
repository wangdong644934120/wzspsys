package cn.stylefeng.guns.modular.test.mapper;

import cn.stylefeng.guns.modular.test.entity.Classes;
import cn.stylefeng.guns.modular.test.model.params.ClassesParam;
import cn.stylefeng.guns.modular.test.model.result.ClassesResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 班级表
 Mapper 接口
 * </p>
 *
 * @author wangdong
 * @since 2019-12-15
 */
public interface ClassesMapper extends BaseMapper<Classes> {

    /**
     * 获取列表
     *
     * @author wangdong
     * @Date 2019-12-15
     */
    List<ClassesResult> customList(@Param("paramCondition") ClassesParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author wangdong
     * @Date 2019-12-15
     */
    Page<ClassesResult> customPageList(@Param("page") Page page, @Param("paramCondition") ClassesParam paramCondition);

}
