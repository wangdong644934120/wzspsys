package cn.stylefeng.guns.util.wechat;


import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import javax.xml.soap.Text;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

@Repository
public class WechatUtils {

    @Value("${guns.appId}")
    private String appId;


    @Value("${guns.appSecret}")
    private String appSecret;

    public String getTokenAccess(){
//        HashMap<String, Object> paramMap = new HashMap<>();
//        paramMap.put("city", "北京");
//        String result= HttpUtil.post("https://www.baidu.com", paramMap);
        System.out.println(" appId:"+appId);
        System.out.println(" appSecret:"+appSecret);

        String accessTokenUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+appId+"&secret="+appSecret;
//        String accessTokenUrl = "https://api.weixin.qq.com/cgi-bin/token";

        String result = HttpUtil.get(accessTokenUrl);

        HashMap paramMap = new HashMap();
        paramMap.put("grant_type", "client_credential");
        paramMap.put("appid", "appId");
        paramMap.put("secret", "appSecret");

//        String result = HttpUtil.get(accessTokenUrl, paramMap);


//        String result = HttpRequest.post(accessTokenUrl).form(paramMap).timeout(2000).execute().body();
        System.out.println(result);

        JSONObject jsonObject = JSONUtil.parseObj(result);

        String access_token = (String) jsonObject.get("access_token");

        System.out.println(" access token ："+access_token);

        System.out.println(access_token.length());

        return access_token;
    }

//    public void getMiniProgramQr2 (String sceneStr) {
//        try {
//            String access_token = getTokenAccess();
//            Map<String, Object> params = new HashMap<>();
//            params.put("scene", "test");
//            params.put("page", "pages/index/index");
////            params.put("access_token", access_token);
//            params.put("width", 430);
//
//            CloseableHttpClient httpClient = HttpClientBuilder.create().build();
//
//            HttpPost httpPost = new HttpPost("https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token="+access_token);
//            httpPost.addHeader(HTTP.CONTENT_TYPE, "application/json");
//            String body = JSON.toJSONString(params);
//            StringEntity entity;
//            entity = new StringEntity(body);
//            entity.setContentType("image/png");
//
//            httpPost.setEntity(entity);
//            HttpResponse response;
//
//            response = httpClient.execute(httpPost);
//            InputStream inputStream = response.getEntity().getContent();
//
//            System.out.println("返回结果：");
//            System.out.println(response.getEntity());
//            System.out.println(response.getEntity().getContentType());
//            System.out.println(response.getEntity().getContent());
//            System.out.println(response.getEntity().getContentLength());
//            System.out.println(response.getEntity().getContentEncoding());
//
//            int contentLen = (int) response.getEntity().getContentLength();
//
//            if (contentLen > 100) {
//                WechatFileUtils.saveToImgByInputStream(inputStream, "E:\\test", "hi2.png");
//            } else {
//                String rs = TextUtils.is2StringByByte(inputStream);
//                System.out.println(rs);
//
////                WechatResult w = (WechatResult) com.alibaba.fastjson.JSONObject.parse(rs);
//                String errmsg = (String) JSONUtil.parseObj(rs).get("errmsg");
//
//                System.out.println(errmsg);
//
//
//
//            }
//
//
//
////            WechatFileUtils.saveToImgByInputStream(inputStream, "E:\\test", "hi2.png");
////            File targetFile = new File("E:\\");
////            if(!targetFile.exists()){
////                targetFile.mkdirs();
////            }
////            FileOutputStream out = new FileOutputStream("E:\\test\\5.png");
////
////            byte[] buffer = new byte[8192];
////            int bytesRead = 0;
////            while((bytesRead = inputStream.read(buffer, 0, 8192)) != -1) {
////                out.write(buffer, 0,bytesRead);
////            }
////            out.flush();
////            out.close();
//        } catch (Exception e) {
//
//        }
//    }

    public void getMiniProgramQr(String sceneStr) {
        String token = this.getTokenAccess();
        try {
           String wacodeunlimitURL = "https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token="+token;


            URL url = new URL(wacodeunlimitURL);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");// 提交模式
            // conn.setConnectTimeout(10000);//连接超时 单位毫秒
            // conn.setReadTimeout(2000);//读取超时 单位毫秒
            // 发送POST请求必须设置如下两行
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            PrintWriter printWriter = new PrintWriter(httpURLConnection.getOutputStream());
            // 发送请求参数
            JSONObject paramJson = new JSONObject();
            paramJson.put("scene", sceneStr);
//            paramJson.put("page", "pages/index/index");
//            paramJson.put("width", 430);
//            paramJson.put("auto_color", true);
            /**
             * line_color生效
             * paramJson.put("auto_color", false);
             * JSONObject lineColor = new JSONObject();
             * lineColor.put("r", 0);
             * lineColor.put("g", 0);
             * lineColor.put("b", 0);
             * paramJson.put("line_color", lineColor);
             * */
            printWriter.write(paramJson.toString());
            // flush输出流的缓冲
            printWriter.flush();

            System.out.println(httpURLConnection.getInputStream());
            System.out.println(httpURLConnection.getErrorStream());
            System.out.println(httpURLConnection.getResponseCode());
            System.out.println(httpURLConnection.getResponseMessage());





            WechatFileUtils.saveToImgByInputStream(httpURLConnection.getInputStream(), "E:\\test", "hi.png");
//            //开始获取数据
//            BufferedInputStream bis = new BufferedInputStream(httpURLConnection.getInputStream());
//            OutputStream os = new FileOutputStream(new File("e:\\test\\2.jpg"));
//            int len;
//            byte[] arr = new byte[1024];
//            while ((len = bis.read(arr)) != -1)
//            {
//                os.write(arr, 0, len);
//                os.flush();
//            }
//            os.close();

        } catch ( Exception e) {
            System.out.println("生成图片失败："+e.toString());
        }
    }
}
