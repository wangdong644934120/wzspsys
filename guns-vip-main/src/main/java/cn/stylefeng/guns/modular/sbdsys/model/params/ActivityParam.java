package cn.stylefeng.guns.modular.sbdsys.model.params;

import lombok.Data;
import cn.stylefeng.roses.kernel.model.validator.BaseValidatingParam;
import java.util.Date;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

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
public class ActivityParam extends Activity implements Serializable, BaseValidatingParam {

    private static final long serialVersionUID = 1L;

    public List<String> communityIds;//党员的报道社区列表

    @Override
    public String checkParam() {
        return null;
    }

}
