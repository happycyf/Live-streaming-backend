package top.vx520.pojo;

import lombok.Data;

//直播记录表实体类
@Data
public class LiveRecord {

    private int liveId;//直播编号
    private int uId;//开播主播编号
    private String liveTitle;//直播标题 修改
    private String startTitle;//开播时间
    private String overTitle;//下播时间
    private int seeNumber;//观看人数

}
