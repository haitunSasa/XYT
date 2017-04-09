package com.Action;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import wtf.socket.*;

import java.util.HashMap;

/**
 * Created by tan on 2017/3/9.
 */
public class SocketAction extends HttpServlet {
    public void init(ServletConfig config) throws ServletException {
        System.out.println("开始初始化");
        // 初始化框架
        WTFSocketSessionFactory.init(
                new WTFSocketConfig()
                        .setIp("139.224.54.233")
                        .setPort(1234)
                        .setLocalName("sshServer")
        );
        WTFSocketSessionFactory.SERVER.sendMsg(new WTFSocketMsg().setBody(new HashMap() {{ put("cmd", 64) ;}}));
        WTFSocketSessionFactory.setDefaultResponse(new WTFSocketHandler() {

            @Override
            public boolean onReceive(WTFSocketSession session, WTFSocketMsg msg) {
                String p = String.format("receive msg from <%s> to <%s>:\nmsg => %s",session.getTo(),session.getFrom(),msg);
                System.out.println(p);
                switch ((int)msg.getBody().get("cmd")){
                    case 16:
                }
                return true; // 如果不想中断消息派发，则返回false
            }

            @Override
            public boolean onException(WTFSocketSession session, WTFSocketMsg msg, WTFSocketException e) {
                System.out.println(String.format(
                        "receive msg from <%s> to <%s>:\nmsg => %s",
                        session.getTo(),
                        session.getFrom(),
                        msg
                ));
                return true; // 如果不想中断异常派发，则返回false
            }
        });
        // 创建到指定对象的会话
        WTFSocketSession session = WTFSocketSessionFactory.getSession("MockHardware_V2_0");


    }
    public static void sendMsg(HashMap body,String  targetName,int cmd ){
        WTFSocketSession session = WTFSocketSessionFactory.getSession("MockHardware_V2_0");
        WTFSocketMsg msg = new WTFSocketMsg();
        msg.setBody(body);
        session.sendMsg(msg);
    }
}
