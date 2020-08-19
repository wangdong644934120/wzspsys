package cn.stylefeng.guns.modular.common.service;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.stylefeng.guns.sys.core.properties.GunsProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URLDecoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

/**
 * @Description: TODO
 * @Duthor: lichenfeng
 * @Date: 2019/9/12 16:27
 * @Version 1.0
 */
@Slf4j
@Service
public class WechatTokenService {

    private static String jsapiToken;
    private static long lastJsTokenTime;
    @Autowired
    private GunsProperties gunsProperties;

    private String getAccessToken(){
        String accessTokenUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+gunsProperties.getAppId()+"&secret="+gunsProperties.getAppSecret();
        String result = HttpUtil.get(accessTokenUrl);
        JSONObject jsonObject = JSONUtil.parseObj(result);
        String access_token = (String) jsonObject.get("access_token");
        log.info("获取到access_token："+access_token);
        return access_token;
    }

    public String getJsapiToken() {
        if (jsapiToken == null || (System.currentTimeMillis() - lastJsTokenTime > 3600 * 1000)) {
            try {
                String url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=" + getAccessToken() + "&type=jsapi";
                String result = HttpUtil.get(url);
                if (result != null) {
                    JSONObject json = JSONUtil.parseObj(result);
                    if (json.containsKey("ticket")) {
                        jsapiToken = (String)json.get("ticket");
                        lastJsTokenTime = System.currentTimeMillis();
                        log.info("获取微信jssdk凭证成功:" + jsapiToken);
                    }
                }
            } catch (Exception ex) {
                log.error("获取微信jssdk凭证成功", ex);
            }
        }
        return jsapiToken;
    }

    public HashMap getJSSDKConf(String wholeUrl, boolean debug) {
        String noncestr = "Wm3WZYTPz0wzccnW";
        try{
            wholeUrl=URLDecoder.decode(wholeUrl,"UTF-8");
        }catch (Exception ex){
            ex.printStackTrace();
        }
        long timestamp=System.currentTimeMillis()/1000;
        String str = "jsapi_ticket=" + getJsapiToken()
                + "&noncestr=" + noncestr + "&timestamp=" + timestamp
                + "&url=" + wholeUrl;
        String signature = SHA1(str);
        HashMap config=new HashMap();
        config.put("debug",debug);
        config.put("appId",gunsProperties.getAppId());
        config.put("timestamp",timestamp);
        config.put("nonceStr",noncestr);
        config.put("signature",signature);
        config.put("jsApiList",new String[]{"checkJsApi","scanQRCode"});

//        StringBuilder sb = new StringBuilder("{");
//        sb.append("debug:").append(debug).append(",")
//                .append("appId:'").append(gunsProperties.getAppId()).append("',")
//                .append("timestamp:").append(lastJsTokenTime / 1000).append(",")
//                .append("nonceStr:'").append(noncestr).append("',")
//                .append("signature:'").append(signature).append("',")
//                .append("jsApiList: ['checkJsApi','onMenuShareTimeline','onMenuShareAppMessage','onMenuShareQQ','onMenuShareWeibo','onMenuShareQZone','hideMenuItems','showMenuItems','hideAllNonBaseMenuItem','showAllNonBaseMenuItem','translateVoice','startRecord','stopRecord','onVoiceRecordEnd','playVoice','onVoicePlayEnd','pauseVoice','stopVoice','uploadVoice','downloadVoice','chooseImage','previewImage','uploadImage','downloadImage','getLocalImgData','getNetworkType','openLocation','getLocation','hideOptionMenu','showOptionMenu','closeWindow','scanQRCode','chooseWXPay','openProductSpecificView','addCard','chooseCard','openCard']}");
        return config;
    }

    private String SHA1(String str) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-1"); //如果是SHA加密只需要将"SHA-1"改成"SHA"即可
            digest.update(str.getBytes());
            byte messageDigest[] = digest.digest();
            StringBuffer hexStr = new StringBuffer();
            // 字节数组转换为 十六进制 数
            for (int i = 0; i < messageDigest.length; i++) {
                String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
                if (shaHex.length() < 2) {
                    hexStr.append(0);
                }
                hexStr.append(shaHex);
            }
            return hexStr.toString();
        } catch (NoSuchAlgorithmException e) {
            log.error("字符串加密出错", e);
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(new WechatTokenService().getJsapiToken());
    }
}
