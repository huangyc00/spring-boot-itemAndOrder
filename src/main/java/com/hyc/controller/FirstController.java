package com.hyc.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2017/9/7.
 */
@RestController
public class FirstController {

    @GetMapping(value="/first")
    public String first(){
        return "hello world";
    }
}
