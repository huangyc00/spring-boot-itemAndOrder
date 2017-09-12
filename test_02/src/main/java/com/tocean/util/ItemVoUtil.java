package com.tocean.util;

import com.tocean.entity.Item;
import com.tocean.result.ItemVo;

/**
 * Created by Administrator on 2017/9/9.
 */
public class ItemVoUtil {

    public static ItemVo getItemVo(Item item){
        ItemVo itemVo = new ItemVo();
        itemVo.setUuid(item.getUuid());
        itemVo.setName(item.getItemName());
        return itemVo;
    }

}
