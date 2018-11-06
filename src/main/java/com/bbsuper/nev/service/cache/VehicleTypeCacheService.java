package com.bbsuper.nev.service.cache;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.bbsuper.nev.beans.po.VehicleTypeEntity;
import com.bbsuper.nev.dao.mapping.VehicleTypeEntityMapper;

/**
 * 车型缓存服务
 * @author liwei
 * @date: 2018年11月1日 上午11:26:14
 *
 */
@Service
public class VehicleTypeCacheService {
	
	@Resource
	private VehicleTypeEntityMapper  vehicleTypeEntityMapper;
	
	@Cacheable(key="methodName",value="vehicleType")
	public Map<Long,VehicleTypeEntity> queryAll() {
		List<VehicleTypeEntity> queryAll = vehicleTypeEntityMapper.queryAll();
		return queryAll.stream().collect(Collectors.toMap(
			u->{
				return u.getId();
			}, 
			u->{
				return u;
			}));
	}

}
