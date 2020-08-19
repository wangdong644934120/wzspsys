package cn.stylefeng.guns.modular.sbdsys.model.result;

import lombok.Data;
import java.util.Date;
import java.io.Serializable;
import java.math.BigDecimal;
import cn.stylefeng.guns.modular.sbdsys.entity.Account;

/**
 * <p>
 * 账户表
 * </p>
 *
 * @author LICHENFENG
 * @since 2019-09-07
 */
@Data
public class AccountResult extends Account implements Serializable {

    private static final long serialVersionUID = 1L;

}
