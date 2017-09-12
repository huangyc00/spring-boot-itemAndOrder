package com.tocean.service;

import com.tocean.dao.OrderDao;
import com.tocean.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Created by Administrator on 2017/9/9.
 */
@Service("orderService")
public class OrderService {

    @Autowired
    private OrderDao orderDao ;

    public List<Order> findAll(){
        return orderDao.findAll();
    }

    public Order findByUuid(String Uuid){
        return orderDao.findOne(Uuid);
    }

}
