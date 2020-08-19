package cn.stylefeng.guns.modular.sbdsys.mapper;

import cn.stylefeng.guns.modular.sbdsys.entity.Store;
import cn.stylefeng.guns.modular.sbdsys.model.params.StoreParam;
import cn.stylefeng.guns.modular.sbdsys.model.result.StoreResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 商家表 Mapper 接口
 * </p>
 *
 * @author Admin
 * @since 2019-09-06
 */
public interface StoreMapper extends BaseMapper<Store> {

    /**
     * 获取列表
     *
     * @author Admin
     * @Date 2019-09-06
     */
    List<StoreResult> customList(@Param("paramCondition") StoreParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author Admin
     * @Date 2019-09-06
     */
    Page<StoreResult> customPageList(@Param("page") Page page, @Param("paramCondition") StoreParam paramCondition);

}
