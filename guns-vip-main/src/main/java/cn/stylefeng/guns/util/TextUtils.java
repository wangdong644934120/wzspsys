package cn.stylefeng.guns.util;


import cn.stylefeng.roses.core.util.MD5Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 文本处理工具
 * **/

public class TextUtils {

    private static final Logger logger = LoggerFactory.getLogger(TextUtils.class);

    public static String[] chars = new String[] {
            "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l",
            "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z",
            "0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
            "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L",
            "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z" };


    /**
     * 重写 equals
     * **/
    public static boolean equals(String str1, String str2){


        if (null == str1){
            return false;
        }
        if (null == str2){
            return false;
        }
        if (str1.equalsIgnoreCase(str2)){
            return true;
        }
        return false;
    }



    public static boolean isEmpty(String str){
        if (null == str){
            return true;
        }
        return str.trim() .equals("")?true:false;
    }

    public static int getIntegerValueOfString(String number) {
        if ( isNumber((number))) {
            return Integer.valueOf(number);
        }
        return 0;
    }

    public static String add(String number1, String number2) {
        if ( isNumber(number1) && isNumber(number2)){
            int number = Integer.valueOf(number1) + Integer.valueOf(number2);
            return String.valueOf(number);
        }
        return "0";
    }


    public static boolean isNotEmpty(String str){
        if (null == str){
            return false;
        }
        return str.trim() .equals("")?false:true;
    }


    public static boolean isChineseChar(char c) {
        return String.valueOf(c).matches("[\u4e00-\u9fa5]");
    }

    public static boolean isChineseChar(String str) {
        boolean b = false;
        if (str.length() > 1){
            for (int i=0;i<str.length();i++){
                char c = str.charAt(i);
                if (isChineseChar(c)){
                    return true;
                }
            }
        }
        return b;
    }

    public static boolean isNumber(String number) {
        if (null == number){
            return false;
        }
        return number.matches("[\\d]+");
    }

    public static boolean hasTableName(String str){

        return str.matches("([\u4e00-\u9fa5_-]+)([\\(\\（])([\\w_]+)([\\)\\）])" );
    }

    public static String getTableName(String str){
        String tablename = "";
        String pattern_tablename = "([\u4e00-\u9fa5_-]+)([\\(（])(?<tablename>.*)([\\)）])";


        if (true){
            Pattern pattern = Pattern.compile(pattern_tablename);
            Matcher matcher = pattern.matcher(str);
            if (matcher.find()){
                tablename = matcher.group("tablename");
            }
        }
        return tablename;
    }

    /**
     * 按字节截取字符串
     *
     * @param orignal
     *      原始字符串
     * @param count
     *      截取位数
     * @return 截取后的字符串

     *       使用了JAVA不支持的编码格式
     */
    public static String substring(String orignal, int count) {
        int chineseCount=0;
        int originalCount=count;
        StringBuffer buff=null;
        int currentCount=0;
        // 原始字符不为null，也不是空字符串
        try {
            if (orignal != null && !"".equals(orignal)) {
                // 将原始字符串转换为GBK编码格式
                orignal = new String(orignal.getBytes(), "UTF-8");//
                // 要截取的字节数大于0，且小于原始字符串的字节数
                if (count > 0 && count < orignal.getBytes("UTF-8").length) {
                    buff = new StringBuffer();
                    char c;
                    ok:
                    for (int i = 0; i < count - 1; i++) {
                        c = orignal.charAt(i);
                        //   System.out.println(count);

                        if (isChineseChar(c)) {
                            System.out.println(count);
                            System.out.println(buff);

                            // 遇到中文汉字，截取字节总数减2
                            if (count + chineseCount * 2 <= originalCount) {
                                buff.append(c);
                                count = count - 2;
                                ++chineseCount;
                                System.out.println(c);
                                System.out.println("中文的个数" + chineseCount);
                            } else if (count + (chineseCount) * 2 > originalCount) {
                                break ok;
                            }
                        } else {
                            buff.append(c);
                        }
                    }
                    //  System.out.println(new String(buff.toString().getBytes("GBK"),"UTF-8"));
                    return new String(buff.toString().getBytes(), "UTF-8");
                }
            }
        } catch (UnsupportedEncodingException e){

        }
        return orignal;
    }


    public static String is2StringByByte(InputStream inputStream){
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length;

        try {
            while ((length = inputStream.read(buffer)) != -1) {
                result.write(buffer, 0, length);
            }
            return result.toString("UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String is2String(InputStream inputStream){
        final int bufferSize = 1024;
        final char[] buffer = new char[bufferSize];
        final StringBuilder out = new StringBuilder();
        Reader in = null;
        try {
            in = new InputStreamReader(inputStream, "UTF-8");
            for (; ; ) {
                int rsz = in.read(buffer, 0, buffer.length);
                if (rsz < 0)
                    break;
                out.append(buffer, 0, rsz);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return out.toString();
    }


    public static String generateShortUuid() {
        StringBuffer shortBuffer = new StringBuffer();
        String uuid = UUID.randomUUID().toString().replace("-", "");
        for (int i = 0; i < 8; i++) {
            String str = uuid.substring(i * 4, i * 4 + 4);
            int x = Integer.parseInt(str, 16);
            shortBuffer.append(chars[x % 0x3E]);
        }
        return shortBuffer.toString();
    }

    public static String getUserTempToken(long userId){
       return getUserToken(String.valueOf(userId));
    }

    public static String getUserToken(String userId){

        long time = System.currentTimeMillis();
        String tempUUID8 = generateShortUuid();

        StringBuilder sb = new StringBuilder();
        sb.append(userId);
        sb.append(time);
        sb.append(tempUUID8);

        return MD5Utils.md5(sb.toString());
    }

    public static String getHtmlStringFromTextArea(String content){
        if (null != content){
            content =   content.replaceAll("\n", "<br />");

            content = content.trim();
        }

        return content;

    }
    public static String getHtmlStringToTextArea(String content){
        if (null != content){
            content =   content.replaceAll("<br />", "\n");

            content = content.trim();
        }
        return content;

    }
}
