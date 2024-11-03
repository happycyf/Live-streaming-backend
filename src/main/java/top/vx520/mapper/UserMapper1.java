package top.vx520.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import top.vx520.pojo.User;

@Mapper
public interface UserMapper1 {

    @Select("select * from user where uId=#{uId}")
    public User findById(int uId);

    @Select("select * from user  where account=#{account}")
    public User findByAccount(String account);

    @Select("select * from user where account=#{uName}")
    public User findByUName(String uName);

    @Select("select * from user where phone=#{phone}")
    public User findByPhone(String phone);

    @Insert("insert into user values (null,#{uName},#{account},#{pwd},'/陆小果.jpg',0,0,0,#{phone},'男',null,2,null)")
    public int add(User newUser);

    @Update("update user set uName=#{uName},sex=#{sex},adress=#{adress} where uId=#{uId}")
    public int upInfo(User user);

    @Update("update user set phone=#{phone} where uId=#{uId}")
    public int upPhone(int uId,String phone);

    @Update("update user set pwd=#{pwd} where uId=#{uId}")
    public int upPwd(int uId,String pwd);

    @Update("update user set fan=fan-1 where uId=#{followUid}")

    int reduceFan(int followUid);

    @Update("update user set attention=attention-1 where uId=#{uId}")
    int reduceAttention(int uId);

    @Update("update user set blance=blance+#{money} where uId=#{uId}")
    int addMoney(int uId,double money);
}
