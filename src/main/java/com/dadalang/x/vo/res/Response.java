package com.dadalang.x.vo.res;

import lombok.Data;

import java.io.Serializable;

/**
 * @version 1.0
 * @Author lj
 * @date 2021/5/17 4:41 下午
 */
@Data
public class Response<T> implements Serializable {
    private String code;
    private String msg;
    private T obj;

    public static Response success() {
        return Response.success(null);
    }
    public static Response success(Object obj) {
        Response res = new Response();
        res.code = "success";
        res.obj = obj;
        return  res;
    }
    public static Response failed(String code, String msg) {
        Response res = new Response();
        res.code = code;
        res.msg = msg;
        return res;
    }

    @Override
    public String toString() {
        return "Response{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                ", obj=" + obj +
                '}';
    }
}
