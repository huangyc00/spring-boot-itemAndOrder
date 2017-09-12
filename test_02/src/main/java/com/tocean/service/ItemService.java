package com.tocean.service;

import com.tocean.dao.ItemDao;
import com.tocean.dao.specification.ItemSpec;
import com.tocean.entity.Item;
import com.tocean.queryEntity.ItemCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/9/12.
 */
@Service("itemService")
public class ItemService {
    @Autowired
    private ItemDao itemDao;

    public Page<Item> findAll(ItemCriteria criteriar, Pageable pageable){
        Specification<Item> specification = ItemSpec.mulCriteria(criteriar);
        return itemDao.findAll(specification,pageable);
    }


}
