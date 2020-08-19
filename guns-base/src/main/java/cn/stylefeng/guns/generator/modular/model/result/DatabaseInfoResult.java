package cn.stylefeng.guns.generator.modular.model.result;

import lombok.Data;
import java.util.Date;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 数据库信息表
 * </p>
 *
 * @author stylefeng
 * @since 2019-05-11
 */
@Data
public class DatabaseInfoResult implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * 主键id
     */
    private Long dbId;

    /**
     * 数据库名称
     */
    private String dbName;

    /**
     * jdbc的驱动类型
     */
    private String jdbcDriver;

    /**
     * 数据库连接的账号
     */
    private String userName;

    /**
     * 数据库连接密码
     */
    private String password;

    /**
     * jdbc的url
     */
    private String jdbcUrl;

    /**
     * 创建时间
     */
    private Date createTime;

}
