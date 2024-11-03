package top.vx520.service;



import top.vx520.pojo.User;

import java.util.List;

public interface UserAttentionService {

    public List<User> findAll(int uId);

    int delAttention(int userUid, int followUid);


}
