package com.epiphyllum.learnmvc.chapter7.web.controller.support.formatter;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.format.support.DefaultFormattingConversionService;

import com.epiphyllum.learnmvc.chapter7.model.FormatterModel;
import com.epiphyllum.learnmvc.chapter7.model.PhoneNumberModel;

public class CustomerFieldFormatterTest {

	
	@Test
	public void test() throws SecurityException, NoSuchFieldException {

		//创建格式化服务
		DefaultFormattingConversionService conversionService = new DefaultFormattingConversionService();

		//
	    //添加自定义的注解格式化工厂
		conversionService.addFormatterForFieldAnnotation(new PhoneNumberFormatAnnotationFormatterFactory());
		
		FormatterModel model = new FormatterModel();

		TypeDescriptor descriptor       = new TypeDescriptor(FormatterModel.class.getDeclaredField("phoneNumber"));
		TypeDescriptor stringDescriptor = TypeDescriptor.valueOf(String.class);

		// conversion service 调用!!
		PhoneNumberModel value = (PhoneNumberModel) conversionService.convert("010-12345678", stringDescriptor, descriptor);
		model.setPhoneNumber(value);
		
		Assert.assertEquals("010-12345678", conversionService.convert(model.getPhoneNumber(), descriptor, stringDescriptor));
		
	}
	
}
