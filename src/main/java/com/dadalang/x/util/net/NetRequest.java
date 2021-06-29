package com.dadalang.x.util.net;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @version 1.0
 * @Author lj
 * @date 2021/6/24 11:42 上午
 * @desc
 */
@Data
public class NetRequest {
    private String url;
    private NetMethod method = NetMethod.get;
    private Map<String, Object> params = new HashMap<>();
    private Map<String, String> headers = new HashMap<>();

    public NetRequest(String url) {
        this.url = url;
    }

    public NetRequest(String url, NetMethod method) {
//        this.url = url;
        this(url);
        this.method = method;
    }

    public NetRequest(String url, NetMethod method, Map<String, Object> params) {
//        this.url = url;
//        this.method = method;
        this(url, method);
        this.params = params;
    }

    public NetRequest(String url, NetMethod method, Map<String, Object> params, Map<String, String> headers) {
//        this.url = url;
//        this.method = method;
//        this.params = params;
        this(url, method, params);
        this.headers = headers;
    }

}
