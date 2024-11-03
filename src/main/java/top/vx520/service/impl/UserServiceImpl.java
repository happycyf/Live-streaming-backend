package top.vx520.service.impl;

import top.vx520.mapper.UserMapper1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.vx520.pojo.User;
import top.vx520.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper1 userMapper;

    @Override
    public User findById(int uId){
        return userMapper.findById(uId);
    }

    public User findByAccount(String account) {
        return userMapper.findByAccount(account);
    }

    public User findByUName(String uName){
        return userMapper.findByUName(uName);
    }

    public int add(User newUser){
        return userMapper.add(newUser);
    }

    public int upInfo(User user){
        return userMapper.upInfo(user);
    }

    public int upPhone(int uId, String phone){
        return userMapper.upPhone(uId,phone);
    }

    @Override
    public int upPwd(int uId, String pwd) {
        return userMapper.upPwd(uId,pwd);
    }

    public User findByPhone(String phone){
        return userMapper.findByPhone(phone);
    }

    @Override
    public int reduceFan(int followUid) {
        return userMapper.reduceFan(followUid);
    }

    @Override
    public int reduceAttention(int uId) {
        return userMapper.reduceAttention(uId);
    }

    @Override
    public int addMoney(int uId,double money) {
        return userMapper.addMoney(uId,money);
    }
}
