package cn.stylefeng.guns.modular.sbdsys.model.params;

import lombok.Data;
import cn.stylefeng.roses.kernel.model.validator.BaseValidatingParam;
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
public class UserExtParam extends UserExt implements Serializable, BaseValidatingParam {

    private static final long serialVersionUID = 1L;

    private String type ;
    private String baseID ;
    private String name  ;
    private String phone ;

    @Override
    public String checkParam() {
        return null;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBaseID() {
        return baseID;
    }

    public void setBaseID(String baseID) {
        this.baseID = baseID;
    }

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
