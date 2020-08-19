package cn.stylefeng.guns.modular.sbdsys.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 活动总结
 * cong
 * @since 2019-09-07
 */
@Data
public class ActivitySumup {
    private int id = 0;
    private String content = "";
    private String activityId = "";
    private String title = "";
    private int isShow = 0;
    private String createDate = "";
    private long createUser = 0;
    private String updateDate = "";
    private long updateUser = 0;
    private int isDel = 0;
    private String remark = "";
    private int status = 0;

    private String tempId = "";
    private String orderby = "";
    private String limit = "";
    private int count = 0;
    private int canEdit = 0;
}
