package cn.stylefeng.guns.modular.sbdsys.controller;

import cn.stylefeng.guns.modular.sbdsys.entity.MyFile;
import cn.stylefeng.guns.modular.sbdsys.entity.Suggestion;
import cn.stylefeng.guns.modular.sbdsys.service.MyFileService;
import cn.stylefeng.guns.modular.sbdsys.service.SuggestService;
import cn.stylefeng.guns.sys.core.exception.enums.BizExceptionEnum;
import cn.stylefeng.guns.sys.core.properties.GunsProperties;
import cn.stylefeng.guns.sys.core.shiro.ShiroKit;
import cn.stylefeng.guns.util.AvatarUtils;
import cn.stylefeng.guns.util.DateUtils;
import cn.stylefeng.guns.util.ImageBase64;
import cn.stylefeng.guns.util.TextUtils;
import cn.stylefeng.roses.core.reqres.response.ResponseData;
import cn.stylefeng.roses.kernel.model.exception.ServiceException;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import java.util.List;

/**
 * 建议反馈 控制器
 *
 * @author Admin
 * @Date 2019-09-06 10:19:01
 */
@Controller
@RequestMapping("/myfile")
public class MyFileController {


    private String PREFIX = "/myupload";

    @Autowired
    private GunsProperties gunsProperties;
    @Autowired
    MyFileService myFileService;



    /**
     *  查询图片数据
     * @param tempId
     * @return
     */
    @RequestMapping("/selectTempFile")
    @ResponseBody
    public ResponseData getTempFile(@RequestParam(value = "tempId", required = true) String tempId) {
        MyFile myFile = new MyFile();

        System.out.println("objID ;;"+tempId);
        myFile.setTempId(tempId);

        List<MyFile> list = myFileService.selectFileTemp(myFile);

        int count = 0;
        if (null != list){
            for (MyFile file : list){
                String fullname = file.getServerPath() + file.getImageName();
                file.setImgBase64(ImageBase64.getPhotoBase64(fullname));
            }
        }

        HashMap rsMap = new HashMap();
        rsMap.put("list", list);
        rsMap.put("count", count);

        return ResponseData.success(rsMap);
    }

    /**
     *  查询图片数据
     * @param objId
     * @return
     */
    @RequestMapping("/selectFileWith")
    @ResponseBody
    public ResponseData getFileWithObj(@RequestParam(value = "objId", required = true) String objId) {
        MyFile myFile = new MyFile();

        System.out.println("objID ;;"+objId);
        myFile.setObjId(objId);

        List<MyFile> list = myFileService.selectFileWith(myFile);

        int count = 0;
        if (null != list){
            for (MyFile file : list){
                String fullname = file.getServerPath() + file.getImageName();
                file.setImgBase64(ImageBase64.getPhotoBase64(fullname));
            }
        }

        HashMap rsMap = new HashMap();
        rsMap.put("list", list);
        rsMap.put("count", count);

        return ResponseData.success(rsMap);
    }
    /***
     * 上传图片
     * @param request
     * @return
     */
    @RequestMapping("/deletePicture")
    @ResponseBody
    public ResponseData deletePicture( HttpServletRequest request) {
        String fid = request.getParameter("fid");

        System.out.println(fid);

        MyFile myFile = new MyFile();

        myFile.setFid(Integer.valueOf(fid));

        myFileService.deleteMyFile(myFile);
        myFileService.deleteFileWith(myFile);

        return ResponseData.success(0, "删除文件成功", null);
    }
    /**
     * 上传图片
     * @param file
     * @param request
     * @return
     */
    @RequestMapping("/uploadPicture")
    @ResponseBody
    public ResponseData uploadPicture(@RequestPart("file") MultipartFile file, HttpServletRequest request) {

        long userId = ShiroKit.getUser().getId();
        if (userId == 0){
            return ResponseData.error("请先登录");
        }
        String tempId = request.getParameter("tempId");
        String type = request.getParameter("type");
        String rootPath = gunsProperties.getFileUploadPath() +PREFIX;
        MyFile myFile = AvatarUtils.getFileInfo(file, rootPath, tempId);


        int isImage = myFile.getIsImage();

        if (isImage == 0){
            return ResponseData.error("只能上传图片格式的文件");
        }
        MyFile existsFile = myFileService.selectFileHasUploaded(myFile);

        if (existsFile.getCount() > 0){
            return ResponseData.error("请不要重复上传同一文件");
        }

        try {
            File targetFile = new File(myFile.getFullName());
            file.transferTo(targetFile);




            BufferedImage image = ImageIO.read(targetFile);

            int width = image.getWidth();
            int height = image.getHeight();

            myFile.setWidth(width);
            myFile.setHeight(height);


            AvatarUtils.compressImage(myFile);


            myFile.setCreateUser(userId);


            // 保存
            int i =  myFileService.insertMyFileInfo(myFile);

            int fid = myFile.getId();
            myFile.setFid(fid);
            MyFile fileWith = new MyFile();
            fileWith.setObjId(tempId);
            fileWith.setTempId(tempId);
            fileWith.setFid(fid);
            fileWith.setType(type);
            myFileService.insertFileWith(fileWith);
            int fileWithId = fileWith.getId();
            myFile.setFileWithId(fileWithId);

        } catch (Exception e) {
            throw new ServiceException(BizExceptionEnum.UPLOAD_ERROR);
        }

            return ResponseData.success(0, "上传成功", myFile);
    }


