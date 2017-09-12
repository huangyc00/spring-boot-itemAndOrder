package com.tocean.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Administrator on 2017/9/9.
 */
@Entity
@Table(name = "t_order")
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String name;
    @OneToMany(mappedBy="order",targetEntity=Item.class,fetch=FetchType.LAZY)
    private List<Item> items;
}
