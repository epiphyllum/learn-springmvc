package com.epiphyllum.learnmvc.chapter7.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.epiphyllum.learnmvc.chapter7.model.UserModel;
import com.epiphyllum.learnmvc.chapter7.web.controller.support.validator.UserModelValidator;


//
// Spring 2.5的编程式数据验证：
// Validator接口  validate(model, errors)
// Errors接口  hasErrors()
//
@Controller
public class RegisterSimpleFormController {

	// validator有状态 ？？   static可否？？？
	private UserModelValidator validator = new UserModelValidator();

	@ModelAttribute("user")        //① 暴露表单引用对象为模型数据
	public UserModel getUser() {
		return new UserModel();
	}
	
	@RequestMapping(value = "/validator", method = RequestMethod.GET)
	public String showRegisterForm() {   //② 表单展示
		return "validate/registerAndValidator";
	}

	@RequestMapping(value = "/validator", method = RequestMethod.POST)
	public String submitForm(
			@ModelAttribute("user") UserModel user,
			Errors errors) {           //③ 表单提交

	    //1 调用UserModelValidator的validate方法进行验证, 这里会填充errors!!!!!
		validator.validate(user, errors);
		
		if(errors.hasErrors()) { //2如果有错误再回到表单展示页面
			return showRegisterForm();
		}
		
		return "redirect:/success";
	}
}
