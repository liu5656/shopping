package com.dadalang.x.schedule;

import com.dadalang.x.service.push.GetTui;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @version 1.0
 * @Author lj
 * @date 2021/6/28 9:31 上午
 * @desc
 */
@Service
public class XSchedule {

    private GetTui getTui = new GetTui();

    XSchedule() {
        try{
            String token = this.getTui.getToken();
            System.out.println("received token: "+ token);
        }catch (Exception e) {
            System.out.println("received token failed");
        }
    }

    @Scheduled(cron = "0 32/2 * * * *")
    public void push() throws Exception {
        Date date = new Date();
        String result = getTui.all(date.toString(), date.toString());
        System.out.println("push result: " + result);
    }

    @Scheduled(cron = "0/1 40 * * * *")
    public void test01() {
        Date date = new Date();
        System.out.println(date.toString());
    }


//    private void sleep(long time) {
//        try{
//            TimeUnit.SECONDS.sleep(time);
//        }catch(InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
}
