package cn.stylefeng.guns.modular.sbdsys.model.result;

import lombok.Data;
import java.util.Date;
import java.io.Serializable;
import java.math.BigDecimal;
import cn.stylefeng.guns.modular.sbdsys.entity.ExchangeRecord;

/**
 * <p>
 * 消费记录
 * </p>
 *
 * @author LICHENFENG
 * @since 2019-09-07
 */
@Data
public class ExchangeRecordResult extends ExchangeRecord implements Serializable {

    private static final long serialVersionUID = 1L;
    private String exchanger;//兑换人
    private String exchangerPhone;//兑换人联系方式
    private String executor;//执行人--暂不使用，无对应数据库字段
    private String commodityName;
    private String pics;
    private String storeName;
}
