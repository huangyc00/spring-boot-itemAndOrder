package com.tocean.entity.addByMyself;

import com.tocean.entity.item.Product;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Administrator on 2017/9/9.
 */
@Entity
@Table(name = "item_goods")
public class Goods {

    @Id
    @Column(name = "UUID", unique = true, nullable = false, length = 44)
    private String uuid;


    @OneToMany(mappedBy = "item_goods", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Product> product = new HashSet<Product>();


}
