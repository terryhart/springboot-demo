package com.bbsuper.nev.beans.vo.common;

import com.bbsuper.nev.beans.enums.result.BaseRetCode;

/**
 * 响应状态码
 * @author liwei
 * @date: 2018年8月16日 下午3:26:02
 *
 */
public interface RetCode {
	
	public int getCode();
	
	public String getMsg();
	
	default boolean success(){
		return getCode() == BaseRetCode.SUCCESS.getCode();
	}

}
