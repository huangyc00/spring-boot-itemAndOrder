package com.tocean.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

/**
 * Created by Administrator on 2017/9/9.
 */
@Entity
@Table(name = "t_item")
@Data
public class Item {

    @Id
    @Column(name = "UUID", unique = true, nullable = false, length = 44)
    private String uuid;

    @Column(name="NAME",nullable = false,length = 44)
    private String itemName ;

//    @JoinColumn(name = "order_id", nullable = false, updatable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ORDER_ID", nullable = false, updatable = false)
    private Order order ;
}
