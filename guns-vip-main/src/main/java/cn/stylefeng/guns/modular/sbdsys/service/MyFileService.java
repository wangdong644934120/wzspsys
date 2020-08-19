package cn.stylefeng.guns.modular.sbdsys.service;

import cn.stylefeng.guns.modular.sbdsys.entity.*;
import cn.stylefeng.guns.modular.sbdsys.mapper.MineMapper;
import cn.stylefeng.guns.modular.sbdsys.mapper.MyFileMapper;
import cn.stylefeng.guns.sys.modular.system.entity.FileInfo;
import cn.stylefeng.guns.sys.modular.system.mapper.FileInfoMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MyFileService {

    @Resource
    MyFileMapper myFileMapper;

    public MyFile selectFileHasUploaded(MyFile myFile){
        return myFileMapper.selectFileHasUploaded(myFile);
    };

    public MyFile selectFileTempIdFromObj(MyFile myFile){
        return myFileMapper.selectFileTempIdFromObj(myFile);
    };




    public int insertMyFileInfo(MyFile myFile){
        return myFileMapper.insertMyFileInfo(myFile);
    }


    public int insertFileWith(MyFile myFile) {
        return myFileMapper.insertFileWith(myFile);
    }


    public List<MyFile> selectFileTemp(MyFile myFile){
        return myFileMapper.selectFileTemp(myFile);
    }

    public List<MyFile> selectFileWith(MyFile myFile){
        return myFileMapper.selectFileWithList(myFile);
    }

    public void updateFileWith(MyFile myFile){
        myFileMapper.updateFileWith(myFile);
    }


    public void deleteMyFile(MyFile myFile){
        myFileMapper.deleteFile(myFile);
    }

    public void deleteFileWith(MyFile myFile){
        myFileMapper.deleteFileWith(myFile);
    }

}
