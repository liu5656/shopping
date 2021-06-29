package com.dadalang.x.service.push;

import com.alibaba.fastjson.JSONObject;
import com.dadalang.x.util.net.NetManager;
import com.dadalang.x.util.net.NetMethod;
import com.dadalang.x.util.net.NetRequest;
import com.dadalang.x.util.tools.DateEx;
import com.dadalang.x.util.tools.StringEx;

import java.util.HashMap;
import java.util.Map;

/**
 * @version 1.0
 * @Author lj
 * @date 2021/6/24 11:29 上午
 * @desc
 */
public class GetTui {
    // TODO 用自己个推账号的信息配置参数
    final String appId = "";
    final String appkey = "";
    final String appSecret = "";
    final String masterSecret = "";

    final String gtUrl = "https://restapi.getui.com/v2" + "/" + appId;
    final String authUrl = gtUrl + "/auth";
    final String single = gtUrl + "/push/single/cid";                   // 单推cid
    final String newMessageUrl = gtUrl + "/push/list/message";          // 创建消息，便于非单推使用
    final String batchUrl = gtUrl + "/push/list/cid";                   // 批量推cid
    final String groupUrl = gtUrl + "/push/all";                        // 向应用所有用户发送

    private String expire;
    private String token;

    /**
     * 向单个用户推送消息
     * @cid 目标用户cid
     * @return
     */
    public String single(String cid, String title, String body) throws Exception {
        String[] cids = {cid};

        Map<String, Object> audience = new HashMap<>();
        audience.put("cid", cids);

        Map<String, Object> params = payload(title, body);
        params.put("audience", audience);

        NetRequest obj = new NetRequest(single, NetMethod.post, params);
        obj.getHeaders().put("token", token);
        JSONObject result = NetManager.exec(obj);
        return result.toJSONString();
    }

    /**
     * 向cids内的用户发送推送
     * @param cids
     * @param title
     * @param body
     * @return
     * @throws Exception
     */
    public String batch(String[] cids, String title, String body) throws Exception {
        String taskid = newMessage(title, body);

        Map<String, Object> audience = new HashMap<>();
        audience.put("cid", cids);

        Map<String, Object> params = payload(title, body);
        params.put("audience", audience);
        params.put("taskid", taskid);
        params.put("is_async", true);

        NetRequest obj = new NetRequest(batchUrl, NetMethod.post, params);
        obj.getHeaders().put("token", token);

        JSONObject result = NetManager.exec(obj);
        return result.toJSONString();
    }

    /**
     * 向应用内所有用户推送
     * @return
     */
    public String all(String title, String body) throws Exception {
        Map<String, Object> params = payload(title, body);
        params.put("audience", "all");

        NetRequest obj = new NetRequest(groupUrl, NetMethod.post, params);
        obj.getHeaders().put("token", token);

        JSONObject result = NetManager.exec(obj);
        return result.toJSONString();
    }

    /**
     * 配置消息
     * @param title
     * @param body
     * @return
     */
    private Map<String, Object> payload(String title, String body) {
        Map<String, Object> map = new HashMap<>();
        map.put("request_id", "" + StringEx.randomNum(20));

        Map<String, Object> setting = new HashMap<>();
        map.put("ttl", -1);

        Map<String, Object> message = new HashMap<>();
//        message.put("notification", content);
        message.put("transmission", "123465");

        Map<String, Object> channel = new HashMap<>();
        channel.put("ios", iosChannel(title, body));
        channel.put("android", androidChannel(title, body, "https://www.baidu.com"));

        map.put("settings", setting);
        map.put("push_message", message);
        map.put("push_channel", channel);

        System.out.println(JSONObject.toJSONString(map));

        return map;
    }

    /**
     * 配置iOS推送消息
     * @param title
     * @param body
     * @return
     */
    private Map<String, Object> iosChannel(String title, String body) {
        Map<String, Object> alert = new HashMap<>();
        alert.put("title", title);
        alert.put("body", body);

        Map<String, Object> aps = new HashMap<>();
        aps.put("alert", alert);
        aps.put("content-available",0);

        Map<String, Object> ios = new HashMap<>();
        ios.put("type", "notify");
        ios.put("payload", "自定义消息");
        ios.put("aps", aps);
        ios.put("auto_badge", "+1");

        return ios;
    }

    /**
     * 配置android推送消息
     * @param title
     * @param body
     * @param url
     * @return
     */
    private Map<String, Object> androidChannel(String title, String body, String url) {
        Map<String, Object> noti = new HashMap<>();
        noti.put("title", title);
        noti.put("body", body);
        noti.put("click_type", "url");
        noti.put("url", url);

        Map<String, Object> ups = new HashMap<>();
        ups.put("notification", noti);

        Map<String, Object> android = new HashMap<>();
        android.put("ups", ups);

        return android;
    }

    private String newMessage(String title, String body) throws Exception {
        Map<String, Object> map = payload(title, body);
        NetRequest obj = new NetRequest(newMessageUrl, NetMethod.post, map);
        obj.getHeaders().put("token", token);
        String taskId = NetManager.exec(obj).getJSONObject("data").getString("taskid");
        System.out.println("created new message: " + taskId);
        return taskId;
    }

    /**
     * 获取授权token，有效期1天，请求限制：100ph、100000pd
     * @return
     * @throws Exception
     */
    public String getToken() throws Exception {
        NetRequest obj = new NetRequest(authUrl, NetMethod.post);
        Long timestamp = DateEx.now();
        Map<String, Object> params = new HashMap<>();
        params.put("sign", sign(timestamp));
        params.put("timestamp", timestamp);
        params.put("appkey", appkey);

        System.out.println("params: " + appkey + " " + timestamp + " " + params.get("sign").toString());
        obj.setParams(params);

        obj.getHeaders().put("content-type", "application/json;charset=utf-8");

        JSONObject result = NetManager.exec(obj).getJSONObject("data");
        expire = result.getString("expire_time");
        token = result.getString("token");
        System.out.println("expire：" + expire + "\n" + "token：" + token);
        return token;
    }

    /**
     * 紧急情况下注销token
     * @return
     * @throws Exception
     */
    public String revokeToken() throws Exception {
        NetRequest obj = new NetRequest(authUrl + "/" + token, NetMethod.delete);
        obj.getHeaders().put("content-type", "application/json;charset=utf-8");
        String result = NetManager.exec(obj).toJSONString();
        return result;
    }

    /**
     * 签名
     * @param timestamp
     * @return
     */
    private String sign(Long timestamp) {
        String raw = appkey + timestamp + masterSecret;
        System.out.println("raw value: " + appkey + " " + timestamp + " " + masterSecret);
        String sign = StringEx.sha256(raw);
        System.out.println("after encode: " + sign);
        return  sign;
    }
}
