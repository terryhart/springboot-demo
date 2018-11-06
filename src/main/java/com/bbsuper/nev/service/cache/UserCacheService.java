package com.bbsuper.nev.service.cache;


import javax.annotation.Resource;

import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.bbsuper.nev.beans.vo.user.UserInfo;
import com.bbsuper.nev.dao.mapping.AccountEntityMapper;
import com.bbsuper.nev.service.CityService;

/**
 * 用户缓存服务
 * @author liwei
 * @date: 2018年10月25日 上午11:50:06
 *
 */
@Service
public class UserCacheService {
	
	
	@Resource
	private AccountEntityMapper accountEntityMapper;
	
	@Resource
	private CityService cityService;
	
	@CachePut(key = "#userInfo.id",value = "userInfo")
	public UserInfo putCache(UserInfo userInfo) {
		return userInfo;
	}
	@Cacheable(key = "#id",value = "userInfo")
	public UserInfo queryUserInfoById(Long id) {
		UserInfo entity = accountEntityMapper.queryUserInfoById(id);
		cityService.addCity(entity);
		return entity;
	}
	
	public UserInfo queryUserInfoByAccount(String account) {
		UserInfo entity = accountEntityMapper.queryUserInfoByAccount(account);
		cityService.addCity(entity);
		return entity;
	}
	
	

}
