package cn.stylefeng.guns.util;

import lombok.Data;

@Data
public class Page {
    private int pageIndex;
    private int pageSize;
    private int hasMore;
    private int totalSize;
    private int pagination;
    private int limit;
    private int orderby;


}
