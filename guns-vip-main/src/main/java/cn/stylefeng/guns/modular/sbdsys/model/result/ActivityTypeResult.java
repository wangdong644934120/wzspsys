package cn.stylefeng.guns.modular.sbdsys.model.result;

import lombok.Data;
import java.util.Date;
import java.io.Serializable;
import java.math.BigDecimal;
import cn.stylefeng.guns.modular.sbdsys.entity.ActivityType;

/**
 * <p>
 * 活动类别信息表
 * </p>
 *
 * @author LICHENFENG
 * @since 2019-09-07
 */
@Data
public class ActivityTypeResult extends ActivityType implements Serializable {

    private static final long serialVersionUID = 1L;

    private String type1Name = "";
    private String type2Name = "";

}
