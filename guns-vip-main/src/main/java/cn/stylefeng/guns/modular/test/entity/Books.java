package cn.stylefeng.guns.modular.test.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 书籍表

 * </p>
 *
 * @author wangdong
 * @since 2019-12-23
 */
@TableName("biz_books")
public class Books implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 书籍名称
     */
    @TableField("bname")
    private String bname;

    /**
     * 作者
     */
    @TableField("bauthor")
    private String bauthor;

    /**
     * 出版社
     */
    @TableField("bcompany")
    private String bcompany;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBname() {
        return bname;
    }

    public void setBname(String bname) {
        this.bname = bname;
    }

    public String getBauthor() {
        return bauthor;
    }

    public void setBauthor(String bauthor) {
        this.bauthor = bauthor;
    }

    public String getBcompany() {
        return bcompany;
    }

    public void setBcompany(String bcompany) {
        this.bcompany = bcompany;
    }

    @Override
    public String toString() {
        return "Books{" +
        "id=" + id +
        ", bname=" + bname +
        ", bauthor=" + bauthor +
        ", bcompany=" + bcompany +
        "}";
    }
}
