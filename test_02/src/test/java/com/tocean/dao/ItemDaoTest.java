package com.tocean.dao;

import com.tocean.entity.Item;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Administrator on 2017/9/9.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ItemDaoTest {


    @Autowired
    private ItemDao itemDao;

    @Test
    public void findByName() throws Exception {
        List<Item> items = itemDao.findByItemName("item_1");
        System.out.println(items);
    }

    @Test
    public void findByItemNameAndOrderId() throws Exception {
        List<Item> items = itemDao.findByItemNameAndOrderId("item_2","1");
        System.out.println(items.size());
    }
}