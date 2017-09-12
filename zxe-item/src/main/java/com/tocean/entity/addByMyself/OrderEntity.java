package com.tocean.entity.addByMyself;

import com.tocean.base.baseObject.BaseEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Administrator on 2017/9/9.
 */

@Entity
@Table(name="item_orderEntity")
@Data
public class OrderEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "UUID", unique = true, nullable = false, length = 44)
    private String uuid;
}
