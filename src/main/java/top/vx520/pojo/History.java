package top.vx520.pojo;

import lombok.Data;

//历史记录表实体类
@Data
public class History {

    private int hisId;//观看记录编号 新增
    private int uid;//观看用户编号 新增
    private int liveId;//直播编号
    private String seeTime;//观看时间

}
