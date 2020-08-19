package cn.stylefeng.guns.modular.sbdsys.mapper;

import cn.stylefeng.guns.modular.sbdsys.entity.BranchCommunity;
import cn.stylefeng.guns.modular.sbdsys.model.params.BranchCommunityParam;
import cn.stylefeng.guns.modular.sbdsys.model.result.BranchCommunityResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 社区党支部关联表 Mapper 接口
 * </p>
 *
 * @author Admin
 * @since 2019-09-06
 */
public interface BranchCommunityMapper extends BaseMapper<BranchCommunity> {

    /**
     * 获取列表
     *
     * @author Admin
     * @Date 2019-09-06
     */
    List<BranchCommunityResult> customList(@Param("paramCondition") BranchCommunityParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author Admin
     * @Date 2019-09-06
     */
    Page<BranchCommunityResult> customPageList(@Param("page") Page page, @Param("paramCondition") BranchCommunityParam paramCondition);

    void deleteByBranch(@Param("branch")String branch);
}
