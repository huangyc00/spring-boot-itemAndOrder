package com.tocean.dao;

import com.tocean.entity.Order;
        import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Administrator on 2017/9/9.
 */
public interface OrderDao extends JpaRepository<Order,String>{
}
