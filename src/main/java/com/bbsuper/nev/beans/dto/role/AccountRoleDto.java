package com.bbsuper.nev.beans.dto.role;

import javax.validation.constraints.NotEmpty;

import com.alibaba.fastjson.JSON;
import com.bbsuper.nev.beans.po.AccountRoleEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 角色
 * @author liwei
 * @date: 2018年10月26日 下午2:04:22
 *
 */
@ApiModel(description = "角色")
public class AccountRoleDto {
	
	@NotEmpty
	@ApiModelProperty("角色名")
    private String name;
	
	@NotEmpty
	@ApiModelProperty("权限code，多个用逗号分隔")
    private String privilegeCode;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrivilegeCode() {
		return privilegeCode;
	}

	public void setPrivilegeCode(String privilegeCode) {
		this.privilegeCode = privilegeCode;
	}
	
	public AccountRoleEntity toAddEntity(){
		AccountRoleEntity roleEntity = new AccountRoleEntity();
		roleEntity.setName(name);
		roleEntity.setPrivilegeCode(privilegeCode);
		roleEntity.setStatus(AccountRoleEntity.Status.NORMAL);
		return roleEntity;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}
    
    

}
