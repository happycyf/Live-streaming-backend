package top.vx520.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper {

    @Insert("insert into `order` values (null,#{uId},#{price},now())")
    public int add(int uId, double price);

}
