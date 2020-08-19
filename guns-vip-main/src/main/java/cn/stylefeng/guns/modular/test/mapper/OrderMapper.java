package cn.stylefeng.guns.modular.test.mapper;

import cn.stylefeng.guns.modular.test.entity.Order;
import cn.stylefeng.guns.modular.test.model.params.OrderParam;
import cn.stylefeng.guns.modular.test.model.result.OrderResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 订单表
 Mapper 接口
 * </p>
 *
 * @author stylefeng
 * @since 2019-11-29
 */
public interface OrderMapper extends BaseMapper<Order> {

    /**
     * 获取列表
     *
     * @author stylefeng
     * @Date 2019-11-29
     */
    List<OrderResult> customList(@Param("paramCondition") OrderParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author stylefeng
     * @Date 2019-11-29
     */
    Page<OrderResult> customPageList(@Param("page") Page page, @Param("paramCondition") OrderParam paramCondition);

}
