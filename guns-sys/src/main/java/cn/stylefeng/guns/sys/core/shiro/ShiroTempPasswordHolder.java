package cn.stylefeng.guns.sys.core.shiro;

/**
 * @Description: TODO
 * @Duthor: lichenfeng
 * @Date: 2019/9/23 9:07
 * @Version 1.0
 */
public class ShiroTempPasswordHolder {
    private static final ThreadLocal contextHolder=new ThreadLocal();           //本地线程键值

    /**
     *
     * @param key
     */
    public static void set(String key){
        contextHolder.set(key);
    }

    /**
     *
     * @return
     */
    public static String get(){
        return (String) contextHolder.get();
    }

    /**
     *
     */
    public static void clear(){
        contextHolder.remove();
    }
}
