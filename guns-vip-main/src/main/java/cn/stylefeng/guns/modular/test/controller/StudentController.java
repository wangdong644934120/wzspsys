package cn.stylefeng.guns.modular.test.controller;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.test.entity.Student;
import cn.stylefeng.guns.modular.test.model.params.StudentParam;
import cn.stylefeng.guns.modular.test.model.result.StudentResult;
import cn.stylefeng.guns.modular.test.service.StudentService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 学生表控制器
 *
 * @author wangdong
 * @Date 2019-12-24 14:46:14
 */
@Controller
@RequestMapping("/student")
public class StudentController extends BaseController {

    private String PREFIX = "/student";

    @Autowired
    private StudentService studentService;

    /**
     * 跳转到主页面
     *
     * @author wangdong
     * @Date 2019-12-24
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/student.html";
    }

    /**
     * 新增页面
     *
     * @author wangdong
     * @Date 2019-12-24
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/student_add.html";
    }

    /**
     * 编辑页面
     *
     * @author wangdong
     * @Date 2019-12-24
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/student_edit.html";
    }

    /**
     * 新增接口
     *
     * @author wangdong
     * @Date 2019-12-24
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(StudentParam studentParam) {
        this.studentService.add(studentParam);
        return ResponseData.success();
    }

    /**
     * 编辑接口
     *
     * @author wangdong
     * @Date 2019-12-24
     */
    @RequestMapping("/editItem")
    @ResponseBody
    public ResponseData editItem(StudentParam studentParam) {
        this.studentService.update(studentParam);
        return ResponseData.success();
    }

    /**
     * 删除接口
     *
     * @author wangdong
     * @Date 2019-12-24
     */
    @RequestMapping("/delete")
    @ResponseBody
    public ResponseData delete(StudentParam studentParam) {
        this.studentService.delete(studentParam);
        return ResponseData.success();
    }

    /**
     * 查看详情接口
     *
     * @author wangdong
     * @Date 2019-12-24
     */
    @RequestMapping("/detail")
    @ResponseBody
    public ResponseData detail(StudentParam studentParam) {
        StudentResult detail = this.studentService.findBySpec(studentParam);
        return ResponseData.success(detail);
    }

    /**
     * 查询列表
     *
     * @author wangdong
     * @Date 2019-12-24
     */
    @ResponseBody
    @RequestMapping("/list")
    public LayuiPageInfo list(StudentParam studentParam) {
        return this.studentService.findPageBySpec(studentParam);
    }

}


