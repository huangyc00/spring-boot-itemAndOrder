package com.tocean.util;

import com.tocean.entity.Item;
import com.tocean.entity.Order;
import com.tocean.result.ItemVo;
import com.tocean.result.OrderVo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/9/9.
 */
public class OrderVoUtil {

    public static OrderVo getOrderVo(Order order){
        OrderVo orderVo = new OrderVo();
        orderVo.setUuid(order.getUuid());
        orderVo.setName(order.getOrderName());
        List<ItemVo> itemVos = new ArrayList<>();
        for(Item item : order.getItems()){
            itemVos.add(ItemVoUtil.getItemVo(item));
        }
        orderVo.setItemsVo(itemVos);
        return orderVo;
    }
}
