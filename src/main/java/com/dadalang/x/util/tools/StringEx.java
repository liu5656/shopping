package com.dadalang.x.util.tools;

import java.util.Random;

/**
 * @version 1.0
 * @Author lj
 * @date 2021/5/24 4:57 下午
 * @desc
 */
public class StringEx {
    public static String randomNum(int count) {
        Random seed = new Random();
        String result = String.format("%0" + count + "d", seed.nextInt((count + 1) * 10 - 1));
        return result;
    }

}
