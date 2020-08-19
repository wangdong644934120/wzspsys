package cn.stylefeng.guns.generator.modular.mapper;

import cn.stylefeng.guns.generator.modular.entity.DatabaseInfo;
import cn.stylefeng.guns.generator.modular.model.params.DatabaseInfoParam;
import cn.stylefeng.guns.generator.modular.model.result.DatabaseInfoResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 数据库信息表 Mapper 接口
 * </p>
 *
 * @author stylefeng
 * @since 2019-05-11
 */
public interface DatabaseInfoMapper extends BaseMapper<DatabaseInfo> {

    /**
     * 获取列表
     *
     * @author stylefeng
     * @Date 2019-05-11
     */
    List<DatabaseInfoResult> customList(DatabaseInfoParam paramCondition);

    /**
     * 获取map列表
     *
     * @author stylefeng
     * @Date 2019-05-11
     */
    List<Map<String, Object>> customMapList(DatabaseInfoParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author stylefeng
     * @Date 2019-05-11
     */
    Page<DatabaseInfoResult> customPageList(Page page, DatabaseInfoParam paramCondition);

    /**
     * 获取分页map列表
     *
     * @author stylefeng
     * @Date 2019-05-11
     */
    Page<Map<String, Object>> customPageMapList(Page page, DatabaseInfoParam paramCondition);

}
