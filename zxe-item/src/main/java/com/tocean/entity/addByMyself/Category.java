package com.tocean.entity.addByMyself;

import com.tocean.base.baseObject.BaseEntity;
import com.tocean.entity.item.Product;
import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Administrator on 2017/9/9.
 */
@Entity
@Table(name="item_category")
@Data
public class Category extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "UUID", unique = true, nullable = false, length = 44)
    private String uuid;

    @OneToMany

    private Set<Product> products = new HashSet<Product>();
}
