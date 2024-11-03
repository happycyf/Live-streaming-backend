package top.vx520.pojo;

import java.sql.Timestamp;

public class liverecordPojo {
//    直播序列
    private int liveId;
//    用户id
    private int uId;
//    直播标题
    private String liveTitle;
//    开始时间
    private Timestamp startTime;
//    关播时间
    private Timestamp overTime;
//    观看人数
    private int seeNumber;

    public int getLiveId() {
        return liveId;
    }

    public void setLiveId(int liveId) {
        this.liveId = liveId;
    }

    public int getuId() {
        return uId;
    }

    public void setuId(int uId) {
        this.uId = uId;
    }

    public String getLiveTitle() {
        return liveTitle;
    }

    public void setLiveTitle(String liveTitle) {
        this.liveTitle = liveTitle;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getOverTime() {
        return overTime;
    }

    public void setCoverTime(Timestamp overTime) {
        this.overTime = overTime;
    }

    public int getSeeNumber() {
        return seeNumber;
    }

    public void setSeeNumber(int seeNumber) {
        this.seeNumber = seeNumber;
    }

    public liverecordPojo() {
    }

    @Override
    public String toString() {
        return "liverecordPojo{" +
                "liveId=" + liveId +
                ", uId=" + uId +
                ", liveTitle='" + liveTitle + '\'' +
                ", startTime=" + startTime +
                ", coverTime=" + overTime +
                ", seeNumber=" + seeNumber +
                '}';
    }

    public liverecordPojo(int liveId, int uId, String liveTitle, Timestamp startTime, Timestamp overTime, int seeNumber) {
        this.liveId = liveId;
        this.uId = uId;
        this.liveTitle = liveTitle;
        this.startTime = startTime;
        this.overTime = overTime;
        this.seeNumber = seeNumber;
    }
}
