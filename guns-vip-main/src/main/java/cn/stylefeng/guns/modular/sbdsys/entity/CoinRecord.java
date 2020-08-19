package cn.stylefeng.guns.modular.sbdsys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 先锋币收支记录
 * </p>
 *
 * @author LICHENFENG
 * @since 2019-09-07
 */
@TableName("sbd_coin_record")
@Data
public class CoinRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.UUID)
    private String id;

    /**
     * 用户id
     */
    @TableField("user_id")
    private Long userId;

    /**
     * 0:收入，1：支出
     */
    @TableField("change_type")
    private Integer changeType;

    @TableField("score")
    private Integer score;

    @TableField("coin")
    private Integer coin;

    /**
     * 收支详情
     */
    @TableField("change_desc")
    private String changeDesc;

    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time", fill = FieldFill.UPDATE)
    private Date updateTime;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getChangeType() {
        return changeType;
    }

    public void setChangeType(Integer changeType) {
        this.changeType = changeType;
    }

    public String getChangeDesc() {
        return changeDesc;
    }

    public void setChangeDesc(String changeDesc) {
        this.changeDesc = changeDesc;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "CoinRecord{" +
        "id=" + id +
        ", userId=" + userId +
        ", changeType=" + changeType +
        ", changeDesc=" + changeDesc +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}";
    }
}
