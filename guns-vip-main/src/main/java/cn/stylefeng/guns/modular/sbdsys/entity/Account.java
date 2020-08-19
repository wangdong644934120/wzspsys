package cn.stylefeng.guns.modular.sbdsys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 账户表
 * </p>
 *
 * @author LICHENFENG
 * @since 2019-09-07
 */
@TableName("sbd_account")
public class Account implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    @TableId(value = "id", type = IdType.UUID)
    private Long id;

    /**
     * 活动积分
     */
    @TableField("score_activity")
    private Integer scoreActivity;

    /**
     * 微心愿积分
     */
    @TableField("score_wish")
    private Integer scoreWish;

    /**
     * 剩余先锋币
     */
    @TableField("coin")
    private Integer coin;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getScoreActivity() {
        return scoreActivity;
    }

    public void setScoreActivity(Integer scoreActivity) {
        this.scoreActivity = scoreActivity;
    }

    public Integer getScoreWish() {
        return scoreWish;
    }

    public void setScoreWish(Integer scoreWish) {
        this.scoreWish = scoreWish;
    }

    public Integer getCoin() {
        return coin;
    }

    public void setCoin(Integer coin) {
        this.coin = coin;
    }

    @Override
    public String toString() {
        return "Account{" +
        "id=" + id +
        ", scoreActivity=" + scoreActivity +
        ", scoreWish=" + scoreWish +
        ", coin=" + coin +
        "}";
    }
}
