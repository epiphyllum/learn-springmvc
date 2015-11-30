package com.epiphyllum.learnmvc.chapter7.web.controller.support.editor;

import java.beans.PropertyEditorSupport;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.util.StringUtils;

import com.epiphyllum.learnmvc.chapter7.model.PhoneNumberModel;

public class PhoneNumberEditor extends PropertyEditorSupport {
	
	private Pattern pattern = Pattern.compile("^(\\d{3,4})-(\\d{7,8})$");
	
	@Override
	public void setAsText(String text) throws IllegalArgumentException {

		//如果没值，设值为null
		if(text == null || !StringUtils.hasLength(text)) {
			setValue(null);    // 继承的方法
			return;
		}

		//
		// 被设置的text需要满足pattern. 满足后， 将text ---> PhoneNumberModel, setValue(phoneNumber)
		//
		Matcher matcher = pattern.matcher(text);
		if(matcher.matches()) {
			PhoneNumberModel phoneNumber = new PhoneNumberModel();
			phoneNumber.setAreaCode(matcher.group(1));
			phoneNumber.setPhoneNumber(matcher.group(2));
			setValue(phoneNumber); 
		} else {
			throw new IllegalArgumentException(
					String.format("类型转换失败，需要格式[010-12345678]，但格式是[%s]", text));
		}
	}
	
	@Override
	public String getAsText() {

		// 调用getValue()获取model 转换为PhoneNumberModel
		// 再把PhoneNumberModel转换为字符串
		PhoneNumberModel phoneNumber = ((PhoneNumberModel)getValue());
		return phoneNumber == null ?
				"" : phoneNumber.getAreaCode() + "-" + phoneNumber.getPhoneNumber();
	}
	

}
