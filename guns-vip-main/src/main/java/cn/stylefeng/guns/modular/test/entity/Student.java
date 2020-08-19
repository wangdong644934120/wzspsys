package cn.stylefeng.guns.modular.test.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 学生表

 * </p>
 *
 * @author wangdong
 * @since 2019-12-24
 */
@TableName("biz_student")
public class Student implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 学生学号
     */
    @TableField("code")
    private String code;

    /**
     * 学生姓名
     */
    @TableField("name")
    private String name;

    /**
     * 家庭住址
     */
    @TableField("address")
    private String address;

    /**
     * 班级
     */
    @TableField("classesid")
    private String classesid;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getClassesid() {
        return classesid;
    }

    public void setClassesid(String classesid) {
        this.classesid = classesid;
    }

    @Override
    public String toString() {
        return "Student{" +
        "id=" + id +
        ", code=" + code +
        ", name=" + name +
        ", address=" + address +
        ", classesid=" + classesid +
        "}";
    }
}
