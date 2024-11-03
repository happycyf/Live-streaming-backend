package top.vx520.mapper;

import org.apache.ibatis.annotations.*;
import top.vx520.pojo.liverecordPojo;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

@Mapper
public interface LiverecordMapper {
    @Insert("insert into liverecord values (default,(select uid from user where account=#{account}),#{liveTitle},#{startTime},#{overTime},#{seeNumber})")
    int add(@Param("account") String account, @Param("liveTitle") String liveTitle, @Param("startTime") String startTime, @Param("overTime") String overTime, int seeNumber);
    @Select("select startTime,overTime,seeNumber from liverecord where uid=" +
            "(select uid from user where account=#{account})" +
            "and Date(startTime) = CURDATE()")
    List<liverecordPojo> getDayHources(String account);
}
