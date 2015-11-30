package com.epiphyllum.learnmvc.chapter7.web.controller.support.converter;

import java.util.List;

import com.epiphyllum.learnmvc.chapter7.model.PhoneNumberModel;
import junit.framework.Assert;

import org.junit.Test;
import org.springframework.core.convert.support.DefaultConversionService;

public class ConverterTest {
	
	@Test
	public void testStringToPhoneNumberConvert() {

		// 默认的conversion service
		DefaultConversionService conversionService = new DefaultConversionService();

		// 添加converter
		conversionService.addConverter(new StringToPhoneNumberConverter());


		// 测试converter
		String phoneNumberStr = "010-12345678";
		PhoneNumberModel phoneNumber = conversionService.convert(phoneNumberStr, PhoneNumberModel.class);
		
		Assert.assertEquals("010", phoneNumber.getAreaCode());
	}

	@Test
	public void testOtherConvert() {

		// 直接调用默认提供的conversion service
		// String -> Boolean
		// String -> List

		DefaultConversionService conversionService = new DefaultConversionService();
		
		//"1"--->true
		Assert.assertEquals(Boolean.valueOf(true), conversionService.convert("1", Boolean.class));
		
		//"1,2,3,4"--->List
		Assert.assertEquals(4, conversionService.convert("1,2,3,4", List.class).size());
	}
}
