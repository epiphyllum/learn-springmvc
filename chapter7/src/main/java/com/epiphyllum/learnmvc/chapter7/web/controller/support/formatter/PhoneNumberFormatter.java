package com.epiphyllum.learnmvc.chapter7.web.controller.support.formatter;

import java.text.ParseException;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.epiphyllum.learnmvc.chapter7.model.PhoneNumberModel;
import org.springframework.format.Formatter;
import org.springframework.util.StringUtils;


//
// 需要实现的方法: print(T t, Locale locale)
//               parse(String text, Locale locale)
//
public class PhoneNumberFormatter implements Formatter<PhoneNumberModel> {

	Pattern pattern = Pattern.compile("^(\\d{3,4})-(\\d{7,8})$");

	// 将模型转换为字符串
	@Override
	public String print(PhoneNumberModel phoneNumber, Locale locale) {
		if(phoneNumber == null) {
			return "";
		}
		return new StringBuilder().append(phoneNumber.getAreaCode()).append("-")
								  .append(phoneNumber.getPhoneNumber()).toString();
	}

	// 将text转换为模型, 并返回
	@Override
	public PhoneNumberModel parse(String text, Locale locale) throws ParseException {
		if(!StringUtils.hasLength(text)) {
			//①如果source为空 返回null
			return null;
		}
		Matcher matcher = pattern.matcher(text);
		if(matcher.matches()) {
			//②如果匹配 进行转换
			PhoneNumberModel phoneNumber = new PhoneNumberModel();
			phoneNumber.setAreaCode(matcher.group(1));
			phoneNumber.setPhoneNumber(matcher.group(2));
			return phoneNumber;
		} else {
			//③如果不匹配 转换失败
			throw new IllegalArgumentException(String.format("类型转换失败，需要格式[010-12345678]，但格式是[%s]", text));
		}
	}
}
