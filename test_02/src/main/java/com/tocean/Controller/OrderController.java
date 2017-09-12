package com.tocean.Controller;

import com.tocean.entity.Item;
import com.tocean.entity.Order;
import com.tocean.result.ItemVo;
import com.tocean.result.OrderVo;
import com.tocean.service.OrderService;
import com.tocean.util.OrderVoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/9/9.
 */
@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping(value="/orders")
    public List<OrderVo> findAll(){
        List<OrderVo> orderVos = new ArrayList<>();
        for(Order order : orderService.findAll()){
           orderVos.add(OrderVoUtil.getOrderVo(order));
        }
        return orderVos;
    }

    @GetMapping(value = "/order/{uuid}")
    public OrderVo findByUuid(@PathVariable("uuid") String uuid){
        Order order = orderService.findByUuid(uuid);
        return OrderVoUtil.getOrderVo(order);
    }


    @GetMapping(value = "/order")
    public String hello(){
        return "hello";
    }
}
