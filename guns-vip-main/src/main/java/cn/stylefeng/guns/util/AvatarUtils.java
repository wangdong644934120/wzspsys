package cn.stylefeng.guns.util;

import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.ImageUtil;
import cn.stylefeng.guns.modular.sbdsys.entity.MyFile;
import cn.stylefeng.guns.sys.modular.system.entity.FileInfo;
import cn.stylefeng.guns.sys.modular.system.service.FileInfoService;
import cn.stylefeng.roses.kernel.model.exception.ServiceException;
import cn.stylefeng.roses.kernel.model.exception.enums.CoreExceptionEnum;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.xml.soap.Text;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

@Repository
public class AvatarUtils {

    private static String DEFAULT_AVATAR_IMAGES = "images/default-images-3.png";

    private static long DEFAULT_AVATAR_SIZE = 10000;
    private static long DEFAULT_IMAGE_SIZE = 80000;

    @Autowired
    FileInfoService fileInfoService;

    public static byte[] getAvatarImageFromFilePath(String filePath) throws FileNotFoundException {

        if (TextUtils.isEmpty( filePath)) {
            String path = "";
            try {
                path = ResourceUtils.getURL("classpath:").getPath();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            filePath = path + DEFAULT_AVATAR_IMAGES;
        }

        File file = new File(filePath);
        if (file.exists() && file.isFile()){
          return IoUtil.readBytes(new FileInputStream(filePath));
        }

        return null;
    }


    public static String getDatePath(String path){
        String date = DateUtils.getCurrentDate("yyyy-MM-dd");
        System.out.println(date);
        String year = date.substring(0, 4);
        String month = date.substring(5, 7);
        String day = date.substring(8, 10);

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(path);
        stringBuilder.append(File.separator);
        stringBuilder.append(year);
        stringBuilder.append(File.separator);
        stringBuilder.append(month);
        stringBuilder.append(File.separator);

        File dirPath = new File(stringBuilder.toString());

        if (! dirPath.isDirectory()) {
            dirPath.mkdirs();
        }

        return stringBuilder.toString();
    }

    public static String getFileExt(String fileName){
        int pos = fileName.lastIndexOf(".");
        //判断点的位置
        if ( pos < 0 )
            return "";
        //获得文件的扩展名,png,jpg等
        return fileName.substring(pos+1).toLowerCase();
    }

    public static String getServerName(String ext){
       StringBuilder sb = new StringBuilder();

       String name = TextUtils.getUserToken("");

       sb.append(name);
       sb.append(".");
       sb.append(ext);

       return sb.toString();
    }


    public static int isImage(String ext){
        String imageString = ",jpg,jpeg,png,gif,bmp,";
        if (TextUtils.isNotEmpty(ext)){
            int pos = imageString.indexOf(ext);
            if (pos > 0)
                return 1;
        }
        return 0;
    }

    public static void compressImage(MyFile file){
        long size = file.getFileSize();
        long width = file.getWidth();
        long height = file.getHeight();

        String serverPath = file.getServerPath();
        String serverName = file.getServerName();
        String imageName = file.getImageName();

        System.out.println(" size ;"+size);

        if (size > DEFAULT_IMAGE_SIZE) {
            float rate  = (float)( (int)( ( DEFAULT_IMAGE_SIZE  * 100)  / size ) ) / 100 ;



            System.out.println(" rate ;"+rate);

            imageName = "m_"+imageName;

            File soureFile = new File(serverPath + serverName);

            File targetFile = new File(serverPath + imageName);

            try {
                Thumbnails.of(soureFile).scale(1f).outputQuality(rate).toFile(targetFile);

                file.setImageName(imageName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        String fullName = serverPath + imageName;

        file.setImgBase64(ImageBase64.getPhotoBase64(fullName));
    }


    public static MyFile getFileInfo(MultipartFile file, String rootPath, String tempId){
        String fileName = file.getOriginalFilename();
        String fileExt = AvatarUtils.getFileExt(fileName);
        int isImage = AvatarUtils.isImage(fileExt);
        long size = file.getSize();


        String serverPath = AvatarUtils.getDatePath(rootPath);
        String serverName = AvatarUtils.getServerName(fileExt);
        String fileSavePath = serverPath + serverName;

        String hashStr = MD5Utils.md5(fileName + tempId );

//        String imageName = serverName;


        MyFile myFile = new MyFile();

        String currentDate = DateUtils.getCurrentDate();

        myFile.setFileName(fileName);
        myFile.setServerPath(serverPath);
        myFile.setServerName(serverName);
        myFile.setImageName(serverName);
        myFile.setFullName(fileSavePath);
        myFile.setExt(fileExt);
        myFile.setFileSize(size);
        myFile.setIsImage(isImage);
        myFile.setIsDel(0);
        myFile.setHash(hashStr);
        myFile.setTempId(tempId);
        myFile.setWidth(0);
        myFile.setHeight(0);
        myFile.setCreateDate(currentDate);


        return myFile;
    }

}