    /**
     * 上传图片
     * @param file
     * @param request
     * @return
     */
    @RequestMapping("/ApiUploadPicture")
    @ResponseBody
    public ResponseData ApiUploadPicture(@RequestPart("file") MultipartFile file, HttpServletRequest request) {


        String apiUserId = request.getParameter("userId");

        long userId = Long.valueOf(apiUserId);


        String tempId = request.getParameter("tempId");
        String type = request.getParameter("type");
        String rootPath = gunsProperties.getFileUploadPath() +PREFIX;
        MyFile myFile = AvatarUtils.getFileInfo(file, rootPath, tempId);

        System.out.println("正在上传文件..");


        int isImage = myFile.getIsImage();

        if (isImage == 0){
            return ResponseData.error("只能上传图片格式的文件");
        }
        MyFile existsFile = myFileService.selectFileHasUploaded(myFile);

        if (existsFile.getCount() > 0){
            return ResponseData.error("请不要重复上传同一文件");
        }

        try {
            File targetFile = new File(myFile.getFullName());
            file.transferTo(targetFile);





            BufferedImage image = ImageIO.read(targetFile);

            int width = image.getWidth();
            int height = image.getHeight();

            myFile.setWidth(width);
            myFile.setHeight(height);


            AvatarUtils.compressImage(myFile);


            myFile.setCreateUser(userId);


            System.out.println("上传文件完成. 正在保存数据.");

            System.out.println(myFileService);
            // 保存
            int i =  myFileService.insertMyFileInfo(myFile);

            int fid = myFile.getId();


            System.out.println("数据保存后获取的ID： "+fid);


            myFile.setFid(fid);
            MyFile fileWith = new MyFile();
            fileWith.setObjId(tempId);
            fileWith.setTempId(tempId);
            fileWith.setFid(fid);
            fileWith.setType(type);
            myFileService.insertFileWith(fileWith);
            int fileWithId = fileWith.getId();
            myFile.setFileWithId(fileWithId);

        } catch (Exception e) {
            throw new ServiceException(BizExceptionEnum.UPLOAD_ERROR);
        }

        return ResponseData.success(0, "上传成功", myFile);
    }


    /**
     * 上传文件方法
     *
     * @param file
     * @param request
     * @return
     */
    @RequestMapping("/uploadFile")
    @ResponseBody
    public ResponseData uploadFile(@RequestPart("file") MultipartFile file, HttpServletRequest request) {

        long userId = ShiroKit.getUser().getId();
        if (userId == 0){
            return ResponseData.error("请先登录");
        }
        String tempId = request.getParameter("tempId");
        String type = request.getParameter("type");
        String rootPath = gunsProperties.getFileUploadPath() +PREFIX;
        MyFile myFile = AvatarUtils.getFileInfo(file, rootPath, tempId);


        MyFile existsFile = myFileService.selectFileHasUploaded(myFile);

        if (existsFile.getCount() > 0){
            return ResponseData.error("请不要重复上传同一文件");
        }

        try {
            File targetFile = new File(myFile.getFullName());
            file.transferTo(targetFile);

            if (myFile.getIsImage() == 1) {
                BufferedImage image = ImageIO.read(targetFile);

                int width = image.getWidth();
                int height = image.getHeight();

                myFile.setWidth(width);
                myFile.setHeight(height);

                AvatarUtils.compressImage(myFile);
            }

            myFile.setCreateUser(userId);

            int i =  myFileService.insertMyFileInfo(myFile);

            MyFile fileWith = new MyFile();
            fileWith.setObjId(tempId);
            fileWith.setTempId(tempId);
            fileWith.setFid(i);
            fileWith.setType(type);
            myFileService.insertFileWith(fileWith);
        } catch (Exception e) {
            throw new ServiceException(BizExceptionEnum.UPLOAD_ERROR);
        }

        return ResponseData.success(myFile);
    }
}
