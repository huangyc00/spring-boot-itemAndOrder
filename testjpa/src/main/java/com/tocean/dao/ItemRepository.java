package com.tocean.dao;

import com.tocean.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by Administrator on 2017/9/9.
 */

public interface ItemRepository extends JpaRepository<Item, Integer>,
        JpaSpecificationExecutor<Item> {
}
