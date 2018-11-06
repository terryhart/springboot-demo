package com.bbsuper.nev.service;


import java.util.List;

import com.bbsuper.nev.beans.dto.paging.PaginationCondition;
import com.bbsuper.nev.beans.dto.paging.PaginationResult;
import com.bbsuper.nev.beans.dto.role.AccountRoleDto;
import com.bbsuper.nev.beans.dto.role.EditRoleDto;
import com.bbsuper.nev.beans.dto.role.RoleCondition;
import com.bbsuper.nev.beans.po.AccountRoleEntity;
import com.bbsuper.nev.beans.vo.common.ResultData;
import com.bbsuper.nev.beans.vo.role.AccountRoleInfo;

/**
 * 角色相关服务
 * @author liwei
 * @date: 2018年10月26日 上午10:38:20
 *
 */
public interface RoleService {
	/**
	 * 查询角色列表
	 * @param condition
	 * @return
	 */
	ResultData<PaginationResult<AccountRoleInfo>> queryList(PaginationCondition<RoleCondition> condition);
	/**
	 * 新增角色
	 * @param accountRoleDto
	 * @return
	 */
	ResultData<Void> add(AccountRoleDto accountRoleDto);
	
	/**
	 * 编辑角色
	 * @param editRoleDto
	 * @return
	 */
	ResultData<Void> edit(EditRoleDto editRoleDto);
	/**
	 * 修改状态
	 * @param parseLong
	 * @return
	 */
	ResultData<Void> modifyStatus(long id,AccountRoleEntity.Status status);
	/**
	 * 查询所有启用的角色列表
	 * @return
	 */
	ResultData<List<AccountRoleInfo>> normalList();

	

}
