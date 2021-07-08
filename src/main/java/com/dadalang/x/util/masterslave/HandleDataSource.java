package com.dadalang.x.util.masterslave;

/**
 * @version 1.0
 * @Author lj
 * @date 2021/7/5 3:07 下午
 * @desc
 */
public class HandleDataSource {
    public static final ThreadLocal<String> holder = new ThreadLocal<String>();

    public static void putDataSource(String datasource) {
        holder.set(datasource);
    }
    public static String getDataSource() {
        return holder.get();
    }
}
