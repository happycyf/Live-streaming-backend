package top.vx520.webSocket;



import jakarta.websocket.*;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;



import org.springframework.stereotype.Component;
import top.vx520.Thread.SendToUser;
import top.vx520.pojo.AchorPojo;


import java.io.File;
import java.util.*;

@ServerEndpoint("/liveWebSocket/{Id}")
@Component
public class LiveWebSocket {
//    存储的地址 以推流key做map的key 一个是主播的session 另一个是观看用户的session
    public static Map<String, List<Session>> map = new HashMap<>();
//   全体主播开播信息 比如标题，描述，封面地址，主播推流url。 key为主播账号
    public static Map<String, AchorPojo> anchorMap = new HashMap<>();
    private String id;


    /**
     * 主播连接时
     * @param session sessionId
     */
    @OnOpen
    public void onOpen(Session session,@PathParam("Id") String id) {
        if (map.get(id) == null) {
            ArrayList<Session> list = new ArrayList<>();
//        第一个用户主播自身
            list.add(session);
//        主播的sessionId
            map.put(id,list);
        }else {
//            永远在第一个就是主播
            map.get(id).add(0,session);
        }

        this.id=id;

//        userMap.put(session.getId(),)
        System.out.println("加入人数"+map.get(id).size());
    }

    /**
     * 前端发送给消息后端接收到的情况
     * @param message 消息本身
     * @param session 自身对象
     */
    @OnMessage
    public void onMessage(String message,Session session) {
        System.out.println("在线序号为"+id);
        for (Session s:  map.get(id)){
//            非本机情况
            if (s != session) {
//                进行多线程传递
                SendToUser sendToUser =new SendToUser(message,s);
                Thread thread = new Thread(sendToUser);
                thread.start();
            }
        }

    }

    /**
     * 关播允许聊天
     * @param session 删除者的session
     * @param id 删除者所在的id
     */
    @OnClose
    public void onClose(Session session,@PathParam("Id") String id) {
        System.out.println("主播端关闭");
//        删除主播在线的session
        map.get(id).remove(session);
//        通过url更改主播状态并删除封面
        for (String s : anchorMap.keySet()) {
//            key匹配
            if (anchorMap.get(s).gettKey().equals(id)) {
//                关播
                anchorMap.get(s).setStatu(1);
//                找到url并删除文件
                String coverName = anchorMap.get(s).getCoverName();
                File f =new File("src/main/resources/static/cover/"+coverName);
                if (f.exists()) {
//                    存在删除
                    f.delete();
                }
//                更改文件地址为null
                anchorMap.get(s).setCoverName(null);
            }
        }
    }
    @OnError
    public void onError(Session session, Throwable throwable,@PathParam("Id") String id) {
        map.get(id).remove(session);
        throwable.printStackTrace();
    }

}
