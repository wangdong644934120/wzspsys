//package cn.stylefeng.guns.generator.modular.restful;
//
//import cn.stylefeng.guns.generator.core.generator.param.ContextParam;
//import cn.stylefeng.guns.generator.modular.restful.feign.FeignApiGenerator;
//import cn.stylefeng.guns.generator.modular.restful.feign.FeignProviderGenerator;
//import cn.stylefeng.guns.generator.modular.restful.mybatisplus.DefaultMpGenerator;
//import cn.stylefeng.guns.generator.modular.restful.mybatisplus.param.MpParam;
//import com.baomidou.mybatisplus.generator.config.po.TableInfo;
//
//import java.util.List;
//import java.util.Map;
//
///**
// * 测试的执行器
// *
// * @author fengshuonan
// * @date 2018-12-18-6:39 PM
// */
//public class RestfulApiExecutor {
//
//    /**
//     * 默认的生成器
//     *
//     * @author fengshuonan
//     * @Date 2019/1/13 22:18
//     */
//    public static void executor(ContextParam contextParam, MpParam mpContext) {
//
//        //执行mp的代码生成，生成entity,dao,service,model，生成后保留数据库元数据
//        DefaultMpGenerator defaultMpGenerator = new DefaultMpGenerator(mpContext);
//        defaultMpGenerator.initContext(contextParam);
//        defaultMpGenerator.doGeneration();
//
//        //获取元数据
//        List<TableInfo> tableInfos = defaultMpGenerator.getTableInfos();
//        Map<String, Map<String, Object>> everyTableContexts = defaultMpGenerator.getEveryTableContexts();
//
//        //遍历所有表
//        for (TableInfo tableInfo : tableInfos) {
//            Map<String, Object> map = everyTableContexts.get(tableInfo.getName());
//
//            //生成api接口
//            FeignApiGenerator feignApiGenerator = new FeignApiGenerator(map);
//            feignApiGenerator.initContext(contextParam);
//            feignApiGenerator.doGeneration();
//
//            //生成provider
//            FeignProviderGenerator feignProviderGenerator = new FeignProviderGenerator(map);
//            feignProviderGenerator.initContext(contextParam);
//            feignProviderGenerator.doGeneration();
//        }
//    }
//
//    public static void main(String[] args) {
//
//        ContextParam contextParam = new ContextParam();
//
//        contextParam.setJdbcDriver("com.mysql.jdbc.Driver");
//        contextParam.setJdbcUserName("root");
//        contextParam.setJdbcPassword("root");
//        contextParam.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/generator_platform?autoReconnect=true&useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=false&serverTimezone=CTT");
//        contextParam.setOutputPath("temp");
//        contextParam.setAuthor("fengshuonan");
//        contextParam.setProPackage("cn.stylefeng.guns.modular.test");
//
//        MpParam mpContextParam = new MpParam();
//        mpContextParam.setGeneratorInterface(true);
//        mpContextParam.setIncludeTables(new String[]{"test"});
//        mpContextParam.setRemoveTablePrefix(new String[]{"sys_"});
//
//        RestfulApiExecutor.executor(contextParam, mpContextParam);
//    }
//
//}
