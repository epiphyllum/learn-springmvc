package com.epiphyllum.learnmvc.chapter7.web.controller.support.converter;

import org.springframework.core.convert.converter.Converter;

import com.epiphyllum.learnmvc.chapter7.model.PhoneNumberModel;

//PhoneNumberModel------>String


public class PhoneNumberToStringConverter implements Converter<PhoneNumberModel, String> {

	@Override
	public String convert(PhoneNumberModel source) {
		
		if(source == null) {
			return "";
		}
		
		return new StringBuilder()
					.append(source.getAreaCode()).append("-")
					.append(source.getPhoneNumber()).toString();

	}

}
