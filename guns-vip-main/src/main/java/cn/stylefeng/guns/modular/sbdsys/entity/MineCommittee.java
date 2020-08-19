package cn.stylefeng.guns.modular.sbdsys.entity;

import lombok.Data;

import java.util.List;

@Data
public class MineCommittee {
    private String committeeId;
    private String committeeCode;
    private String committeeName;
    private String committeePhone;

    private String branchCount;

    private List<MineBranch> branchList;


}
