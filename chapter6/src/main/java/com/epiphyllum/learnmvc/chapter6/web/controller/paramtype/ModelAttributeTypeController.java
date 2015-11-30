package com.epiphyllum.learnmvc.chapter6.web.controller.paramtype;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.epiphyllum.learnmvc.chapter6.model.UserModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.epiphyllum.learnmvc.chapter6.model.DataBinderTestModel;


@Controller
@RequestMapping("/method/param/annotation")
public class ModelAttributeTypeController {


    // 《《《《《《《非功能方法》》》》》》》》》》》
    // 为功能方法提供Model(会提供给view)
    @ModelAttribute("cityList")
    public List<String> cityList() {


        // 是不是每个功能方法都会调用cityList方法呢？？？  打个日志在这里
        System.out.println("cityList is called>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        return Arrays.asList("北京", "山东");
    }


    // 《《《《《《《非功能方法》》》》》》》》》》》
    // 为功能方法提供Model, 这里可以有RequestParam!!!!
    // 会从request中取出一些参数给此方法
    @ModelAttribute("user")  //①
    public UserModel getUser(@RequestParam(value="username", defaultValue="") String username) {

        //TODO 去数据库根据用户名查找用户对象
        UserModel user = new UserModel();
        user.setRealname("zhang");
        System.out.println(String.format("getUser is called, param[%s] return[user]", username, user));

        return user;
    }

    //==================================================================//
    // 功能方法区
    //==================================================================//
    //
    // 调用此方法前， 上面两个ModelAttribute方法已经被调用
    // 此时model中已经有了cityList, user两个属性了
    // 但是此方法有个ModelAttribute("user") UserModel user
    // 这个user其实与前面的getUser返回的为同一个
    //
    // 这里调用次序为:
    // cityList
    // ①处被调用, 得到UserModel:user放到了model中
    // ②处被调用, 在模型绑定时， 发现已经有了user,  所以直接使用
    //                         如果没有①的ModelAttribute, 那么这里会反射产生一个UserModel:user放入model
    //
    @RequestMapping(value="/model1") //②
    public String test1(@ModelAttribute("user") UserModel user, Model model) {
        System.out.println(model.containsAttribute("cityList"));
        System.out.println(user);
        return "success";
    }


    //http://localhost:9080/springmvc-chapter6/method/param/annotation/model2/username=wang
    // ?username=zhang&bool=yes&schooInfo.specialty=computer&hobbyList[0]=program&hobbyList[1]=music&map[key1]=value1&map[key2]=value2&state=blocked
    // 这里的username 绑定到了
    @RequestMapping(value="/model2/{username}")
    public String test2(@ModelAttribute("model") DataBinderTestModel model) {
        System.out.println(model);
        return "success";
    }
    

    // 参数与返回类型都有@ModelAttribute
    // 应该暴露返回值的模型数据给view
    // 最终交付给view的模型中， 返回值的UserModel:user2会覆盖原来模型中的参数user
    @RequestMapping(value="/model3")
    public @ModelAttribute("user2") UserModel test3(@ModelAttribute("user2") UserModel user) {
        UserModel user2 = new UserModel();
        user2.setUsername("zhang");
        return user2;
    }


    // 此处没有为@ModelAttribute名称, 默认的名称为userModel
    // model中包含了user
    @RequestMapping(value="/model4")
    public String test4(@ModelAttribute UserModel user, Model model) {
        System.out.println(model.containsAttribute("userModel"));
        return "success";
    }

    // 默认名称userModel
    @RequestMapping(value="/model5")
    public String test5(UserModel user, Model model) {
        System.out.println(model.containsAttribute("userModel"));
        return "success";
    }


    // stringList
    @RequestMapping(value="/model6")
    public @ModelAttribute List<String> test6() {
        return Arrays.asList("山东", "北京");
    }

    // userModelList
    @RequestMapping(value="/model7")
    public @ModelAttribute List<UserModel> test7() {
        return Arrays.asList(new UserModel(), new UserModel());
    }

    // 返回类型上的@ModelAttribute注解Map类型
    // 模型属性名称为map
    @RequestMapping(value="/model8")
    public @ModelAttribute Map<String, UserModel> test8() {
        return new HashMap<String, UserModel>();
    }
}
