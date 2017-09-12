package com.tocean.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Administrator on 2017/9/9.
 */
@Entity
@Table(name = "t_order")
@Data
public class Order implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "UUID", unique = true, nullable = false, length = 44)
    private String uuid;

    @Column(name="order_name",nullable = false,length = 44)
    private String orderName;

//    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL,fetch=FetchType.EAGER)
    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER, cascade = {
            CascadeType.ALL}, orphanRemoval = true)
    private List<Item> items = new ArrayList<>();
}
