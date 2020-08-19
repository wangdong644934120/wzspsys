package cn.stylefeng.guns.modular.sbdsys.model.result;

import lombok.Data;
import java.util.Date;
import java.io.Serializable;
import java.math.BigDecimal;
import cn.stylefeng.guns.modular.sbdsys.entity.Wish;

/**
 * <p>
 * 微心愿表
 * </p>
 *
 * @author LICHENFENG
 * @since 2019-09-07
 */
@Data
public class WishResult extends Wish implements Serializable {

    private static final long serialVersionUID = 1L;

    private int score;
    private String icon;
    private String communityName;
    private int canEdit=0;
    private String qrcode;
    private Date actionTime;
    private Date executeTime;
    private String executeNote;
    private String userName;
    private String userPhone;
}
