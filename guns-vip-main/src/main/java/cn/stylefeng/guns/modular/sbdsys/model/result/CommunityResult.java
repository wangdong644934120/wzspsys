package cn.stylefeng.guns.modular.sbdsys.model.result;

import lombok.Data;
import java.util.Date;
import java.io.Serializable;
import java.math.BigDecimal;
import cn.stylefeng.guns.modular.sbdsys.entity.Community;

/**
 * <p>
 * 社区表
 * </p>
 *
 * @author LICHENFENG
 * @since 2019-09-07
 */
@Data
public class CommunityResult extends Community implements Serializable {

    private static final long serialVersionUID = 1L;

}
