package cn.stylefeng.guns.modular.sbdsys.mapper;

import cn.stylefeng.guns.modular.sbdsys.entity.Commodity;
import cn.stylefeng.guns.modular.sbdsys.model.params.CommodityParam;
import cn.stylefeng.guns.modular.sbdsys.model.result.CommodityResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 商品信息 Mapper 接口
 * </p>
 *
 * @author Admin
 * @since 2019-09-06
 */
public interface CommodityMapper extends BaseMapper<Commodity> {

    /**
     * 获取列表
     *
     * @author Admin
     * @Date 2019-09-06
     */
    List<CommodityResult> customList(@Param("paramCondition") CommodityParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author Admin
     * @Date 2019-09-06
     */
    Page<CommodityResult> customPageList(@Param("page") Page page, @Param("paramCondition") CommodityParam paramCondition);

    Page<CommodityResult> customPageList4Phone(@Param("page") Page page, @Param("paramCondition") CommodityParam paramCondition);

    Page<CommodityResult> customPageList4PartMember(@Param("page") Page page, @Param("paramCondition") CommodityParam paramCondition);

    CommodityResult getResult(@Param("paramCondition") CommodityParam paramCondition);

}
