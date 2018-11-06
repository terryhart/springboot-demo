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
import com.bbsuper.nev.beans.dto.user.AccountCondition;
import com.bbsuper.nev.beans.dto.user.AccountDto;
import com.bbsuper.nev.beans.dto.user.EditAccountDto;
import com.bbsuper.nev.beans.po.AccountEntity;
import com.bbsuper.nev.beans.vo.city.CityInfo;
import com.bbsuper.nev.beans.vo.common.ResultData;
import com.bbsuper.nev.beans.vo.role.AccountRoleInfo;
import com.bbsuper.nev.beans.vo.user.UserInfo;
import com.bbsuper.nev.service.AccountService;
import com.bbsuper.nev.service.CityService;
import com.bbsuper.nev.service.RoleService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 账号列表
 * @author liwei
 * @date: 2018年10月29日 上午11:10:33
 *
 */
@Api(tags="账号列表")
@RequestMapping("/system/account")
@RestController
public class AccountController {
	
	@Resource
	private AccountService accountService;
	
	@Resource
	private RoleService roleService;
	
	@Resource
	private CityService cityService;
	
	@PostMapping("/queryList")
	@ApiOperation(value = "查询列表", notes = "按条件查询列表")
	@Slf4j
	public ResultData<PaginationResult<UserInfo>> queryList(@RequestBody @Validated PaginationCondition<AccountCondition> condition){

		return accountService.queryListByCondition(condition);
	}
	
	@PostMapping("/roleList")
	@ApiOperation(value = "启用的角色列表", notes = "启用的角色列表")
	@Slf4j
	public ResultData<List<AccountRoleInfo>> roleList(){

		return roleService.normalList();
	}
	
	@PostMapping("/cityList")
	@ApiOperation(value = "启用的城市列表", notes = "启用的城市列表")
	@Slf4j
	public ResultData<List<CityInfo>> cityList(){
		
		return cityService.normalList();
	}
	
	@PostMapping("/add")
	@ApiOperation(value = "新增账号", notes = "新增账号")
	@Slf4j
	public ResultData<Void> add(@Validated AccountDto accountDto){
		return accountService.add(accountDto);
	}
	
	@PostMapping("/edit")
	@ApiOperation(value = "编辑账号", notes = "编辑账号")
	@Slf4j
	public ResultData<Void> edit(@Validated EditAccountDto editAccountDto){
		return accountService.edit(editAccountDto);
	}
	
	@PostMapping("/resetPassword")
	@ApiOperation(value = "重置密码", notes = "重置密码")
	@Slf4j
	public ResultData<Void> resetPassword(@ApiParam("账户id") @RequestParam("id") String id){
		
		return accountService.resetPassword(Long.parseLong(id));
	}
	
	@PostMapping("/delete")
	@ApiOperation(value = "删除账号", notes = "删除账号")
	@Slf4j
	public ResultData<Void> delete(@ApiParam("账户id") @RequestParam("id") String id){
		return accountService.modifyStatus(Long.parseLong(id), AccountEntity.Status.DELETE);
	}
	
	@PostMapping("/disabled")
	@ApiOperation(value = "禁用账号", notes = "禁用账号")
	@Slf4j
	public ResultData<Void> disabled(@ApiParam("账户id") @RequestParam("id") String id){
		return accountService.modifyStatus(Long.parseLong(id), AccountEntity.Status.DISABLED);
	}
	
	@PostMapping("/enabled")
	@ApiOperation(value = "启用账号", notes = "启用账号")
	@Slf4j
	public ResultData<Void> enabled(@ApiParam("账户id") @RequestParam("id") String id){
		return accountService.modifyStatus(Long.parseLong(id), AccountEntity.Status.NORMAL);
	}


}
