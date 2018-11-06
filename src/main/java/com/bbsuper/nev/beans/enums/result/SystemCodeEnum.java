package com.bbsuper.nev.beans.enums.result;

import com.bbsuper.nev.beans.vo.common.RetCode;

/**
 * 系统响应枚举
 * @author liwei
 * @date: 2018年10月29日 下午1:25:54
 *
 */
public enum SystemCodeEnum implements RetCode{
	
	
	USER_EXIST(1101,"账号已存在"),
	
	PWD_SAME(1102,"新旧密码相同"),
	
	OLD_PWD_ERR(1103,"原密码错误"),
	
	NOT_CHANGE_STATUS(1104,"初始的最高权限账号，不允许修改状态"),
	
	NOT_CHANGE_ADMIN(1105,"初始管理员角色，不允许修改");

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

	private SystemCodeEnum(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

}
