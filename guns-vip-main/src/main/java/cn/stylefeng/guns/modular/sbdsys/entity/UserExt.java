package cn.stylefeng.guns.modular.sbdsys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 联系人表
 * </p>
 *
 * @author LICHENFENG
 * @since 2019-09-07
 */
@TableName("sbd_user_ext")
public class UserExt implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "user_id", type = IdType.ID_WORKER)
    private Long userId;

    /**
     * 党委id
     */
    @TableField("committee")
    private String committee;

    /**
     * 党支部id
     */
    @TableField("branch")
    private String branch;

    /**
     * 社区id
     */
    @TableField("community")
    private String community;

    /**
     * 商家id
     */
    @TableField("store")
    private String store;


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getCommittee() {
        return committee;
    }

    public void setCommittee(String committee) {
        this.committee = committee;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getCommunity() {
        return community;
    }

    public void setCommunity(String community) {
        this.community = community;
    }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }

    @Override
    public String toString() {
        return "UserExt{" +
        "userId=" + userId +
        ", committee=" + committee +
        ", branch=" + branch +
        ", community=" + community +
        ", store=" + store +
        "}";
    }
}
