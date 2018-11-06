package com.bbsuper.nev.service.cache;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.bbsuper.nev.beans.po.CityEntity;
import com.bbsuper.nev.dao.mapping.CityEntityMapper;

/**
 * 城市缓存服务
 * @author liwei
 * @date: 2018年11月1日 上午10:07:09
 *
 */
@Service
public class CityCacheService {
	
	@Resource
	private CityEntityMapper cityEntityMapper;
	
	@Cacheable(key="methodName",value="city")
	public Map<Long,CityEntity> queryAll() {
		List<CityEntity> queryAll = cityEntityMapper.queryAll();
		return queryAll.stream().collect(Collectors.toMap(
			u->{
				return u.getId();
			}, 
			u->{
				return u;
			}));
	}
	


}
