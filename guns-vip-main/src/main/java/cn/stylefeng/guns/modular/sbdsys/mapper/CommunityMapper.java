package cn.stylefeng.guns.modular.sbdsys.mapper;

import cn.stylefeng.guns.modular.sbdsys.entity.Community;
import cn.stylefeng.guns.modular.sbdsys.model.params.CommunityParam;
import cn.stylefeng.guns.modular.sbdsys.model.result.CommunityResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 社区表 Mapper 接口
 * </p>
 *
 * @author Admin
 * @since 2019-09-06
 */
public interface CommunityMapper extends BaseMapper<Community> {

    /**
     * 获取列表
     *
     * @author Admin
     * @Date 2019-09-06
     */
    List<CommunityResult> customList(@Param("paramCondition") CommunityParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author Admin
     * @Date 2019-09-06
     */
    Page<CommunityResult> customPageList(@Param("page") Page page, @Param("paramCondition") CommunityParam paramCondition);

}
