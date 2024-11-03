package top.vx520.mapper;

import org.apache.ibatis.annotations.*;
import top.vx520.pojo.UserAttention;

@Mapper
public interface getName {
    @Select("select uName from  user where account=#{account}")
    String getName(String account);
    @Insert("INSERT INTO giftrecord VALUES((SELECT giftId FROM gift WHERE giftName = #{giftName}),#{uId},(SELECT uId FROM user WHERE account = #{account}),now())")
    Integer add(@Param("giftName") String giftName, @Param("uId") int uId,@Param("account") String account);

    /**
     * 修改关闭的消费金额
     * @param account 主播账号
     * @param uId  消费的id
     * @param giftName 礼物名
     * @return
     */
    @Update("update userattention " +
            "set monetary=monetary+" +
            "(select giftMoney from gift where giftName=#{giftName}) " +
            "where userUId=#{uId} " +
            "and FollowUid=(select uid from user where account=#{account})")
    int setUserattentionMoney(@Param("account") String account,@Param("uId") int uId,@Param("giftName") String giftName);
    @Insert("insert into userattention values (#{uId}," +
            "(select uId from user where account=#{account})," +
            "ifNUll((select sum((select giftMoney from gift g where gc.giftId=g.giftId)) from giftrecord gc where  reId=(select uId from user where account=#{account})" +
            " and sendId=#{uId}),0))")
    int adduserattention(@Param("account") String account,@Param("uId") int uId);
    @Select("SELECT COUNT(*) FROM userattention WHERE FollowUid=(select uId from user where account=#{account})")
    int fan(String account);
    @Delete("DELETE FROM userattention WHERE userUid=#{uId} AND FollowUid=(SELECT uId FROM user WHERE account=#{account} )")
    int deluserattention(@Param("uId") int uId,@Param("account") String account);
    @Select("SELECT * FROM userattention WHERE userUid=#{uId} AND FollowUid=(select uId from user where account=#{account})")
    UserAttention chaxuuserattention(@Param("uId") int uId, @Param("account") String account);
    @Update("update user set blance =blance-(select giftMoney from gift where giftName=#{giftName}) where uid=#{uId}")
    int upgift(@Param("uId") int uId,@Param("giftName") String giftName);
    @Update("update user set blance=blance+(select giftMoney from gift where giftName=#{giftName}) where account=#{account}")
    int addBlance(@Param("account") String account,@Param("giftName") String giftName);
}
