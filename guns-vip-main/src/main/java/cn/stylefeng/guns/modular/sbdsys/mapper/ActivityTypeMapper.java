package cn.stylefeng.guns.modular.sbdsys.mapper;

import cn.stylefeng.guns.modular.sbdsys.entity.ActivityType;
import cn.stylefeng.guns.modular.sbdsys.model.params.ActivityTypeParam;
import cn.stylefeng.guns.modular.sbdsys.model.result.ActivityTypeResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 活动类别信息表 Mapper 接口
 * </p>
 *
 * @author Admin
 * @since 2019-09-06
 */
public interface ActivityTypeMapper extends BaseMapper<ActivityType> {

    /**
     * 获取列表
     *
     * @author Admin
     * @Date 2019-09-06
     */
    List<ActivityTypeResult> customList(@Param("paramCondition") ActivityTypeParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author Admin
     * @Date 2019-09-06
     */
    Page<ActivityTypeResult> customPageList(@Param("page") Page page, @Param("paramCondition") ActivityTypeParam paramCondition);

}
