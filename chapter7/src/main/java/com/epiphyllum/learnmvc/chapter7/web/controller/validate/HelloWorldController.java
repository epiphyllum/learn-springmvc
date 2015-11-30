package com.epiphyllum.learnmvc.chapter7.web.controller.validate;

import javax.validation.Valid;    // Valid  JSR 303

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.epiphyllum.learnmvc.chapter7.model.UserModel;

@Controller
public class HelloWorldController {

	//
	// 请求参数绑定到模型的user属性:
	// 且要求Valid!!!   user的valid要求为username不为空
	//
	@RequestMapping("/validate/hello")
	public String validate(
			@Valid @ModelAttribute("user") UserModel user,
			Errors errors
	) {
		
		if(errors.hasErrors()) {
			return "validate/error";
		}
		return "redirect:/success";
	}
}
