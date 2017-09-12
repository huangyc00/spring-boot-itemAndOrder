package com.tocean.dao;

import com.tocean.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Administrator on 2017/9/9.
 */
public interface ItemDao extends JpaRepository<Item,String>,JpaSpecificationExecutor<Item> {

    //select * from t_item where NAME=?
    List<Item> findByItemName(String name );

    @Query("select i from Item i where i.itemName=?1 and i.order.id=?2" )
    List<Item> findByItemNameAndOrderId(String itemName,String orderId);

}