package cn.stylefeng.guns.modular.sbdsys.model.result;

import lombok.Data;
import java.util.Date;
import java.io.Serializable;
import java.math.BigDecimal;
import cn.stylefeng.guns.modular.sbdsys.entity.PartyBranchPerson;

/**
 * <p>
 * 党支部人员表
 * </p>
 *
 * @author LICHENFENG
 * @since 2019-09-07
 */
@Data
public class PartyBranchPersonResult extends PartyBranchPerson implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name ;
    private String phone ;

}
