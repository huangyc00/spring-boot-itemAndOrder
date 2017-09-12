package com.tocean.result;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/9/9.
 */
@Data
public class OrderVo {

    private String uuid ;

    private String name ;

    private List<ItemVo> itemsVo = new ArrayList<ItemVo>();
}
