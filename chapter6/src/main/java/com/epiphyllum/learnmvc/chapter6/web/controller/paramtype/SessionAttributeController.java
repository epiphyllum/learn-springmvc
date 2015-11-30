package com.epiphyllum.learnmvc.chapter6.web.controller.paramtype;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.WebRequest;

import com.epiphyllum.learnmvc.chapter6.model.UserModel;

//
//① 标识模型对象中名字如果是“user”将存储在会话范围，
//   并自动暴露到模型数据中
//
@Controller
@RequestMapping("/method/param/annotation")
@SessionAttributes(value = {"user"}, types={})
public class SessionAttributeController {

    //
    //② 如果模型数据中没有名字为user的对象，调用该方法并存储到模型数据中(但是在session_scope)上
    // 同时由于class上的@SessionAttributes的配置， 这个user对象会再Session_Scope暴露
    //
    @ModelAttribute("user")
    public UserModel initUser() {
        System.out.println("initUser is called!!!!!!!");
        return new UserModel();
    }

    //
    //③ 首先查找模型数据中是否有user对象，
    //   有直接使用， 没有则创建一个，并将请求参数绑定到该对象上
    // 由于initUser的存在， 模型的session_scope上已经有了user了
    @RequestMapping("/session1")
    public String session1(
            @ModelAttribute("user") UserModel user,   // 由于类上的SesstionAttributes("user")  user存储在回话中， 但同时暴露到了model中
            ModelMap model,            // model中有user了， 在session_scope上
            WebRequest request,
            SessionStatus status
    ) {
        System.out.println(user == model.get("user"));
        user.setUsername("zhang");
        return "success";
    }

    //③
    @RequestMapping("/session2")
    public String session(
            @ModelAttribute("user") UserModel user,
            ModelMap model,
            WebRequest request,
            SessionStatus status
    ) {
        System.out.println(user == request.getAttribute("user", WebRequest.SCOPE_SESSION));  // request中的session中的 - 同一个
        System.out.println(user == model.get("user"));                                       // 模型中的 同一个
        System.out.println(user);

        //④如果会话可以终止了，就标识会话结束，可以清理掉会话数据了
        if(true) {
            status.setComplete();
        }
        return "success";
    }
}


