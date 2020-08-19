package cn.stylefeng.guns.modular.test.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.test.entity.Books;
import cn.stylefeng.guns.modular.test.mapper.BooksMapper;
import cn.stylefeng.guns.modular.test.model.params.BooksParam;
import cn.stylefeng.guns.modular.test.model.result.BooksResult;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 书籍表
 服务实现类
 * </p>
 *
 * @author wangdong
 * @since 2019-12-23
 */
@Service
public class BooksService extends ServiceImpl<BooksMapper, Books> {

    //新增
    public void add(BooksParam param){
        this.save(param);
    }
    //删除
    public void delete(BooksParam param){
        this.removeById(getKey(param));
    }
    //更新
    public void update(BooksParam param){
        this.updateById(param);
    }
    //查询单条数据，Specification模式
    public BooksResult findBySpec(BooksParam param){
        List<BooksResult> list=this.findListBySpec(param);
        if(list==null||list.isEmpty()){
            return null;
        }
        return list.get(0);
    }
    //查询列表，Specification模式
    public List<BooksResult> findListBySpec(BooksParam param){
        return this.baseMapper.customList(param);
    }
    //查询分页数据，Specification模式
    public LayuiPageInfo findPageBySpec(BooksParam param){
        Page pageContext = LayuiPageFactory.defaultPage();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(BooksParam param){
        return param.getId();
    }

}
