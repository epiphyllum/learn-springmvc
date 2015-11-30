package com.epiphyllum.learnmvc.chapter7.web.controller.support.formatter;

import com.epiphyllum.learnmvc.chapter7.model.PhoneNumberModel;
import junit.framework.Assert;

import org.junit.Test;
import org.springframework.format.support.DefaultFormattingConversionService;

public class CustomerFormatterTest {

	@Test
	public void test() {

		// 默认的的格式化转换服务
		DefaultFormattingConversionService conversionService = new DefaultFormattingConversionService();

		// 增加新的formmater
		conversionService.addFormatter(new PhoneNumberFormatter());

		// 测试！！！
		PhoneNumberModel phoneNumber = new PhoneNumberModel("010", "12345678");
		Assert.assertEquals("010-12345678", conversionService.convert(phoneNumber, String.class));
		
		Assert.assertEquals("010", conversionService.convert("010-12345678", PhoneNumberModel.class).getAreaCode());
		
	}
	
	
}
