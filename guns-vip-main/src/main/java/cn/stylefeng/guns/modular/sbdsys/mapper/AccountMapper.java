package cn.stylefeng.guns.modular.sbdsys.mapper;

import cn.stylefeng.guns.modular.sbdsys.entity.Account;
import cn.stylefeng.guns.modular.sbdsys.model.params.AccountParam;
import cn.stylefeng.guns.modular.sbdsys.model.result.AccountResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 账户表 Mapper 接口
 * </p>
 *
 * @author Admin
 * @since 2019-09-06
 */
public interface AccountMapper extends BaseMapper<Account> {

    /**
     * 获取列表
     *
     * @author Admin
     * @Date 2019-09-06
     */
    List<AccountResult> customList(@Param("paramCondition") AccountParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author Admin
     * @Date 2019-09-06
     */
    Page<AccountResult> customPageList(@Param("page") Page page, @Param("paramCondition") AccountParam paramCondition);

}
