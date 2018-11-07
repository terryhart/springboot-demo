package com.bbsuper.nev.beans.vo.common;

import com.bbsuper.nev.beans.enums.result.BaseRetCode;

/**
 * 响应状态码
 * @author liwei
 * @date: 2018年8月16日 下午3:26:02
 *
 */
public interface RetCode {
	
	/**
	 * 响应码，0表示成功
	 * @return
	 */
	public int getCode();
	
	/**
	 * 附带响应消息
	 * @return
	 */
	public String getMsg();
	
	/**
	 * 是否成功
	 * @return
	 */
	default boolean success(){
		return getCode() == BaseRetCode.SUCCESS.getCode();
	}

}
