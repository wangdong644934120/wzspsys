package cn.stylefeng.guns.sys.config.datasource;

import cn.stylefeng.guns.sys.core.shiro.ShiroKit;
import cn.stylefeng.roses.core.metadata.CustomMetaObjectHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * mp的插件拓展
 *
 * @author fengshuonan
 * @Date 2019/5/10 21:33
 */
@Configuration
public class MpPluginsConfig {

    /**
     * 拓展核心包中的字段包装器
     *
     * @author fengshuonan
     * @Date 2019/5/10 21:35
     */
    @Bean
    public CustomMetaObjectHandler gunsMpFieldHandler() {
        return new CustomMetaObjectHandler() {

            @Override
            protected Object getUserUniqueId() {
                try {

                    return ShiroKit.getUser().getId();

                } catch (Exception e) {

                    //如果获取不到当前用户就存空id
                    return "";
                }
            }
        };
    }

}
