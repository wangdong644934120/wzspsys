package cn.stylefeng.guns.modular.sbdsys.mapper;

import cn.stylefeng.guns.modular.sbdsys.entity.PartyBranchPerson;
import cn.stylefeng.guns.modular.sbdsys.model.params.PartyBranchPersonParam;
import cn.stylefeng.guns.modular.sbdsys.model.result.PartyBranchPersonResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 党支部人员表 Mapper 接口
 * </p>
 *
 * @author Admin
 * @since 2019-09-06
 */
public interface PartyBranchPersonMapper extends BaseMapper<PartyBranchPerson> {

    /**
     * 获取列表
     *
     * @author Admin
     * @Date 2019-09-06
     */
    List<PartyBranchPersonResult> customList(@Param("paramCondition") PartyBranchPersonParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author Admin
     * @Date 2019-09-06
     */
    Page<PartyBranchPersonResult> customPageList(@Param("page") Page page, @Param("paramCondition") PartyBranchPersonParam paramCondition);

    List<PartyBranchPerson> getByUserId(@Param("paramCondition") PartyBranchPersonParam paramCondition);

    // 删除党员
    void deleteBranchPerson(@Param("paramCondition") PartyBranchPersonParam paramCondition);
}
