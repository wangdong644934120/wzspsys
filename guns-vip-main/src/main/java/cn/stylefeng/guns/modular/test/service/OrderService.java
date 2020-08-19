package cn.stylefeng.guns.modular.test.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.test.entity.Order;
import cn.stylefeng.guns.modular.test.mapper.OrderMapper;
import cn.stylefeng.guns.modular.test.model.params.OrderParam;
import cn.stylefeng.guns.modular.test.model.result.OrderResult;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 订单表
 服务实现类
 * </p>
 *
 * @author stylefeng
 * @since 2019-11-29
 */
@Service
public class OrderService extends ServiceImpl<OrderMapper, Order> {

    //新增
    public void add(OrderParam param){
        this.save(param);
    }
    //删除
    public void delete(OrderParam param){
        this.removeById(getKey(param));
    }
    //更新
    public void update(OrderParam param){
        this.updateById(param);
    }
    //查询单条数据，Specification模式
    public OrderResult findBySpec(OrderParam param){
        List<OrderResult> list=this.findListBySpec(param);
        if(list==null||list.isEmpty()){
            return null;
        }
        return list.get(0);
    }
    //查询列表，Specification模式
    public List<OrderResult> findListBySpec(OrderParam param){

        return this.baseMapper.customList(param);
    }
    //查询分页数据，Specification模式
    public LayuiPageInfo findPageBySpec(OrderParam param){
        Page pageContext = LayuiPageFactory.defaultPage();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        LayuiPageInfo lpi=LayuiPageFactory.createPageInfo(page);
        return lpi;
    }

    private Serializable getKey(OrderParam param){
        return param.getId();
    }

}
