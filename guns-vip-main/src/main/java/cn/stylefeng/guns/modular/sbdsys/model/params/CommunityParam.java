package cn.stylefeng.guns.modular.sbdsys.model.params;

import lombok.Data;
import cn.stylefeng.roses.kernel.model.validator.BaseValidatingParam;
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
public class CommunityParam extends Community implements Serializable, BaseValidatingParam {

    private static final long serialVersionUID = 1L;

    @Override
    public String checkParam() {
        return null;
    }

}
