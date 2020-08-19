package cn.stylefeng.guns.modular.sbdsys.mapper;

import cn.stylefeng.guns.modular.sbdsys.entity.WishRecord;
import cn.stylefeng.guns.modular.sbdsys.model.params.WishRecordParam;
import cn.stylefeng.guns.modular.sbdsys.model.result.WishRecordResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Admin
 * @since 2019-09-06
 */
public interface WishRecordMapper extends BaseMapper<WishRecord> {

    /**
     * 获取列表
     *
     * @author Admin
     * @Date 2019-09-06
     */
    List<WishRecordResult> customList(@Param("paramCondition") WishRecordParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author Admin
     * @Date 2019-09-06
     */
    Page<WishRecordResult> customPageList(@Param("page") Page page, @Param("paramCondition") WishRecordParam paramCondition);

    WishRecordResult getWishRecordDetial(@Param("wishId") String wishId);

}
