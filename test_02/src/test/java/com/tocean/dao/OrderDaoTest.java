package com.tocean.dao;

import com.tocean.entity.Item;
import com.tocean.entity.Order;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Administrator on 2017/9/9.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDaoTest {
    @Autowired
    private OrderDao orderDao;

    @Autowired
    private ItemDao itemDao;

    @Test
    public void findallTest(){
        List<Order> orders = orderDao.findAll();
        for(Order order : orders){
            if(order.getUuid().equals("1")){
                System.out.println(order.getUuid()+order.getOrderName()+order.getItems().get(0).getUuid());
            }
        }
//        List<Item> items = itemDao.findAll();
//        for(Item item : items){
//
//        }
    }

    @Test
    public void findByUuid(){
        Order order = orderDao.findOne("1");
        System.out.println(order.getUuid()+order.getOrderName()+order.getItems().get(0).getUuid());
    }


}