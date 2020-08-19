package cn.stylefeng.guns.modular.sbdsys.entity;

import lombok.Data;

@Data
public class MineAccount {
    private long userId;
    private String name;
    private String phone;
    private String avatar;
    private int scoreActivity;
    private int scoreWish;
    private int score;
    private int coin;
    private String openId;
    private int goodsCount;

}
