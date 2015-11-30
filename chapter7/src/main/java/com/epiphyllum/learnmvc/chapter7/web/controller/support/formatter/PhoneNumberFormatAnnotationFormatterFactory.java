package com.epiphyllum.learnmvc.chapter7.web.controller.support.formatter;

import java.util.HashSet;
import java.util.Set;

import com.epiphyllum.learnmvc.chapter7.model.PhoneNumberModel;
import org.springframework.format.AnnotationFormatterFactory;
import org.springframework.format.Parser;
import org.springframework.format.Printer;

//
// Formatter 实现了Parser & Printer接口
//
// 这里可以设置@PhoneNumber 支持在哪些字段类型上注解
//
// 注解格式化工厂
// PhoneNumber 是一个annotation
public class PhoneNumberFormatAnnotationFormatterFactory
	implements AnnotationFormatterFactory<PhoneNumber> {//①指定可以解析/格式化的字段注解类型

	private final Set<Class<?>> fieldTypes;  // 支持那些类型的对象注解!!!

	private final PhoneNumberFormatter formatter;  // string <--> model

	// 构造函数
	public PhoneNumberFormatAnnotationFormatterFactory() {
		Set<Class<?>> set = new HashSet<Class<?>>();
		set.add(PhoneNumberModel.class);  // 电话号码模型, 只支持这种类型数据的注解!, 也就是说@PhoneNumber注解只能放在PhoneNumberModel字段上
		this.fieldTypes = set;
		this.formatter = new PhoneNumberFormatter();//此处使用之前定义的Formatter实现
	}
	
	//②指定可以被解析/格式化的字段类型集合
	@Override
	public Set<Class<?>> getFieldTypes() {
		return fieldTypes;
	}
	
	//③根据注解信息和字段类型获取解析器
	@Override
	public Parser<?> getParser(PhoneNumber annotation, Class<?> fieldType) {
		return formatter;
	}

	//④根据注解信息和字段类型获取格式化器
	@Override	
	public Printer<?> getPrinter(PhoneNumber annotation, Class<?> fieldType) {
		return formatter;
	}

	// formatter 实现了Parser/Printer接口

}
