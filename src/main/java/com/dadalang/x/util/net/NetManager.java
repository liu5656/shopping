package com.dadalang.x.util.net;


import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.*;
import org.apache.commons.httpclient.params.HttpMethodParams;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @version 1.0
 * @Author lj
 * @date 2021/6/24 11:42 上午
 * @desc
 */
public class NetManager {
    static public JSONObject exec(NetRequest obj) throws Exception {
        HttpUriRequest request = prepare(obj);
        JSONObject result = request(request);
        return result;
    }
    static private HttpUriRequest prepare(NetRequest obj) throws Exception {
        String url = obj.getUrl();
        HttpUriRequest request;
        NetMethod method = obj.getMethod();
        switch (method) {
            case get:
                request = get(obj);
                break;
            case post:
                request = post(obj);
                break;
            case put:
                request = put(obj);
                break;
            case delete:
                request = delete(obj);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + method);
        }
        configHeader(obj.getHeaders(), request);
        return request;
    }
    static private void configHeader(Map<String, String> headers, HttpUriRequest request) {
        headers.forEach((key, value) -> {
            request.setHeader(key, value);
        });
    }
    static private HttpUriRequest get(NetRequest obj) {
        String query = "";
        for (Map.Entry<String, Object> entry : obj.getParams().entrySet()) {
            if (query.startsWith("?")) {
                query += "&";
            }else{
                query += "?";
            }
            query += entry.getKey() + "=" + entry.getValue().toString();
        }
        String url = obj.getUrl() + query;
        HttpGet req = new HttpGet(url);
        return req;
    }
    static private HttpUriRequest post(NetRequest obj) throws IOException {
        HttpPost post = new HttpPost(obj.getUrl());
//        List<NameValuePair> params = new ArrayList<>();
//        for (Map.Entry<String, Object> entry : obj.getParams().entrySet()) {
//            NameValuePair pair = new BasicNameValuePair(entry.getKey(), entry.getValue().toString());
//            params.add(pair);
//        }
//        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params);


        String jsonStr = JSONObject.toJSONString(obj.getParams());
        StringEntity entity = new StringEntity(jsonStr, ContentType.APPLICATION_JSON);

        post.setEntity(entity);
        return post;
    }
    static private HttpUriRequest put(NetRequest obj) {
        HttpPut put = new HttpPut(obj.getUrl());
        return put;
    }
    static private HttpUriRequest delete(NetRequest obj) {
        HttpDelete delete = new HttpDelete(obj.getUrl());
        return delete;
    }
    static private JSONObject request(HttpUriRequest request) throws IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpResponse response = client.execute(request);
        String resultStr = EntityUtils.toString(response.getEntity());
        JSONObject result = JSONObject.parseObject(resultStr);
        return result;
    }
}
