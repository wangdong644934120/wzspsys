package cn.stylefeng.guns.modular.sbdsys.mapper;

import cn.stylefeng.guns.modular.sbdsys.entity.*;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 账户表 Mapper 接口
 * </p>
 *
 * @author Admin
 * @since 2019-09-06
 */
public interface MyFileMapper {

   int insertMyFileInfo(@Param("file") MyFile myFile);

   List<MyFile> selectMyFile(@Param("file") MyFile myFile);


   MyFile selectFileHasUploaded(@Param("file") MyFile myFile);

   MyFile selectFileTempIdFromObj(@Param("file") MyFile myFile);

   List<MyFile> selectFileWithList(@Param("file") MyFile myFile);


   List<MyFile> selectFileTemp(@Param("file") MyFile myFile);


   int deleteFile(@Param("file") MyFile myFile);


   int deleteFileWith(@Param("file") MyFile myFile);


   int insertObjFileWithList(@Param("file") MyFile myFile);


   int insertFileWith(@Param("file") MyFile myFile);


   int updateFileWith(@Param("file") MyFile myFile);
}
