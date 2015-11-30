package com.epiphyllum.learnmvc.chapter6.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SuccessController {

    @RequestMapping(value="/success")
    public String success() {
        return "success";  // 返回view名称
    }
}
