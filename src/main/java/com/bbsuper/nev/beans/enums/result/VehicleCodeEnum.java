package com.bbsuper.nev.beans.enums.result;

import com.bbsuper.nev.beans.vo.common.RetCode;
/**
 * 车辆相关错误码
 * @author liwei
 * @date: 2018年10月30日 下午1:10:45
 *
 */
public enum VehicleCodeEnum implements RetCode{
	
	
	NOT_VEHICLE_TYPE(1201,"不存在该车型"),
	
	IMPORT_VEHICLE_FAIL(1202,"导入车辆失败"),
	
	DATA_EMPTY(1203,"数据为空"),
	
	PARAM_MISSING(1204,"表格参数缺失"),
	
	NOT_TYPE(1205,"不存在的车型");

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

	private VehicleCodeEnum(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

}
