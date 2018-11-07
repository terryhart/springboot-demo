package com.bbsuper.nev.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import com.bbsuper.nev.beans.dto.paging.PaginationCondition;
import com.bbsuper.nev.beans.dto.paging.PaginationResult;
import com.bbsuper.nev.beans.dto.role.AccountRoleDto;
import com.bbsuper.nev.beans.dto.role.EditRoleDto;
import com.bbsuper.nev.beans.dto.role.RoleCondition;
import com.bbsuper.nev.beans.enums.result.BaseRetCode;
import com.bbsuper.nev.beans.enums.result.SystemCodeEnum;
import com.bbsuper.nev.beans.po.AccountRoleEntity;
import com.bbsuper.nev.beans.vo.common.ResultData;
import com.bbsuper.nev.beans.vo.role.AccountRoleInfo;
import com.bbsuper.nev.dao.mapping.AccountRoleEntityMapper;
import com.bbsuper.nev.service.RoleService;
import com.bbsuper.nev.service.handler.ParamCheckService;
/**
 * 
 * @author liwei
 * @date: 2018年11月7日 上午10:44:05
 *
 */
@Service
public class RoleServiceImpl implements RoleService{
	
	@Resource
	private AccountRoleEntityMapper accountRoleEntityMapper;
	
	@Resource
	private ParamCheckService paramCheckService;

	@Override
	public ResultData<PaginationResult<AccountRoleInfo>> queryList(PaginationCondition<RoleCondition> condition) {
		List<AccountRoleEntity> list = accountRoleEntityMapper.queryListByCondition(condition);
		int count = accountRoleEntityMapper.queryCountByCondition(condition);
		List<AccountRoleInfo> result = list.stream().map(r->{
			return r.toInfo();
		}).collect(Collectors.toList());
		PaginationResult<AccountRoleInfo> instance = PaginationResult.getInstance(result, condition.getPageInfo(), count);
		return ResultData.getInstance(instance);
	}
	
	@Override
	public ResultData<List<AccountRoleInfo>> normalList() {
		RoleCondition roleCondition = new RoleCondition();
		roleCondition.setStatus(AccountRoleEntity.Status.NORMAL);
		List<AccountRoleEntity> list = accountRoleEntityMapper.queryListByCondition(new PaginationCondition<RoleCondition>(roleCondition));
		List<AccountRoleInfo> collect = list.stream().map(r->{
			return r.toInfo();
		}).collect(Collectors.toList());
		return ResultData.getInstance(collect);
	}

	@Override
	public ResultData<Void> add(AccountRoleDto accountRoleDto) {
		ResultData<Void> code = paramCheckService.checkRole(accountRoleDto);
		if(!code.success()){
			return code;
		}
		int insert = accountRoleEntityMapper.insert(accountRoleDto.toAddEntity());
		return insert==1?ResultData.getInstance():ResultData.getInstance(BaseRetCode.FAIL);
	}
	
	@CacheEvict(value = "userInfo",allEntries = true)
	@Override
	public ResultData<Void> edit(EditRoleDto editRoleDto) {
		ResultData<Void> code = paramCheckService.checkRole(editRoleDto);
		if(!code.success()){
			return code;
		}
		if(editRoleDto.getId().longValue()==1L){
			return ResultData.getInstance(SystemCodeEnum.NOT_CHANGE_ADMIN);
		}
		int updateById = accountRoleEntityMapper.updateById(editRoleDto.toEntity());
		return updateById==1?ResultData.getInstance():ResultData.getInstance(BaseRetCode.FAIL);
	}
	
	@CacheEvict(value = "userInfo",allEntries = true)
	@Override
	public ResultData<Void> modifyStatus(long id,AccountRoleEntity.Status status) {
		if(id==1L){
			return ResultData.getInstance(SystemCodeEnum.NOT_CHANGE_ADMIN);
		}
		AccountRoleEntity entity = new AccountRoleEntity();
		entity.setId(id);
		entity.setStatus(status);
		int modify = accountRoleEntityMapper.updateById(entity);
		return modify==1?ResultData.getInstance():ResultData.getInstance(BaseRetCode.FAIL);
	}


}
