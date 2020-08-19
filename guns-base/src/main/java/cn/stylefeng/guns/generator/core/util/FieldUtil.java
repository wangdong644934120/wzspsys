package cn.stylefeng.guns.generator.core.util;

import com.baomidou.mybatisplus.generator.config.po.TableField;

import java.util.ArrayList;
import java.util.List;

/**
 * 字段转化工具类
 *
 * @author fengshuonan
 * @Date 2019/5/7 22:12
 */
public class FieldUtil {

    /**
     * 将下划线字段数组，转化成驼峰式的字段数组
     *
     * @param underlineFields 下划线的字段数组
     * @param tableFields     带有驼峰式的字段数组
     * @author fengshuonan
     * @Date 2019/5/7 22:14
     */
    public static List<String> getCamelFields(String[] underlineFields, List<TableField> tableFields) {

        ArrayList<String> newFields = new ArrayList<>();

        if (underlineFields.length <= 0 || tableFields == null) {
            return newFields;
        } else {
            for (String underlineField : underlineFields) {
                for (TableField tableField : tableFields) {
                    if (underlineField.equals(tableField.getName())) {
                        newFields.add(tableField.getPropertyName());
                    }
                }
            }
            return newFields;
        }
    }

}
