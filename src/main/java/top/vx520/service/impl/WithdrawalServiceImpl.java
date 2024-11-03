package top.vx520.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.vx520.mapper.WithdrawalMapper;
import top.vx520.pojo.Withdrawal;
import top.vx520.service.WithdrawalService;

import java.util.List;

@Service
public class WithdrawalServiceImpl implements WithdrawalService {

    @Autowired
    WithdrawalMapper withdrawalMapper;

    public List<Withdrawal> findAll(){
        return withdrawalMapper.findAll();
    }

    @Override
    public int upState(int id,int state) {
        return withdrawalMapper.upState(id,state);
    }


}
