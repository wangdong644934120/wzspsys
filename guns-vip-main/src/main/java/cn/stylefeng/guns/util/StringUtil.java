package cn.stylefeng.guns.util;

public class StringUtil {

    public static String getNextID(String str,int length){
        //当前id+1
        Long maxIdl = new Long(str);
        maxIdl +=1l;
        //判断当前id位数
        String rtnString =String.format("%0"+length+"d", maxIdl);
        return rtnString;
    }
}
