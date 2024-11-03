package top.vx520.mapper;

import top.vx520.pojo.Withdrawal;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface WithdrawalMapper {


    @Select("select w.id 'id',w.data,money 'money', u.uName 'name',w.state 'state',w.payAccount 'payAccount' from withdrawal w ,user u where w.uid=u.uid")
    public List<Withdrawal> findAll();

    @Update("update withdrawal set state=#{state} where id=#{id}")
    int upState(int id,int state);
}
