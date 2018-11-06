package com.bbsuper.nev.exception;
/**
 * 参数缺失异常
 * @author liwei
 * @date: 2018年10月31日 上午11:10:31
 *
 */
public class MissingParameterException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8593061655591659694L;

	public MissingParameterException(String parameter) {
		super("参数"+parameter+"缺失");
	}
}
