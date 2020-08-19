package cn.stylefeng.guns.util;

public class PageUtils {
    private int totalSize;

    private int pageSize;

    private String orderby;

    public PageUtils(int totalSize){
        this(totalSize, 12, "");
    }

    public PageUtils(int totalSize,  int pageSize){
        this(totalSize, pageSize, "");
    }


    public PageUtils(int totalSize,  int pageSize, String orderby) {
        this.totalSize = totalSize;
        this.pageSize = pageSize;
        this.orderby = orderby;

    }


    public int getPagination(){
        int page = totalSize / pageSize;

        int modPage = totalSize % pageSize;

        if (modPage > 0){
            page = page + 1;
        }

        if (page == 0){
            page = 1;
        }


        return page;
    }

    public String getLimit(int pageIndex){

        pageIndex = pageIndex == 0 ? 1 : pageIndex;

        StringBuilder sb = new StringBuilder();
        int page = getPagination();

        pageIndex = pageIndex >= page ? page :pageIndex;

        sb.append( (pageIndex -1 ) * pageSize );
        sb.append(",");
        sb.append(pageSize);

        return sb.toString();
    }

    public int hasMore(int pageIndex) {
        int page = getPagination();
       return  pageIndex = pageIndex >= page ? 0 : 1;
    }
}
