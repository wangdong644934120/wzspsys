package cn.stylefeng.guns.sys.config.datasource;

import cn.stylefeng.roses.core.util.SpringContextHolder;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.IOException;

/**
 * @Description: TODO
 * @Duthor: lichenfeng
 * @Date: 2019/9/9 17:34
 * @Version 1.0
 */
@Configuration
@AutoConfigureAfter(SqlSessionFactory.class)
public class MybatisPlusHotConfig {

    @Value("${mybatis-plus.mapper-locations}")
    private String mapperLocations;

    @Value("${mybatis-plus.refresh-mapper}")
    private Boolean refreshMapper;

    private static final ResourcePatternResolver resourceResolver = new PathMatchingResourcePatternResolver();

    /**
     * mapper.xml 热加载
     * @return
     */
    @Bean
    public MybatisMapperRefresh mybatisMapperRefresh(){
        SqlSessionFactory sqlSessionFactory = (SqlSessionFactory) SpringContextHolder.getBean("sqlSessionFactory");
        Resource[] resources = new Resource[0];
        try {
            resources = resourceResolver.getResources(mapperLocations);
        } catch (IOException e) {
            e.printStackTrace();
        }
        MybatisMapperRefresh mybatisMapperRefresh = new MybatisMapperRefresh(resources,sqlSessionFactory,10,5,refreshMapper);
        return mybatisMapperRefresh;
    }
}