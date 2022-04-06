package com.dadalang.x.controller.test;

import com.dadalang.x.service.push.GetTui;
import com.dadalang.x.service.rmq.MqSender;
import com.dadalang.x.vo.res.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @version 1.0
 * @Author lj
 * @date 2021/6/24 4:23 下午
 * @desc
 */
@RestController
@RequestMapping("/test")
public class TestController {

    private GetTui getTui;

    @Autowired MqSender mqSender;

    @GetMapping("/getui")
    public Response createToken() throws Exception{
        getTui = new GetTui();
        String content = getTui.getToken();
        return Response.success(content);
    }
    @DeleteMapping("/getui")
    public Response revokeToken() throws Exception {
        String result = getTui.revokeToken();
        return Response.success(result);
    }
    @GetMapping("/getui/{cid}/{title}/{body}")
    public Response single(@PathVariable("cid") String cid, @PathVariable("title") String title, @PathVariable("body") String dy) throws Exception {
        String result = getTui.single(cid, title, dy);
        return Response.success(result);
    }
    @GetMapping("/getui/batch/{title}/{body}")
    public Response batch(@PathVariable String title, @PathVariable String body) throws Exception {
        String[] cids = {"6b5bffd75494f3c7ba42189281a64d4e", "4b904a1b2f8872b6422aaf3102148e21"};
        String result = getTui.batch(cids, title, body);
        return Response.success(result);
    }
    @GetMapping("/getui/all/{title}/{body}")
    public Response all(@PathVariable String title, @PathVariable String body) throws Exception {
        String result = getTui.all(title, body);
        return Response.success(result);
    }

    @GetMapping("/mq/send/{mes}")
    public Response mqSend(@PathVariable String mes){
        mqSender.send(mes);
        return Response.success();
    }

}
