package com.epiphyllum.learnmvc.chapter6.web.controller.parameter;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/parameter2")                      //①处理器的通用映射前缀
public class RequestParameterController2 {


    // 参数submitFlag存在， 且为create, GET
    @RequestMapping(params="submitFlag=create", method=RequestMethod.GET)  //②进行类级别的@RequestMapping窄化
    public String showForm() {
        System.out.println("===============showForm");
        return "parameter/create";        
    }

    // 参数submitFlag存在， 且为create, POST
    @RequestMapping(params="submitFlag=create", method=RequestMethod.POST)   //③进行类级别的@RequestMapping窄化
    public String submit() {
        System.out.println("===============submit");
        return "redirect:/success";        
    }
    
    
    //请求参数submitFlag 不等于 create  GET
    @RequestMapping(params="submitFlag!=create", method=RequestMethod.GET)  //进行类级别的@RequestMapping窄化
    public String error() {
        System.out.println("================error");
        return "redirect:/success";        
    }
    
    
    
    
}
