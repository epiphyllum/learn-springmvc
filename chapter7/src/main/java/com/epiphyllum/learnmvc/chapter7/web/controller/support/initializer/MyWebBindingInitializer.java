package com.epiphyllum.learnmvc.chapter7.web.controller.support.initializer;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.context.request.WebRequest;

import com.epiphyllum.learnmvc.chapter7.model.PhoneNumberModel;
import com.epiphyllum.learnmvc.chapter7.web.controller.support.editor.PhoneNumberEditor;


// 批量注册PropertyEditor
public class MyWebBindingInitializer implements WebBindingInitializer {

	@Override
	public void initBinder(WebDataBinder binder, WebRequest request) {
		//注册自定义的属性编辑器

		//1、日期
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		CustomDateEditor dateEditor = new CustomDateEditor(df, true);

		//2. 电话号码
		PhoneNumberEditor phoneNumberEditor = new PhoneNumberEditor();


		// 如果命令对象有Date类型的属性，          将使用dateEditor进行类型转换
		// 如果命令对象有PhoneNumberModel类型属性，将使用phoneNumberEditor进行类型转换
		binder.registerCustomEditor(Date.class, dateEditor);
		binder.registerCustomEditor(PhoneNumberModel.class, phoneNumberEditor);
	}

}
