package com.dadalang.x.util.tools;

import jdk.dynalink.beans.StaticClass;

import java.security.MessageDigest;
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
    public static String sha256(String raw) {
        String encoded = "";
        try{
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            digest.update(raw.getBytes("UTF-8"));
            encoded = byte2Hex(digest.digest());
        }catch(Exception e) {
            e.printStackTrace();
        }
        return encoded;
    }
    public static String byte2Hex(byte[] bytes) {
        StringBuilder builder = new StringBuilder();
        for (byte value : bytes) {
            String temp = Integer.toHexString(value & 0xFF);
            if (1 == temp.length()) {
                builder.append("0");
            }
            builder.append(temp);
        }
        return builder.toString();
    }
}
