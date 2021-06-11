package com.dadalang.x.util.tools;

import io.netty.util.internal.StringUtil;

/**
 * @version 1.0
 * @Author lj
 * @date 2021/6/11 4:47 下午
 * @desc
 */
public class Compare {

    /**
     * 字符串判空
     * @param str
     * @return 如果传入字符串对象为空或长度小于1，则返回true,反之则返回false。
     */
    public static boolean str(String str) {
        if (null == str || str.length() < 1) {
            return true;
        }else{
            return false;
        }
    }

    /**
     * 判断a是否大于b
     * @param a 比较数
     * @param b 被比较数
     * @return 如果a为空或b为空或a小于b,则返回false,反之则true。
     */
    public static boolean more(Integer a, Integer b) {
        if (null == a || null == b || a < b) {
            return false;
        }else{
            return true;
        }
    }
    public static boolean more(Double a, Double b) {
        if (null == a || null == b || a < b) {
            return false;
        }else{
            return true;
        }
    }

    /**
     * 判断a是否小于b
     * @param a
     * @param b
     * @return 如果a为空或b为空或a大于b，则返回false，反之则true。
     */
    public static boolean less(Integer a, Integer b) {
        if (null == a || null == b || a > b) {
            return false;
        }else{
            return true;
        }
    }
    public static boolean less(Double a, Double b) {
        if (null == a || null == b || a > b) {
            return false;
        }else{
            return true;
        }
    }

    /**
     * 比较是否相同
     * @param a
     * @param b
     * @return 如果a为空或b为空或a不等于b，返回false，反之则true。
     */
    public static boolean equal(Integer a, Integer b) {
        if (null == a || null == b || a != b) {
            return false;
        }else{
            return true;
        }
    }
    public static boolean equal(Double a, Double b) {
        if (null == a || null == b || a != b) {
            return false;
        }else{
            return true;
        }
    }
}
