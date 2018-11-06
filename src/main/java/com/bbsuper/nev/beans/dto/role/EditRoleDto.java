package com.bbsuper.nev.beans.dto.role;

import javax.validation.constraints.NotNull;

import com.bbsuper.nev.beans.po.AccountRoleEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * 编辑角色
 * @author liwei
 * @date: 2018年11月1日 下午2:56:04
 *
 */
@ApiModel(description = "编辑角色")
public class EditRoleDto extends AccountRoleDto{
	
	@NotNull
	@ApiModelProperty("id")
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public AccountRoleEntity toEntity(){
		AccountRoleEntity roleEntity = new AccountRoleEntity();
		roleEntity.setId(id);
		roleEntity.setName(getName());
		roleEntity.setPrivilegeCode(getPrivilegeCode());
		return roleEntity;
	}

}
