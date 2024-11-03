package top.vx520.mapper;


import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import top.vx520.pojo.User;

import java.util.List;

@Mapper
public interface UserAttentionMapper {


    @Select("select u.* from user u,userattention ua where ua.userUid=#{uId} and ua.FollowUid = u.uId")
    public List<User> findAll(int uId);

    @Delete("delete from userattention where userUid=#{userUid} and FollowUid=#{followUid}")
    int delAttention(int userUid, int followUid);
}
