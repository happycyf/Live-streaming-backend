package top.vx520.Thread;


import jakarta.websocket.Session;


public class SendToUser implements Runnable{
    private String msg;
    private Session session;
    public SendToUser(String msg, Session session) {
        this.msg = msg;
        this.session = session;
    }

    public SendToUser() {
    }

    @Override
    public void run() {
        System.out.println("执行");
        session.getAsyncRemote().sendText(msg);
    }
}
