package cn.stylefeng.guns.modular.sbdsys.entity;

import lombok.Data;

@Data
public class Suggestion {
    private int id = 0;
    private String content = "";
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


}
