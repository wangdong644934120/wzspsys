package cn.stylefeng.guns.generator.modular.controller;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.generator.core.util.DbUtil;
import cn.stylefeng.guns.generator.modular.entity.DatabaseInfo;
import cn.stylefeng.guns.generator.modular.mapper.DatabaseInfoMapper;
import cn.stylefeng.roses.core.reqres.response.SuccessResponseData;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

import static cn.stylefeng.guns.generator.modular.controller.GeneratorController.CONDITION_FIELDS;

/**
 * 代码生成控制器
 *
 * @author fengshuonan
 * @date 2019-01-30-2:39 PM
 */
@Controller
@RequestMapping("/db")
public class DatabaseController {

    @Autowired
    private DatabaseInfoMapper databaseInfoMapper;

    /**
     * 数据库管理主页
     *
     * @author fengshuonan
     * @Date 2019/1/30 2:49 PM
     */
    @RequestMapping("")
    public String index() {
        return "/db/db.html";
    }

    /**
     * 新增页面
     *
     * @author fengshuonan
     * @Date 2019-01-11
     */
    @RequestMapping("/add")
    public String add() {
        return "/db/db_add.html";
    }

    /**
     * 新增
     *
     * @author fengshuonan
     * @Date 2019-01-11
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public Object addItem(DatabaseInfo databaseInfo) {
        this.databaseInfoMapper.insert(databaseInfo);
        return new SuccessResponseData();
    }

    /**
     * 删除
     *
     * @author fengshuonan
     * @Date 2019-01-11
     */
    @RequestMapping("/delete")
    @ResponseBody
    public Object delete(Long dbId) {
        this.databaseInfoMapper.deleteById(dbId);
        return new SuccessResponseData();
    }

    /**
     * 获取数据源列表
     *
     * @author fengshuonan
     * @Date 2019/1/30 2:49 PM
     */
    @RequestMapping("/list")
    @ResponseBody
    public Object list() {
        List<DatabaseInfo> all = databaseInfoMapper.selectList(new QueryWrapper<>());

        Page<DatabaseInfo> objectPage = new Page<>();
        objectPage.setRecords(all);

        return LayuiPageFactory.createPageInfo(objectPage);
    }

    /**
     * 获取某个数据源下的所有表
     *
     * @author fengshuonan
     * @Date 2019/1/30 2:49 PM
     */
    @RequestMapping("/tableList")
    @ResponseBody
    public Object tableList(Long dbId, HttpServletRequest request) {

        if (ToolUtil.isEmpty(dbId)) {
            return new LayuiPageInfo();
        }

        //清空session中的字段条件信息
        HttpSession session = request.getSession();
        session.removeAttribute(CONDITION_FIELDS);

        try {
            DatabaseInfo databaseInfo = databaseInfoMapper.selectById(dbId);
            List<Map<String, Object>> maps = DbUtil.selectTables(databaseInfo);
            Page<Map<String, Object>> objectPage = new Page<>();
            objectPage.setRecords(maps);
            return LayuiPageFactory.createPageInfo(objectPage);
        } catch (Exception e) {
            Page<Map<String, Object>> objectPage = new Page<>();
            return LayuiPageFactory.createPageInfo(objectPage);
        }
    }

}
