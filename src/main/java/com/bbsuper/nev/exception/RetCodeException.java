package com.bbsuper.nev.exception;

import com.bbsuper.nev.beans.vo.common.ResultData;
import com.bbsuper.nev.beans.vo.common.RetCode;

/**
 * 附带RetCode的异常
 * @author liwei
 * @date: 2018年11月2日 下午5:31:16
 *
 */
public class RetCodeException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2010420346426677267L;
	
	private ResultData<Void> retCode;
	

	public ResultData<Void> getRetCode() {
		return retCode;
	}


	public RetCodeException(RetCode retCode) {
		super(retCode.getMsg());
		this.retCode = ResultData.getInstance(retCode);
	}
	
	

}
