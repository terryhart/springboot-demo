package com.bbsuper.nev.beans.enums.result;

import com.bbsuper.nev.beans.vo.common.RetCode;
/**
 * 客户相关错误码
 * @author liwei
 * @date: 2018年10月30日 下午1:10:45
 *
 */
public enum CustomerCodeEnum implements RetCode{
	
	
	NOT_STATUS(1301,"不合法的状态扭转"),
	
	NOT_EXIST_VEHICLE(1302,"未匹配到正确的车辆信息");

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

	private CustomerCodeEnum(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

}
