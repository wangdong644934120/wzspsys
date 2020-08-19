package cn.stylefeng.guns.modular.test.mapper;

import cn.stylefeng.guns.modular.test.entity.Books;
import cn.stylefeng.guns.modular.test.model.params.BooksParam;
import cn.stylefeng.guns.modular.test.model.result.BooksResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 书籍表
 Mapper 接口
 * </p>
 *
 * @author wangdong
 * @since 2019-12-23
 */
public interface BooksMapper extends BaseMapper<Books> {

    /**
     * 获取列表
     *
     * @author wangdong
     * @Date 2019-12-23
     */
    List<BooksResult> customList(@Param("paramCondition") BooksParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author wangdong
     * @Date 2019-12-23
     */
    Page<BooksResult> customPageList(@Param("page") Page page, @Param("paramCondition") BooksParam paramCondition);

}
