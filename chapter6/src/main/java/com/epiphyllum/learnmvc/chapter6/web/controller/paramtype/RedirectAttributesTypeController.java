package com.epiphyllum.learnmvc.chapter6.web.controller.paramtype;

import java.util.Arrays;

import com.epiphyllum.learnmvc.chapter6.model.UserModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/method/param/annotation")
public class RedirectAttributesTypeController {

    @RequestMapping(value="/redirect1")
    public String test1(Model model) {

        model.addAttribute("username", "zhang");
        model.addAttribute("role", Arrays.asList("admin", "user"));

        return "redirect:/method/param/annotation/redirectSuccess";
    }

    // redirect时， 有redirectAttributes:  里面可以放置flashAttribute
    @RequestMapping(value="/redirect2")
    public String test2(RedirectAttributes redirectAttributes) {

        // 放入user到flash中
        UserModel user = new UserModel();
        user.setUsername("zhang");
        user.setPassword("123");
        redirectAttributes.addFlashAttribute("user", user);

        return "redirect:/method/param/annotation/redirectSuccess";
    }
    
    
    @RequestMapping(value="/redirect3")
    public String edit(RedirectAttributes redirectAttributes) {

        redirectAttributes.addAttribute("userId", 123);
        return "redirect:/method/param/annotation/show/redirectSuccess";
    }

   // 所有的重定向都到这里了!!!!!!!
    @RequestMapping(value="/redirectSuccess")
    public String success(@ModelAttribute("user") UserModel user) {
        System.out.println(user);
        return "success";
    }

    // flash的生命周期！！！

}
