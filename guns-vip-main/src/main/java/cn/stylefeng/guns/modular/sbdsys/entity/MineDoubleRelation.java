package cn.stylefeng.guns.modular.sbdsys.entity;

import lombok.Data;

import java.util.List;

@Data
public class MineDoubleRelation {
    private String branchId;
    private String branchName;
    private int personCount;
    private long userId;
    private String userName;
    private String phone;
    private String communityId;
    private String communityName;

    private List<MineDoubleRelation> userList;
    private List<MineDoubleRelation> communityList;

}
