package cn.stylefeng.guns.modular.sbdsys.mapper;

import cn.stylefeng.guns.base.pojo.node.ZTreeNode;
import cn.stylefeng.guns.modular.sbdsys.entity.PartyBranch;
import cn.stylefeng.guns.modular.sbdsys.model.params.PartyBranchParam;
import cn.stylefeng.guns.modular.sbdsys.model.result.PartyBranchResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 党支部表 Mapper 接口
 * </p>
 *
 * @author Admin
 * @since 2019-09-06
 */
public interface PartyBranchMapper extends BaseMapper<PartyBranch> {

    /**
     * 获取列表
     *
     * @author Admin
     * @Date 2019-09-06
     */
    List<PartyBranchResult> customList(@Param("paramCondition") PartyBranchParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author Admin
     * @Date 2019-09-06
     */
    Page<PartyBranchResult> customPageList(@Param("page") Page page, @Param("paramCondition") PartyBranchParam paramCondition);

    List<ZTreeNode> communityTreeListByBranch(@Param("branch") String branch);
}
