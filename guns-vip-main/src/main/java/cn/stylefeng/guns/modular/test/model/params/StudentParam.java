package cn.stylefeng.guns.modular.test.model.params;

import lombok.Data;
import cn.stylefeng.roses.kernel.model.validator.BaseValidatingParam;
import java.util.Date;
import java.io.Serializable;
import java.math.BigDecimal;
import cn.stylefeng.guns.modular.test.entity.Student;

/**
 * <p>
 * 学生表

 * </p>
 *
 * @author wangdong
 * @since 2019-12-24
 */
@Data
public class StudentParam extends Student implements Serializable, BaseValidatingParam {

    private static final long serialVersionUID = 1L;


    @Override
    public String checkParam() {
        return null;
    }

}
