package cn.stylefeng.guns.modular.sbdsys.controller;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.sbdsys.entity.ActivityType;
import cn.stylefeng.guns.modular.sbdsys.model.params.ActivityTypeParam;
import cn.stylefeng.guns.modular.sbdsys.model.result.ActivityTypeResult;
import cn.stylefeng.guns.modular.sbdsys.service.ActivityTypeService;
import cn.stylefeng.guns.sys.core.exception.enums.BizExceptionEnum;
import cn.stylefeng.guns.sys.core.properties.GunsProperties;
import cn.stylefeng.guns.sys.modular.system.entity.Dict;
import cn.stylefeng.guns.sys.modular.system.service.DictService;
import cn.stylefeng.guns.util.ImageBase64;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ResponseData;
import cn.stylefeng.roses.kernel.model.exception.ServiceException;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


/**
 * 活动类别信息表控制器
 *
 * @author Admin
 * @Date 2019-09-06 10:19:01
 */
@Controller
@RequestMapping("/activityType")
public class ActivityTypeController extends BaseController {

    private String PREFIX = "//activityType";

    @Autowired
    private ActivityTypeService activityTypeService;

    @Autowired
    private DictService dictService;

    @Autowired
    private GunsProperties gunsProperties;


    /**
     * 跳转到主页面
     *
     * @author Admin
     * @Date 2019-09-06
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/activityType.html";
    }

    /**
     * 新增页面
     *
     * @author Admin
     * @Date 2019-09-06
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/activityType_add.html";
    }

    /**
     * 编辑页面
     *
     * @author Admin
     * @Date 2019-09-06
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/activityType_edit.html";
    }

    /**
     * 新增接口
     *
     * @author Admin
     * @Date 2019-09-06
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(ActivityTypeParam activityTypeParam) {
        activityTypeParam.setCreateTime(new Date());
        activityTypeParam.setUpdateTime(new Date());
        this.activityTypeService.add(activityTypeParam);
        return ResponseData.success();
    }

    /**
     * 编辑接口
     *
     * @author Admin
     * @Date 2019-09-06
     */
    @RequestMapping("/editItem")
    @ResponseBody
    public ResponseData editItem(ActivityTypeParam activityTypeParam) {
        activityTypeParam.setCreateTime(new Date());
        activityTypeParam.setUpdateTime(new Date());
        this.activityTypeService.update(activityTypeParam);
        return ResponseData.success();
    }

    /**
     * 删除接口
     *
     * @author Admin
     * @Date 2019-09-06
     */
    @RequestMapping("/delete")
    @ResponseBody
    public ResponseData delete(ActivityTypeParam activityTypeParam) {
        this.activityTypeService.delete(activityTypeParam);
        return ResponseData.success();
    }

    /**
     * 查看详情接口
     *
     * @author Admin
     * @Date 2019-09-06
     */
    @RequestMapping("/detail")
    @ResponseBody
    public ResponseData detail(ActivityTypeParam activityTypeParam) {
        ActivityType detail = this.activityTypeService.getById(activityTypeParam.getId());
        return ResponseData.success(detail);
    }

    /**
     * 查询列表
     *
     * @author Admin
     * @Date 2019-09-06
     */
    @ResponseBody
    @RequestMapping("/list")
    public LayuiPageInfo list(ActivityTypeParam activityTypeParam) {
        LayuiPageInfo lpi = this.activityTypeService.findPageBySpec(activityTypeParam);
        return lpi ;
    }
    /**不分页查询
     * @Description
     * @Author lichenfeng
     * @Date 2019/9/6 17:09
     **/
    @RequestMapping("/listAll")
    @ResponseBody
    public ResponseData listAll(ActivityTypeParam activityTypeParam) {
        List<ActivityTypeResult> list = this.activityTypeService.findListBySpec(activityTypeParam);
        return ResponseData.success(list);
    }

    /**不分页查询
     * @Description
     * @Author lichenfeng
     * @Date 2019/9/6 17:09
     **/
    @RequestMapping("/getActivityTypeList")
    @ResponseBody
    public ResponseData getActivityTypeList(ActivityTypeParam activityTypeParam) {
        List<Dict> list = dictService.getDictListByType("ACTIVITY_TYPE");
        return ResponseData.success(list);
    }

    @RequestMapping("/uploadPicture")
    @ResponseBody
    public ResponseData uploadPicture(@RequestPart("file") MultipartFile file, HttpServletRequest request) {
        HashMap map = uploadFile(file, request, "png");
        return ResponseData.success(0, "上传成功", map);
    }

    /**
     * 上传文件方法
     *
     * @param file
     * @param request
     * @param fileType
     * @return
     */
    private HashMap uploadFile(@RequestPart("file") MultipartFile file, HttpServletRequest request, String fileType) {
        String idStr = IdWorker.getIdStr();
        String name = file.getOriginalFilename();
        request.getSession().setAttribute("upFile", name);
        String fileSaveFold = gunsProperties.getFileUploadPath() + PREFIX + "/";
        if (!new File(fileSaveFold).exists()) {
            new File(fileSaveFold).mkdirs();
        }
        String fileSavePath = gunsProperties.getFileUploadPath() + PREFIX + "/" + idStr + "." + fileType;
        try {
            file.transferTo(new File(fileSavePath));
        } catch (Exception e) {
            throw new ServiceException(BizExceptionEnum.UPLOAD_ERROR);
        }

        HashMap<String, Object> map = new HashMap<>();
        map.put("fileUUID", PREFIX + "/" + idStr + "." + fileType);
        if (!fileType.equals("dwg")) {
            map.put("imgBase64", ImageBase64.getPhotoBase64(fileSavePath));
        }
        return map;
    }


}


