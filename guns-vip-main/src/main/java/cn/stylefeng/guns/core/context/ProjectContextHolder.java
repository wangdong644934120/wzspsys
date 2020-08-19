package cn.stylefeng.guns.core.context;

public class ProjectContextHolder {

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
