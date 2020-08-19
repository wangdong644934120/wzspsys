package cn.stylefeng.guns.modular.sbdsys.mapper;

import cn.stylefeng.guns.modular.sbdsys.entity.Account;
import cn.stylefeng.guns.modular.sbdsys.entity.CoinRecord;
import cn.stylefeng.guns.modular.sbdsys.entity.Commodity;
import cn.stylefeng.guns.modular.sbdsys.entity.ExchangeRecord;
import cn.stylefeng.guns.modular.sbdsys.model.params.ExchangeRecordParam;
import cn.stylefeng.guns.modular.sbdsys.model.result.ExchangeRecordResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 消费记录 Mapper 接口
 * </p>
 *
 * @author Admin
 * @since 2019-09-06
 */
public interface ExchangeRecordMapper extends BaseMapper<ExchangeRecord> {

    /**
     * 获取列表
     *
     * @author Admin
     * @Date 2019-09-06
     */
    List<ExchangeRecordResult> customList(@Param("paramCondition") ExchangeRecordParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author Admin
     * @Date 2019-09-06
     */
    Page<ExchangeRecordResult> customPageList(@Param("page") Page page, @Param("paramCondition") ExchangeRecordParam paramCondition);

    Account getAccountWithTransaction(@Param("userId") Long userId);

    Commodity getCommodityWithTransaction(@Param("commodityId")String commodityId);

    void updateCommodity(@Param("commodity")Commodity commodity);

    void updateAccount(@Param("account")Account account);

    void insertExchangeRecord(@Param("exchangeRecord")ExchangeRecord exchangeRecord);

    void insertCoinRecord(@Param("coinRecord")CoinRecord coinRecord);

    Commodity getCommodityByUserId(@Param("commodityId")String commodityId,@Param("userId")Long userId);
}
