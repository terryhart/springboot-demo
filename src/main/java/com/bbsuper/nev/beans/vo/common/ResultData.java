package com.bbsuper.nev.beans.vo.common;


import java.io.Serializable;
import com.alibaba.fastjson.JSON;
import com.bbsuper.nev.beans.enums.result.BaseRetCode;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * 返回数据
 * @author liwei
 * @date: 2018年8月16日 下午3:26:19
 *
 */
@ApiModel(description = "返回数据")
public class ResultData<T> implements RetCode,Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3398323943665457721L;

	@ApiModelProperty("状态码，0表示成功")
	private int code;
	
	@ApiModelProperty("消息")
	private String msg;
	
	@ApiModelProperty("数据")
	private T data;
	
	@Override
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
	@Override
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public ResultData(RetCode retCode) {
		this.code = retCode.getCode();
		this.msg = retCode.getMsg();
	}

	public ResultData(T data) {
		this();
		this.data = data;
	}
	
	public ResultData() {
		this.code = BaseRetCode.SUCCESS.getCode();
		this.msg = BaseRetCode.SUCCESS.getMsg();
	}

	public static <T> ResultData<T> getInstance(RetCode retCode){
		return new ResultData<T>(retCode);
	}
	
	public static <T> ResultData<T> getInstance(T data){
		return new ResultData<T>(data);
	}
	
	public static ResultData<Void> getInstance(){
		return new ResultData<Void>();
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}
	
	
	

}
