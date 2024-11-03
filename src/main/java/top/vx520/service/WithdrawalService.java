package top.vx520.service;



import top.vx520.pojo.Withdrawal;

import java.util.List;

public interface WithdrawalService {

    public List<Withdrawal> findAll();

    public int upState(int id,int state);

}
