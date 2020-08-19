package cn.stylefeng.guns.modular.test.model.params;

import lombok.Data;
import cn.stylefeng.roses.kernel.model.validator.BaseValidatingParam;
import java.util.Date;
import java.io.Serializable;
import java.math.BigDecimal;
import cn.stylefeng.guns.modular.test.entity.Order;

/**
 * <p>
 * 订单表

 * </p>
 *
 * @author stylefeng
 * @since 2019-11-29
 */
@Data
public class OrderParam extends Order implements Serializable, BaseValidatingParam {

    private static final long serialVersionUID = 1L;

    @Override
    public String checkParam() {
        return null;
    }

}
