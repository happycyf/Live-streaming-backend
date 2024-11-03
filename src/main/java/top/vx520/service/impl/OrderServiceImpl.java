package top.vx520.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.vx520.mapper.OrderMapper;
import top.vx520.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderMapper orderMapper;

    @Override
    public int add(int uId, double price) {
        return orderMapper.add(uId,price);
    }
}
