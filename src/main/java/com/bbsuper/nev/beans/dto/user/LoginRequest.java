package com.bbsuper.nev.beans.dto.user;

import javax.validation.constraints.NotEmpty;

import com.alibaba.fastjson.JSON;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 登录请求
 * @author liwei
 * @date: 2018年10月25日 上午10:01:54
 *
 */
@ApiModel(description = "登录请求数据")
public class LoginRequest {
	
	@ApiModelProperty("设备ID")
	@NotEmpty
	private String deviceId;
	
	@ApiModelProperty("账号")
	@NotEmpty
    private String account;

	@ApiModelProperty("密码")
	@NotEmpty
    private String password;
    
	@ApiModelProperty("验证码")
	@NotEmpty
    private String imageCode;

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getImageCode() {
		return imageCode;
	}

	public void setImageCode(String imageCode) {
		this.imageCode = imageCode;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}
	
	

}
