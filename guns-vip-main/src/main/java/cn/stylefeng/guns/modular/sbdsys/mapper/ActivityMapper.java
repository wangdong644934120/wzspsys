package cn.stylefeng.guns.modular.sbdsys.mapper;

import cn.stylefeng.guns.modular.sbdsys.entity.Activity;
import cn.stylefeng.guns.modular.sbdsys.entity.ActivitySumup;
import cn.stylefeng.guns.modular.sbdsys.model.params.ActivityParam;
import cn.stylefeng.guns.modular.sbdsys.model.result.ActivityResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 活动表 Mapper 接口
 * </p>
 *
 * @author Admin
 * @since 2019-09-06
 */
public interface ActivityMapper extends BaseMapper<Activity> {

    /**
     * 获取列表
     *
     * @author Admin
     * @Date 2019-09-06
     */
    List<ActivityResult> customList(@Param("paramCondition") ActivityParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author Admin
     * @Date 2019-09-06
     */
    Page<ActivityResult> customPageList(@Param("page") Page page, @Param("paramCondition") ActivityParam paramCondition);

    ActivityResult selectByPrimaryId(@Param("id")String id);

    Page<ActivityResult> getJoinListByUser(@Param("page") Page page, @Param("userId")Long userId);

    Page<ActivityResult> getDoneListByUser(@Param("page") Page page, @Param("userId")Long userId);

    List<String> getBranchCommunityIds(@Param("userId")Long userId);
}
