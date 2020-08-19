package cn.stylefeng.guns.modular.sbdsys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author LICHENFENG
 * @since 2019-09-07
 */
@TableName("sbd_wish_record")
@Data
public class WishRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 微心愿id
     */
    @TableId(value = "id", type = IdType.UUID)
    private String id;

    /**
     * 抢愿人id
     */
    @TableField("user_id")
    private Long userId;

    /**
     * 参加时间
     */
    @TableField("action_time")
    private Date actionTime;

    /**
     * @Description 执行时间
     * @Author lichenfeng
     * @Date 2019/9/17 14:36
     **/
    @TableField("execute_time")
    private Date executeTime;

    /**
     * 所获积分
     */
    @TableField("score")
    private Integer score;

    /**
     * 微心愿id
     */
    @TableField("wish_id")
    private String wishId;

    /**
     * 情况说明
     */
    @TableField("note")
    private String note;


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

    public Date getActionTime() {
        return actionTime;
    }

    public void setActionTime(Date actionTime) {
        this.actionTime = actionTime;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getWishId() {
        return wishId;
    }

    public void setWishId(String wishId) {
        this.wishId = wishId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "WishRecord{" +
        "id=" + id +
        ", userId=" + userId +
        ", actionTime=" + actionTime +
        ", score=" + score +
        ", wishId=" + wishId +
        ", note=" + note +
        "}";
    }
}
