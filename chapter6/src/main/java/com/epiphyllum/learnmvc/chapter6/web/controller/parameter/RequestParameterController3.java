package com.epiphyllum.learnmvc.chapter6.web.controller.parameter;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/parameter3")                      //①处理器的通用映射前缀
public class RequestParameterController3 {


    // 存在参数test1, test2, 且test2的参数为create
    @RequestMapping(params={"test1", "test2=create"})  //②进行类级别的@RequestMapping窄化
    public String test() {
        System.out.println("===============test1 and test2=create");
        return "parameter/create";        
    }
}
