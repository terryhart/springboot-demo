package com.bbsuper.nev.beans.vo.role;

import com.alibaba.fastjson.JSON;
import com.bbsuper.nev.beans.po.AccountRoleEntity;
import com.bbsuper.nev.beans.po.AccountRoleEntity.Status;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("角色详情")
public class AccountRoleInfo {
	
	@ApiModelProperty("id")
	private Long id;
	
	@ApiModelProperty("角色名称")
    private String name;
	
	@ApiModelProperty("权限code，多个用逗号分隔")
	private String privilegeCode;

	@ApiModelProperty("角色状态 NORMAL,DISABLED")
    private AccountRoleEntity.Status status;
    
	@ApiModelProperty("添加日期，格式yyyy-MM-dd")
    private String createDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getPrivilegeCode() {
		return privilegeCode;
	}

	public void setPrivilegeCode(String privilegeCode) {
		this.privilegeCode = privilegeCode;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}
    
    
    
    

}
