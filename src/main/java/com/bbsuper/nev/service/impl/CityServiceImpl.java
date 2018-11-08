package com.bbsuper.nev.service.impl;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import com.bbsuper.nev.beans.dto.city.CityCondition;
import com.bbsuper.nev.beans.dto.paging.PaginationCondition;
import com.bbsuper.nev.beans.dto.paging.PaginationResult;
import com.bbsuper.nev.beans.enums.result.BaseRetCode;
import com.bbsuper.nev.beans.po.CityEntity;
import com.bbsuper.nev.beans.vo.city.CityInfo;
import com.bbsuper.nev.beans.vo.common.ResultData;
import com.bbsuper.nev.beans.vo.customer.CustomerInfo;
import com.bbsuper.nev.beans.vo.user.UserInfo;
import com.bbsuper.nev.dao.mapping.CityEntityMapper;
import com.bbsuper.nev.service.CityService;
import com.bbsuper.nev.service.cache.CityCacheService;
import com.google.common.collect.Lists;

/**
 * 城市相关服务
 * @author liwei
 * @date: 2018年10月26日 下午3:49:04
 *
 */
@Service
public class CityServiceImpl implements CityService{
	
	@Resource
	private CityEntityMapper cityEntityMapper;
	
	@Resource
	private CityCacheService cityCacheService;

	@Override
	@CacheEvict(value = "city",allEntries = true)
	public ResultData<Void> modifyStatus(long id, CityEntity.Status status) {
		CityEntity cityEntity = new CityEntity();
		cityEntity.setId(id);
		cityEntity.setStatus(status);
		int updateById = cityEntityMapper.updateById(cityEntity);
		return updateById==1?ResultData.getInstance():ResultData.getInstance(BaseRetCode.FAIL);
	}

	/**
	 * 由于城市列表没有新增的功能，只能去数据库加，导致redis不能及时更新
	 * 所以，在列表查询的时候强制更新缓存
	 */
	@Override
	@CacheEvict(value = "city",allEntries = true)
	public ResultData<PaginationResult<CityInfo>> queryList(PaginationCondition<CityCondition> condition) {
		List<CityEntity> list = cityEntityMapper.queryListByCondition(condition);
		int count = cityEntityMapper.queryCountByCondition(condition);
		List<CityInfo> result = list.stream().map(r->{
			return r.toInfo();
		}).collect(Collectors.toList());
		PaginationResult<CityInfo> instance = PaginationResult.getInstance(result, condition.getPageInfo(), count);
		return ResultData.getInstance(instance);
	}

	@Override
	public ResultData<List<CityInfo>> normalList() {
		List<CityInfo> list = Lists.newArrayList();
		Map<Long, CityEntity> queryAll = cityCacheService.queryAll();
		queryAll.forEach((k,v)->{
			if(v.getStatus()==CityEntity.Status.NORMAL){
				list.add(v.toInfo());
			}
		});
		return ResultData.getInstance(list);
	}

	@Override
	public <T> void addCity(Collection<T> list) {
		Map<Long, CityEntity> map = cityCacheService.queryAll();
		list.forEach(bean->{
			if(bean instanceof CustomerInfo){
				addCity((CustomerInfo)bean,map);
			}
			if(bean	instanceof UserInfo){
				addCity((UserInfo)bean,map);
			}
		});
		
	}

	private void addCity(UserInfo u, Map<Long, CityEntity> map) {
		if(u.getCityId()!=null&&u.getCityId().longValue()!=0){
			u.setCityName(map.get(u.getCityId()).getCity());
		}
	}

	private void addCity(CustomerInfo c, Map<Long, CityEntity> map) {
		if(c.getCityId()!=null&&c.getCityId().longValue()!=0){
			c.setCityName(map.get(c.getCityId()).getCity());
		}
	}
	

}
