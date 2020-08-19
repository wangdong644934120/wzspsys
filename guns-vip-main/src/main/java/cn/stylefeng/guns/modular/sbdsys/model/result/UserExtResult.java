package cn.stylefeng.guns.modular.sbdsys.model.result;

import lombok.Data;
import java.util.Date;
import java.io.Serializable;
import java.math.BigDecimal;
import cn.stylefeng.guns.modular.sbdsys.entity.UserExt;

/**
 * <p>
 * 联系人表
 * </p>
 *
 * @author LICHENFENG
 * @since 2019-09-07
 */
@Data
public class UserExtResult extends UserExt implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name = "" ;
    private String phone = "";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
