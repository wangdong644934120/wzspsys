package cn.stylefeng.guns.modular.sbdsys.mapper;

import cn.stylefeng.guns.modular.sbdsys.entity.ActivitySumup;
import cn.stylefeng.guns.modular.sbdsys.entity.MyFile;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 账户表 Mapper 接口
 * </p>
 *
 * @author Admin
 * @since 2019-09-06
 */
public interface ActivitySumUpMapper {

   int insertActivitySumUp(@Param("activitySumup") ActivitySumup activitySumup);

   int updateActivitySumUp(@Param("activitySumup") ActivitySumup activitySumup);

   ActivitySumup  selectActivitySumUpExists(@Param("activitySumup") ActivitySumup activitySumup);


   ActivitySumup  selectActivityDetailWithSumUp(@Param("activitySumup") ActivitySumup activitySumup);

   ActivitySumup  selectActivitySumUpDetail(@Param("activitySumup") ActivitySumup activitySumup);

}
