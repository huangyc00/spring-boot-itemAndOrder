package com.tocean.dao.specification;


import com.tocean.entity.Item;
import com.tocean.queryEntity.ItemCriteria;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2017/9/11.
 */
public class ItemSpec {

    /**
     *
     * @param criteria 搜索条件
     * @return  Specification
     */
    public static Specification<Item> mulCriteria(final ItemCriteria criteria){
        return new Specification<Item>() {
            public Predicate toPredicate(Root<Item> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                List<Predicate> list = new ArrayList<Predicate>();

                if (StringUtils.isNoneBlank(criteria.getUuid())) {
                    list.add(cb.like(root.get("uuid").as(String.class), "%" + criteria.getUuid() + "%"));
                }

                if (StringUtils.isNoneBlank(criteria.getName())) {
                    list.add(cb.like(root.get("itemName").as(String.class), "%" + criteria.getName() + "%"));
                }

//                if(StringUtils.isNoneBlank(criteria.getOrderId())){
//                    list.add(cb.like(root.get("order").as(String.class), "%" + criteria.getOrderId() + "%"));
//                }
                Predicate[] p = new Predicate[list.size()];
                return cb.and(list.toArray(p));
            }
        };
    }

}
