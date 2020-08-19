package cn.stylefeng.guns.modular.sbdsys.model.result;

import lombok.Data;
import java.util.Date;
import java.io.Serializable;
import java.math.BigDecimal;
import cn.stylefeng.guns.modular.sbdsys.entity.Activity;

/**
 * <p>
 * 活动表
 * </p>
 *
 * @author LICHENFENG
 * @since 2019-09-07
 */
@Data
public class ActivityResult extends Activity implements Serializable {

    private static final long serialVersionUID = 1L;

    private int score;
    private String icon;
    private String communityName;
    private int canEdit=0;
    private String qrcode;
    private Date actionTime;//参加活动时间
    private int actionCount;//参加人数
    private int sumupId;
}
