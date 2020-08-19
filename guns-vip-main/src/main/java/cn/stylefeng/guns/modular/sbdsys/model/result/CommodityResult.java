package cn.stylefeng.guns.modular.sbdsys.model.result;

import lombok.Data;
import java.util.Date;
import java.io.Serializable;
import java.math.BigDecimal;
import cn.stylefeng.guns.modular.sbdsys.entity.Commodity;

/**
 * <p>
 * 商品信息
 * </p>
 *
 * @author LICHENFENG
 * @since 2019-09-07
 */
@Data
public class CommodityResult extends Commodity implements Serializable {

    private static final long serialVersionUID = 1L;
    private String storeName;//商家名称
    private String storeAddress;//商家地址
    private String storePhone;//商家电话

}
