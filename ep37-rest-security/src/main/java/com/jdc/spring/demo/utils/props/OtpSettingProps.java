package com.jdc.spring.demo.utils.props;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@Data
@ConfigurationProperties(prefix = "app.setting.otp")
public class OtpSettingProps {

	private int otpLife;
	private int optLimitDuration;
	private int otpLimitCount;
}
