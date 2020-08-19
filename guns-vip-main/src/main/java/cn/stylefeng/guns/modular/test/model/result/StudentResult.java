package cn.stylefeng.guns.modular.test.model.result;

import lombok.Data;
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
public class StudentResult extends Student implements Serializable {

    private static final long serialVersionUID = 1L;

    private String classesname;

    public String getClassesname() {
        return classesname;
    }

    public void setClassesname(String classesname) {
        this.classesname = classesname;
    }
}
