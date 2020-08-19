package cn.stylefeng.guns.modular.sbdsys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 消费记录
 * </p>
 *
 * @author LICHENFENG
 * @since 2019-09-07
 */
@TableName("sbd_exchange_record")
public class ExchangeRecord implements Serializable {

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
     * 花费先锋币
     */
    @TableField("cost_coin")
    private Integer costCoin;

    /**
     * 商品Id
     */
    @TableField("commodity_id")
    private String commodityId;
    /**
     * 兑换人id
     */
    @TableField("exchange_user")
    private Long exchangeUser;

    /**
     * 是否兑换：0待兑换，1已兑换
     */
    @TableField("isExchange")
    private Integer isExchange;

    /**
     * 下单时间
     */
    @TableField("order_time")
    private Date orderTime;

    /**
     * 兑换时间
     */
    @TableField("exchange_time")
    private Date exchangeTime;

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

    public Integer getCostCoin() {
        return costCoin;
    }

    public void setCostCoin(Integer costCoin) {
        this.costCoin = costCoin;
    }

    public String getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(String commodityId) {
        this.commodityId = commodityId;
    }

    public Integer getIsExchange() {
        return isExchange;
    }

    public void setIsExchange(Integer isExchange) {
        this.isExchange = isExchange;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public Date getExchangeTime() {
        return exchangeTime;
    }

    public void setExchangeTime(Date exchangeTime) {
        this.exchangeTime = exchangeTime;
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

    public Long getExchangeUser() {
        return exchangeUser;
    }

    public void setExchangeUser(Long exchangeUser) {
        this.exchangeUser = exchangeUser;
    }


    @Override
    public String toString() {
        return "ExchangeRecord{" +
        "id=" + id +
        ", userId=" + userId +
        ", costCoin=" + costCoin +
        ", commodityId=" + commodityId +
        ", isExchange=" + isExchange +
        ", orderTime=" + orderTime +
        ", exchangeTime=" + exchangeTime +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}";
    }
}
