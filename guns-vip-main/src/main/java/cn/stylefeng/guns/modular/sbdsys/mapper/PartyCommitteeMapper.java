package cn.stylefeng.guns.modular.sbdsys.mapper;

import cn.stylefeng.guns.modular.sbdsys.entity.PartyCommittee;
import cn.stylefeng.guns.modular.sbdsys.model.params.PartyCommitteeParam;
import cn.stylefeng.guns.modular.sbdsys.model.result.PartyCommitteeResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 党委表 Mapper 接口
 * </p>
 *
 * @author Admin
 * @since 2019-09-06
 */
public interface PartyCommitteeMapper extends BaseMapper<PartyCommittee> {

    /**
     * 获取列表
     *
     * @author Admin
     * @Date 2019-09-06
     */
    List<PartyCommitteeResult> customList(@Param("paramCondition") PartyCommitteeParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author Admin
     * @Date 2019-09-06
     */
    Page<PartyCommitteeResult> customPageList(@Param("page") Page page, @Param("paramCondition") PartyCommitteeParam paramCondition);

}
