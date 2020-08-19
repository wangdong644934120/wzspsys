package cn.stylefeng.guns.modular.sbdsys.mapper;

import cn.stylefeng.guns.modular.sbdsys.entity.Wish;
import cn.stylefeng.guns.modular.sbdsys.model.params.WishParam;
import cn.stylefeng.guns.modular.sbdsys.model.result.WishResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 微心愿表 Mapper 接口
 * </p>
 *
 * @author Admin
 * @since 2019-09-06
 */
public interface WishMapper extends BaseMapper<Wish> {

    /**
     * 获取列表
     *
     * @author Admin
     * @Date 2019-09-06
     */
    List<WishResult> customList(@Param("paramCondition") WishParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author Admin
     * @Date 2019-09-06
     */
    Page<WishResult> customPageList(@Param("page") Page page, @Param("paramCondition") WishParam paramCondition);

    WishResult selectByPrimaryId(@Param("id")String id);

    Page<WishResult> customPageListByUser(@Param("page") Page page, @Param("userId")Long userId, @Param("status")int status);

}
