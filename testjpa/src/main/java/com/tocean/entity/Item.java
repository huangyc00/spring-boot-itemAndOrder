package com.tocean.entity;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String name;
    @ManyToOne(fetch=FetchType.LAZY,targetEntity=Order.class)
    @JoinColumn(name="order_id")
    private Order order;
}
