package com.bbsuper.nev.beans.enums.result;

import com.bbsuper.nev.beans.vo.common.RetCode;

/**
 * 基本响应状态码
 * @author liwei
 * @date: 2018年9月10日 上午10:13:47
 *
 */
public enum BaseRetCode implements RetCode{
	
	SUCCESS(0,"成功"),
	
	FAIL(1,"失败"),
	
	EXCEPTION(2,"异常"),
	
	UNAUTHORIZED(3,"无访问权限"),
	
	NOT_LOGIN(4,"请登录后再访问"),
	
	NOT_USER_EXIST(5,"账号不存在"),
	
	USER_CHANGE(6,"用户名或密码已发生变化，请重新登录"),
	
	USER_DISABLED(7,"账号或角色已被禁用"),
	
	EINVAL(8,"请求参数不合法"),
	
	PARAM_MISSING(9,"参数缺失");
	
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

	private BaseRetCode(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

}
