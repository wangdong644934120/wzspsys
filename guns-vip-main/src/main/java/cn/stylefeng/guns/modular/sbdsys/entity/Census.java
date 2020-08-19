package cn.stylefeng.guns.modular.sbdsys.entity;

import lombok.Data;

@Data
public class Census {
    private int c = 0;
    private int monthCount = 0;
    private int yearCount = 0;


    private int score = 0;
    private int monthScore = 0;
    private int yearScore = 0;

    private String month;
    private String year;
    private String date;

    private int manager = 0;

    private String branchId;
    private int branchManager = 0;

    private String committeeId;
    private String communityId;
    private int communityManager = 0;

    private String branchName;
    private String committeeName;
    private String communityName;
    private int committeeManager = 0;

    private long userId;
    private String userName;
    private String avatar;
    private String phone;
    private String orderby;
    private int userManager = 0;

    private String storeId;
    private int storeManager = 0;

}
