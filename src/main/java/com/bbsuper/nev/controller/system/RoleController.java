package com.bbsuper.nev.controller.system;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bbsuper.nev.annotation.Slf4j;
import com.bbsuper.nev.beans.dto.paging.PaginationCondition;
import com.bbsuper.nev.beans.dto.paging.PaginationResult;
import com.bbsuper.nev.beans.dto.role.AccountRoleDto;
import com.bbsuper.nev.beans.dto.role.EditRoleDto;
import com.bbsuper.nev.beans.dto.role.RoleCondition;
import com.bbsuper.nev.beans.enums.PrivilegeEnum;
import com.bbsuper.nev.beans.po.AccountRoleEntity;
import com.bbsuper.nev.beans.vo.common.ResultData;
import com.bbsuper.nev.beans.vo.role.AccountRoleInfo;
import com.bbsuper.nev.beans.vo.user.Privilege;
import com.bbsuper.nev.service.RoleService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 角色管理
 * @author liwei
 * @date: 2018年10月26日 上午10:27:32
 *
 */
@Api(tags="角色管理")
@RequestMapping("/system/role")
@RestController
public class RoleController {
	
	@Resource
	private RoleService roleService;
	
	@PostMapping("/queryList")
	@ApiOperation(value = "查询列表", notes = "按条件查询类表")
	@Slf4j
	public ResultData<PaginationResult<AccountRoleInfo>> queryList(@RequestBody @Validated PaginationCondition<RoleCondition> condition){

		return roleService.queryList(condition);
	}
	
	@PostMapping("/authorityList")
	@ApiOperation(value = "所有权限列表", notes = "所有权限列表")
	@Slf4j
	public ResultData<List<Privilege>> authorityList(){
		return ResultData.getInstance(PrivilegeEnum.getAllPrivilege());
	}
	
	@PostMapping("/add")
	@ApiOperation(value = "新增角色", notes = "新增角色")
	@Slf4j
	public ResultData<Void> add(@Validated AccountRoleDto accountRoleDto){
		return roleService.add(accountRoleDto);
	}
	
	@PostMapping("/edit")
	@ApiOperation(value = "编辑角色", notes = "编辑角色")
	@Slf4j
	public ResultData<Void> edit(@Validated EditRoleDto editRoleDto){
		return roleService.edit(editRoleDto);
	}
	
	@PostMapping("/delete")
	@ApiOperation(value = "删除角色", notes = "删除角色")
	@Slf4j
	public ResultData<Void> delete(@ApiParam("角色id") @RequestParam("id") String id){
		return roleService.modifyStatus(Long.parseLong(id), AccountRoleEntity.Status.DELETE);
	}
	
	@PostMapping("/disabled")
	@ApiOperation(value = "禁用角色", notes = "禁用角色")
	@Slf4j
	public ResultData<Void> disabled(@ApiParam("角色id") @RequestParam("id") String id){
		return roleService.modifyStatus(Long.parseLong(id), AccountRoleEntity.Status.DISABLED);
	}
	
	@PostMapping("/enabled")
	@ApiOperation(value = "启用角色", notes = "启用角色")
	@Slf4j
	public ResultData<Void> enabled(@ApiParam("角色id") @RequestParam("id") String id){
		return roleService.modifyStatus(Long.parseLong(id), AccountRoleEntity.Status.NORMAL);
	}

}
