package cn.stylefeng.guns.modular.sbdsys.model.result;

import lombok.Data;
import java.util.Date;
import java.io.Serializable;
import java.math.BigDecimal;
import cn.stylefeng.guns.modular.sbdsys.entity.WishRecord;

/**
 * <p>
 * 
 * </p>
 *
 * @author LICHENFENG
 * @since 2019-09-07
 */
@Data
public class WishRecordResult extends WishRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    private String userName;//抢愿人姓名
    private String userPhone;//抢愿人电话
}
