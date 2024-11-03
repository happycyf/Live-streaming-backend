package top.vx520.pojo;

import java.util.Date;

public class SendChatUserPojo {
//    发送者用户昵称
    private String sendUserName;
//    发送者账号
    private String sendUserId;
//     发送时间
    private Date sendTime;

    public SendChatUserPojo() {
    }

    public SendChatUserPojo(String sendUserName, String sendUserId, Date sendTime) {
        this.sendUserName = sendUserName;
        this.sendUserId = sendUserId;
        this.sendTime = sendTime;
    }

    public String getSendUserName() {
        return sendUserName;
    }

    public void setSendUserName(String sendUserName) {
        this.sendUserName = sendUserName;
    }

    public String getSendUserId() {
        return sendUserId;
    }

    public void setSendUserId(String sendUserId) {
        this.sendUserId = sendUserId;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }
}
