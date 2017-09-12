package com.tocean.service;


import com.tocean.entity.Item;
import com.tocean.queryEntity.ItemCriteria;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;


import static org.junit.Assert.*;

/**
 * Created by Administrator on 2017/9/12.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ItemServiceTest {
    @Autowired
    private ItemService itemService;

    @Test
    public void findAllTest(){
        Integer page = 0;
        Integer pageSize = 1 ;
        Pageable pageable = new PageRequest(page,pageSize);
        ItemCriteria criteria = new ItemCriteria();
        criteria.setUuid("1");
        criteria.setName("it");
        criteria.setOrderId("1");
        Page<Item> items = itemService.findAll(criteria,pageable);
        System.out.println(items.getContent().size());
    }
}