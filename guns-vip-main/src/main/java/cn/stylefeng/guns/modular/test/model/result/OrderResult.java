package cn.stylefeng.guns.modular.test.model.result;

import lombok.Data;
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
public class OrderResult extends Order implements Serializable {

    private static final long serialVersionUID = 1L;

}
