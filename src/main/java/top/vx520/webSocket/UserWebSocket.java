package top.vx520.webSocket;

import com.google.gson.Gson;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@ServerEndpoint("/userWebSocket/{Id}")
@Component
public class UserWebSocket {
    private String id;
    @OnOpen
    public void onOpen(Session session, @PathParam("Id") String Id) {
        this.id=Id;
//        第一次连接使用id进行添加自身
        LiveWebSocket.map.get(Id).add(session);
        Map map =new HashMap();
        map.put("instruction","add");
        Gson gson = new Gson();
        String json = gson.toJson(map);
        System.out.println(json);
//        告诉主播热度+1
        sendMessage(json,session);

    }
    @OnMessage
    public void onMessage(String message, Session session) {
//        用户发送消息到其他地方直接转发即可
        sendMessage(message,session);
    }
//    当支线关闭时
    @OnClose
    public void onClose(Session session) {
        System.out.println("我关闭了");
        LiveWebSocket.map.get(id).remove(session);
        Map map =new HashMap();
        map.put("instruction","sub");
        Gson gson = new Gson();
        String json = gson.toJson(map);
//        提醒主播减去用户
//        用户发送消息到其他地方直接转发即可
        sendMessage(json,session);
    }
    public void  sendMessage(String message,Session session) {
        for (Session session1 : LiveWebSocket.map.get(id)) {
//            非本机
            if (session1 != session) {
//                消息转发给其他客户端端
                session1.getAsyncRemote().sendText(message);
            }
        }
    }
}
