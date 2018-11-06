package com.bbsuper.nev.beans.enums.result;

import com.bbsuper.nev.beans.vo.common.RetCode;

/**
 * 登录响应枚举
 * @author liwei
 * @date: 2018年10月25日 上午10:29:34
 *
 */
public enum LoginCodeEnum implements RetCode{
	
	
	PWD_ERR(1001,"账号或密码错误"),
	
	IMG_CODE_ERR(1002,"验证码错误"),
	
	LOCKING(1003,"账号被锁定,10分钟后再试"),
	
	NOT_EXIST(1004,"账号不存在"),
	
	DISABLED(1005,"账号或角色被禁用");

	private int code;
	
	private String msg;

	@Override
	public int getCode() {
		return code;
	}

	@Override
	public String getMsg() {
		return msg;
	}

	private LoginCodeEnum(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

}
