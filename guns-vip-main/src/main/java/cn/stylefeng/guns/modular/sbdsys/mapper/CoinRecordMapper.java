package cn.stylefeng.guns.modular.sbdsys.mapper;

import cn.stylefeng.guns.modular.sbdsys.entity.CoinRecord;
import cn.stylefeng.guns.modular.sbdsys.model.params.CoinRecordParam;
import cn.stylefeng.guns.modular.sbdsys.model.result.CoinRecordResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 先锋币收支记录 Mapper 接口
 * </p>
 *
 * @author Admin
 * @since 2019-09-06
 */
public interface CoinRecordMapper extends BaseMapper<CoinRecord> {

    /**
     * 获取列表
     *
     * @author Admin
     * @Date 2019-09-06
     */
    List<CoinRecordResult> customList(@Param("paramCondition") CoinRecordParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author Admin
     * @Date 2019-09-06
     */
    Page<CoinRecordResult> customPageList(@Param("page") Page page, @Param("paramCondition") CoinRecordParam paramCondition);

}
