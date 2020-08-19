package cn.stylefeng.guns.modular.test.model.result;

import lombok.Data;
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
public class BooksResult extends Books implements Serializable {

    private static final long serialVersionUID = 1L;

}
