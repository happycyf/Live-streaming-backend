package top.vx520.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.vx520.mapper.UserAttentionMapper;
import top.vx520.pojo.User;
import top.vx520.service.UserAttentionService;

import java.util.List;

@Service
public class UserAttentionServiceImpl implements UserAttentionService {

    @Autowired
    UserAttentionMapper userAttentionMapper;
    @Override
    public List<User> findAll(int uId) {
        return userAttentionMapper.findAll(uId);
    }

    @Override
    public int delAttention(int userUid, int followUid) {
        return userAttentionMapper.delAttention(userUid,followUid);
    }
}
