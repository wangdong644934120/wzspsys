package cn.stylefeng.guns.modular.sbdsys.mapper;

import cn.stylefeng.guns.modular.sbdsys.entity.UserExt;
import cn.stylefeng.guns.modular.sbdsys.model.params.UserExtParam;
import cn.stylefeng.guns.modular.sbdsys.model.result.UserExtResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 联系人表 Mapper 接口
 * </p>
 *
 * @author Admin
 * @since 2019-09-06
 */
public interface UserExtMapper extends BaseMapper<UserExt> {

    /**
     * 获取列表
     *
     * @author Admin
     * @Date 2019-09-06
     */
    List<UserExtResult> customList(@Param("paramCondition") UserExtParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author Admin
     * @Date 2019-09-06
     */
    Page<UserExtResult> customPageList(@Param("page") Page page, @Param("paramCondition") UserExtParam paramCondition);

    List<UserExtResult> getByPhone(@Param("paramCondition") UserExtParam paramCondition);
}
