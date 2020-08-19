package cn.stylefeng.guns.util.wechat;

import lombok.Data;

@Data
public class WechatResult {

    private String errcode = "";
    private String errmsg = "";

    private String access_token = "";
    private int expires_in = 0;

}
