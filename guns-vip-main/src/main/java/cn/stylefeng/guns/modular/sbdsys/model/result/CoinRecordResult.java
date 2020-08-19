package cn.stylefeng.guns.modular.sbdsys.model.result;

import lombok.Data;
import java.util.Date;
import java.io.Serializable;
import java.math.BigDecimal;
import cn.stylefeng.guns.modular.sbdsys.entity.CoinRecord;

/**
 * <p>
 * 先锋币收支记录
 * </p>
 *
 * @author LICHENFENG
 * @since 2019-09-07
 */
@Data
public class CoinRecordResult extends CoinRecord implements Serializable {

    private static final long serialVersionUID = 1L;

}
