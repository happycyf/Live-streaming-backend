package top.vx520.service;


import top.vx520.pojo.User;

public interface UserService {


    public User findById(int uId);
    public User findByAccount(String account);

    public User findByUName(String uName);

    public int add(User newUser);

    public int upInfo(User user);

    public int upPhone(int uId, String phone);

    int upPwd(int uId, String pwd);

    public User findByPhone(String phone);

    int reduceFan(int followUid);
    int reduceAttention(int uId);

    int addMoney(int uId,double money);
}
