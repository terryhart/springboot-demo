package com.bbsuper.nev.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import com.bbsuper.nev.beans.dto.paging.PaginationCondition;
import com.bbsuper.nev.beans.dto.paging.PaginationResult;
import com.bbsuper.nev.beans.dto.user.AccountCondition;
import com.bbsuper.nev.beans.dto.user.AccountDto;
import com.bbsuper.nev.beans.dto.user.EditAccountDto;
import com.bbsuper.nev.beans.enums.result.BaseRetCode;
import com.bbsuper.nev.beans.enums.result.SystemCodeEnum;
import com.bbsuper.nev.beans.po.AccountEntity;
import com.bbsuper.nev.beans.po.AccountEntity.Status;
import com.bbsuper.nev.beans.vo.common.ResultData;
import com.bbsuper.nev.beans.vo.user.UserInfo;
import com.bbsuper.nev.dao.mapping.AccountEntityMapper;
import com.bbsuper.nev.service.AccountService;
import com.bbsuper.nev.service.CityService;
import com.bbsuper.nev.service.cache.UserCacheService;
import com.bbsuper.nev.service.handler.ParamCheckService;
import com.bbsuper.nev.utils.EncryptionUtils;

/**
 * 账号相关服务
 * @author liwei
 * @date: 2018年10月29日 上午11:33:08
 *
 */
@Service
public class AccountServiceImpl implements AccountService{
	
	@Resource
	private AccountEntityMapper accountEntityMapper;
	
	@Resource
	private ParamCheckService paramCheckService;
	
	@Resource
	private UserCacheService userCacheService;
	
	@Resource
	private CityService cityService;

	@Override
	public ResultData<PaginationResult<UserInfo>> queryListByCondition(PaginationCondition<AccountCondition> condition) {
		List<UserInfo> queryListByCondition = accountEntityMapper.queryListByCondition(condition);
		queryListByCondition.forEach(u->{
			u.setPassword(null);
		});
		cityService.addCity(queryListByCondition);
		int count = accountEntityMapper.queryCountByCondition(condition);
		PaginationResult<UserInfo> result = PaginationResult.getInstance(queryListByCondition, condition.getPageInfo(), count);
		return ResultData.getInstance(result);
	}

	@Override
	@CacheEvict(key = "#id",value = "userInfo")
	public ResultData<Void> modifyStatus(long id, Status status) {
		if(id == 1L){
			return ResultData.getInstance(SystemCodeEnum.NOT_CHANGE_STATUS);
		}
		AccountEntity entity = new AccountEntity();
		entity.setId(id);
		entity.setStatus(status);
		int updateById = accountEntityMapper.updateById(entity);
		return updateById == 1?ResultData.getInstance():ResultData.getInstance(BaseRetCode.FAIL);
	}

	@Override
	@CacheEvict(key = "#editAccountDto.id",value = "userInfo")
	public ResultData<Void> edit(EditAccountDto editAccountDto) {

		//账号是否存在
		UserInfo userInfo = userCacheService.queryUserInfoById(editAccountDto.getId());
		if(!userInfo.getAccount().equals(editAccountDto.getAccount())){
			UserInfo account = userCacheService.queryUserInfoByAccount(editAccountDto.getAccount());
			if(account!=null){
				return ResultData.getInstance(SystemCodeEnum.USER_EXIST);
			}
		}
		int updateById = accountEntityMapper.updateById(editAccountDto.toEntity());
		
		return updateById==1?ResultData.getInstance():ResultData.getInstance(BaseRetCode.FAIL);
	}

	@Override
	public ResultData<Void> add(AccountDto accountDto) {
		//账号是否存在
		UserInfo account = userCacheService.queryUserInfoByAccount(accountDto.getAccount());
		if(account!=null){
			return ResultData.getInstance(SystemCodeEnum.USER_EXIST);
		}
		int insert = accountEntityMapper.insert(accountDto.toAddEntity());
		return insert==1?ResultData.getInstance():ResultData.getInstance(BaseRetCode.FAIL);
	}

	@Override
	@CacheEvict(key = "#id",value = "userInfo")
	public ResultData<Void> resetPassword(long id) {
		AccountEntity accountEntity = new AccountEntity();
		accountEntity.setId(id);
		accountEntity.setPassword(EncryptionUtils.md5("123456"));
		int updateById = accountEntityMapper.updateById(accountEntity);
		return updateById==1?ResultData.getInstance():ResultData.getInstance(BaseRetCode.FAIL);
	}

	@Override
	public List<UserInfo> queryListByData(PaginationCondition<AccountCondition> paginationCondition) {
		List<UserInfo> queryListByCondition = accountEntityMapper.queryListByCondition(paginationCondition);
		cityService.addCity(queryListByCondition);
		return queryListByCondition;
	}

}
