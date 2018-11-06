package com.bbsuper.nev.beans.dto.role;

import java.io.Serializable;

import com.alibaba.fastjson.JSON;
import com.bbsuper.nev.beans.po.AccountRoleEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 角色查询条件
 * @author liwei
 * @date: 2018年10月26日 上午10:30:01
 *
 */
@ApiModel(description = "角色查询条件")
public class RoleCondition implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1049706263660899234L;

	@ApiModelProperty("状态")
	private AccountRoleEntity.Status status;
	
	@ApiModelProperty("角色名称")
	private String name;

	public AccountRoleEntity.Status getStatus() {
		return status;
	}

	public void setStatus(AccountRoleEntity.Status status) {
		this.status = status;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}
	
	

}
