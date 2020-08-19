package cn.stylefeng.guns.modular.sbdsys.entity;

import lombok.Data;

@Data
public class MyFile {
    private int id = 0;
    private String fileName = "";
    private String serverName = "";
    private String serverPath = "";
    private String fullName = "";
    private String imageName = "";
    private String ext = "";
    private long fileSize = 0;
    private int isImage = 0;
    private int width = 0;
    private int height = 0;
    private String createDate = "";
    private long createUser = 0;
    private String hash = "";
    private String tempId = "";
    private int isDel = 0;

    private int fid = 0;
    private int count = 0;
    private int fileWithId = 0;
    private String objId = "";
    private String type = "";
    private String imgBase64 = "";

}
