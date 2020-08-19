package cn.stylefeng.guns.modular.sbdsys.mapper;

import cn.stylefeng.guns.modular.sbdsys.entity.ActivityRecord;
import cn.stylefeng.guns.modular.sbdsys.model.params.ActivityRecordParam;
import cn.stylefeng.guns.modular.sbdsys.model.result.ActivityRecordResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 活动记录表 Mapper 接口
 * </p>
 *
 * @author Admin
 * @since 2019-09-06
 */
public interface ActivityRecordMapper extends BaseMapper<ActivityRecord> {

    /**
     * 获取列表
     *
     * @author Admin
     * @Date 2019-09-06
     */
    List<ActivityRecordResult> customList(@Param("paramCondition") ActivityRecordParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author Admin
     * @Date 2019-09-06
     */
    Page<ActivityRecordResult> customPageList(@Param("page") Page page, @Param("paramCondition") ActivityRecordParam paramCondition);

}
