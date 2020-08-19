package cn.stylefeng.guns.generator.modular.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 数据库信息表
 * </p>
 *
 * @author stylefeng
 * @since 2019-05-11
 */
@TableName("database_info")
public class DatabaseInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "db_id", type = IdType.ID_WORKER)
    private Long dbId;

    /**
     * 数据库名称
     */
    @TableField("db_name")
    private String dbName;

    /**
     * jdbc的驱动类型
     */
    @TableField("jdbc_driver")
    private String jdbcDriver;

    /**
     * 数据库连接的账号
     */
    @TableField("user_name")
    private String userName;

    /**
     * 数据库连接密码
     */
    @TableField("password")
    private String password;

    /**
     * jdbc的url
     */
    @TableField("jdbc_url")
    private String jdbcUrl;

    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;


    public Long getDbId() {
        return dbId;
    }

    public void setDbId(Long dbId) {
        this.dbId = dbId;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public String getJdbcDriver() {
        return jdbcDriver;
    }

    public void setJdbcDriver(String jdbcDriver) {
        this.jdbcDriver = jdbcDriver;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getJdbcUrl() {
        return jdbcUrl;
    }

    public void setJdbcUrl(String jdbcUrl) {
        this.jdbcUrl = jdbcUrl;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "DatabaseInfo{" +
        "dbId=" + dbId +
        ", dbName=" + dbName +
        ", jdbcDriver=" + jdbcDriver +
        ", userName=" + userName +
        ", password=" + password +
        ", jdbcUrl=" + jdbcUrl +
        ", createTime=" + createTime +
        "}";
    }
}
