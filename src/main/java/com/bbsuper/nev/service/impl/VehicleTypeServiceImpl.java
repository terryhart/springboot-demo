package com.bbsuper.nev.service.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import com.bbsuper.nev.beans.dto.paging.PaginationCondition;
import com.bbsuper.nev.beans.dto.paging.PaginationResult;
import com.bbsuper.nev.beans.dto.vehicle_type.EditVehicleTypeDto;
import com.bbsuper.nev.beans.dto.vehicle_type.VehicleTypeCondition;
import com.bbsuper.nev.beans.dto.vehicle_type.VehicleTypeDto;
import com.bbsuper.nev.beans.enums.result.BaseRetCode;
import com.bbsuper.nev.beans.po.VehicleTypeEntity;
import com.bbsuper.nev.beans.po.VehicleTypeEntity.Status;
import com.bbsuper.nev.beans.vo.common.ResultData;
import com.bbsuper.nev.beans.vo.customer.CustomerInfo;
import com.bbsuper.nev.beans.vo.finance.ReceiptInfo;
import com.bbsuper.nev.beans.vo.finance.RefundInfo;
import com.bbsuper.nev.beans.vo.vehicle.VehicleInfo;
import com.bbsuper.nev.beans.vo.vehicle_type.VehicleTypeInfo;
import com.bbsuper.nev.dao.mapping.VehicleTypeEntityMapper;
import com.bbsuper.nev.service.VehicleTypeService;
import com.bbsuper.nev.service.cache.VehicleTypeCacheService;
import com.bbsuper.nev.service.handler.ParamCheckService;
import com.google.common.collect.Lists;

/**
 * 车辆类目
 * @author liwei
 * @date: 2018年10月29日 下午2:25:32
 *
 */
@Service
public class VehicleTypeServiceImpl implements VehicleTypeService{
	
	@Resource
	private VehicleTypeEntityMapper vehicleTypeEntityMapper;
	
	@Resource
	private ParamCheckService paramCheckService;
	
	@Resource
	private VehicleTypeCacheService vehicleTypeCacheService;

	@Override
	public ResultData<PaginationResult<VehicleTypeInfo>> queryListByCondition(PaginationCondition<VehicleTypeCondition> condition) {
		List<VehicleTypeEntity> list = vehicleTypeEntityMapper.queryListByCondition(condition);
		int count = vehicleTypeEntityMapper.queryCountByCondition(condition);
		List<VehicleTypeInfo> result = list.stream().map(r->{
			return r.toInfo();
		}).collect(Collectors.toList());
		PaginationResult<VehicleTypeInfo> instance = PaginationResult.getInstance(result, condition.getPageInfo(), count);
		return ResultData.getInstance(instance);
	}

	@Override
	@CacheEvict(value = "vehicleType",allEntries = true)
	public ResultData<Void> modifyStatus(long id, Status status) {
		VehicleTypeEntity entity = new VehicleTypeEntity();
		entity.setId(id);
		entity.setStatus(status);
		int modify = vehicleTypeEntityMapper.updateById(entity);
		return modify==1?ResultData.getInstance():ResultData.getInstance(BaseRetCode.FAIL);
	}

	@Override
	@CacheEvict(value = "vehicleType",allEntries = true)
	public ResultData<Void> add(VehicleTypeDto vehicleTypeDto) {
		int insert = vehicleTypeEntityMapper.insert(vehicleTypeDto.toAddEntity());
		return insert==1?ResultData.getInstance():ResultData.getInstance(BaseRetCode.FAIL);
	}

	@Override
	@CacheEvict(value = "vehicleType",allEntries = true)
	public ResultData<Void> edit(EditVehicleTypeDto editVehicleTypeDto) {
		int insert = vehicleTypeEntityMapper.updateById(editVehicleTypeDto.toEntity());
		return insert==1?ResultData.getInstance():ResultData.getInstance(BaseRetCode.FAIL);
	}

	@Override
	public ResultData<List<VehicleTypeInfo>> normalList() {
		List<VehicleTypeInfo> result = Lists.newArrayList();
		Map<Long, VehicleTypeEntity> queryAll = vehicleTypeCacheService.queryAll();
		queryAll.forEach((k,v)->{
			if(v.getStatus()==VehicleTypeEntity.Status.NORMAL){
				result.add(v.toInfo());
			}
		});
		return ResultData.getInstance(result);
	}

	@Override
	public List<VehicleTypeEntity> queryListData(PaginationCondition<VehicleTypeCondition> paginationCondition) {
		return vehicleTypeEntityMapper.queryListByCondition(paginationCondition);
	}

	@Override
	public VehicleTypeEntity selectById(Long id) {
		Map<Long, VehicleTypeEntity> queryAll = vehicleTypeCacheService.queryAll();
		return queryAll.get(id);
	}

	@Override
	public void addType(List<VehicleInfo> list) {
		Map<Long, VehicleTypeEntity> queryAll = vehicleTypeCacheService.queryAll();
		list.forEach(v->{
			VehicleTypeEntity entity = queryAll.get(v.getVehicleTypeId());
			v.setDistributor(entity.getDistributor());
			v.setTrademark(entity.getTrademark());
		});
		
	}

	@Override
	public void addType(CustomerInfo customerInfo) {
		Map<Long, VehicleTypeEntity> map = vehicleTypeCacheService.queryAll();
		if(customerInfo.getIntentionVehicleTypeId()!=null){
			VehicleTypeEntity entity = map.get(customerInfo.getIntentionVehicleTypeId());
			customerInfo.setIntentionVehicleType(entity.getTrademark());
		}
		if(customerInfo.getVehicleTypeId()!=null){
			VehicleTypeEntity entity = map.get(customerInfo.getVehicleTypeId());
			customerInfo.setVehicleType(entity.getTrademark());
		}
	}

	@Override
	public void addTType(List<CustomerInfo> list) {
		Map<Long, VehicleTypeEntity> map = vehicleTypeCacheService.queryAll();
		list.forEach(c->{
			if(c.getIntentionVehicleTypeId()!=null){
				VehicleTypeEntity entity = map.get(c.getIntentionVehicleTypeId());
				c.setIntentionVehicleType(entity.getTrademark());
			}
			if(c.getVehicleTypeId()!=null){
				VehicleTypeEntity entity = map.get(c.getVehicleTypeId());
				c.setVehicleType(entity.getTrademark());
			}
		});
		
	}

	@Override
	public void addTTType(List<ReceiptInfo> list) {
		Map<Long, VehicleTypeEntity> map = vehicleTypeCacheService.queryAll();
		list.forEach(c->{
			if(c.getIntentionVehicleTypeId()!=null){
				VehicleTypeEntity entity = map.get(c.getIntentionVehicleTypeId());
				c.setIntentionVehicleType(entity.getTrademark());
			}
			if(c.getVehicleTypeId()!=null){
				VehicleTypeEntity entity = map.get(c.getVehicleTypeId());
				c.setVehicleType(entity.getTrademark());
			}
		});
		
	}

	@Override
	public void addTTTType(List<RefundInfo> list) {
		Map<Long, VehicleTypeEntity> map = vehicleTypeCacheService.queryAll();
		list.forEach(c->{
			if(c.getIntentionVehicleTypeId()!=null){
				VehicleTypeEntity entity = map.get(c.getIntentionVehicleTypeId());
				c.setIntentionVehicleType(entity.getTrademark());
			}
			if(c.getVehicleTypeId()!=null){
				VehicleTypeEntity entity = map.get(c.getVehicleTypeId());
				c.setVehicleType(entity.getTrademark());
			}
		});
	}


}
