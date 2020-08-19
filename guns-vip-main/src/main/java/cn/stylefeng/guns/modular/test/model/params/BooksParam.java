package cn.stylefeng.guns.modular.test.model.params;

import lombok.Data;
import cn.stylefeng.roses.kernel.model.validator.BaseValidatingParam;
import java.util.Date;
import java.io.Serializable;
import java.math.BigDecimal;
import cn.stylefeng.guns.modular.test.entity.Books;

/**
 * <p>
 * 书籍表

 * </p>
 *
 * @author wangdong
 * @since 2019-12-23
 */
@Data
public class BooksParam extends Books implements Serializable, BaseValidatingParam {

    private static final long serialVersionUID = 1L;
    private  long time;

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    @Override
    public String checkParam() {
        return null;
    }

}
