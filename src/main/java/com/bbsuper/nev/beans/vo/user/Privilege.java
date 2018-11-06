package com.bbsuper.nev.beans.vo.user;

import java.io.Serializable;

import com.bbsuper.nev.beans.enums.PrivilegeEnum;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 权限列表，无递归结构
 * @author liwei
 * @date: 2018年10月25日 下午4:00:48
 *
 */
@ApiModel("权限")
public class Privilege implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8271887841328360450L;
	
	@ApiModelProperty("权限code")
	private int code;
	
	@ApiModelProperty("权限名称")
	private String desc;
	
	@ApiModelProperty("允许访问的uri")
	private String uri;
	
	public enum Type{
		DIRECTORY,ACTUAL;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public Privilege() {
		super();
	}

	public Privilege(PrivilegeEnum privilegeEnum) {
		this.code = privilegeEnum.getCode();
		this.desc = privilegeEnum.getDesc();
		this.uri = privilegeEnum.getUri();
	}
	

}
