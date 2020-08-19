package cn.stylefeng.guns.util;

import cn.stylefeng.guns.modular.sbdsys.entity.Census;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CensusUtils {
    public static void asort(List<Census> list, final String comparedColumn) {

       Collections.sort(list, new Comparator<Census>() {
           @Override
           public int compare(Census o1, Census o2) {
               int diff = 0;
               if (TextUtils.equals("month", comparedColumn)){
                   diff = o1.getMonthScore() - o2.getMonthScore();
               }
               if (TextUtils.equals("year", comparedColumn)){
                   diff = o1.getYearScore() - o2.getYearScore();
               }
               if (TextUtils.equals("score", comparedColumn)){
                   diff = o1.getScore() - o2.getScore();
               }
               if (diff > 0){
                   return  1;
               } else if (diff < 0){
                   return -1;
               }
               return 0;
           }
       });
    }

    public static void dsort(List<Census> list, final String comparedColumn) {

        Collections.sort(list, new Comparator<Census>() {
            @Override
            public int compare(Census o1, Census o2) {
                int diff = 0;
                if (TextUtils.equals("month", comparedColumn)){
                    diff = o1.getMonthScore() - o2.getMonthScore();
                }
                if (TextUtils.equals("year", comparedColumn)){
                    diff = o1.getYearScore() - o2.getYearScore();
                }
                if (TextUtils.equals("score", comparedColumn)){
                    diff = o1.getScore() - o2.getScore();
                }
                if (diff > 0){
                    return  -1;
                } else if (diff < 0){
                    return 1;
                }
                return 0;
            }
        });
    }
}
