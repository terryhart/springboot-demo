package com.bbsuper.nev.service;

import java.util.Collection;
import java.util.List;

import com.bbsuper.nev.beans.dto.paging.PaginationCondition;
import com.bbsuper.nev.beans.dto.paging.PaginationResult;
import com.bbsuper.nev.beans.dto.vehicle.type.EditVehicleTypeDto;
import com.bbsuper.nev.beans.dto.vehicle.type.VehicleTypeCondition;
import com.bbsuper.nev.beans.dto.vehicle.type.VehicleTypeDto;
import com.bbsuper.nev.beans.po.VehicleTypeEntity;
import com.bbsuper.nev.beans.po.VehicleTypeEntity.Status;
import com.bbsuper.nev.beans.vo.common.ResultData;
import com.bbsuper.nev.beans.vo.vehicle.type.VehicleTypeInfo;

/**
 * 
 * 车辆类目
 * @author liwei
 * @date: 2018年10月29日 下午2:24:59
 *
 */
public interface VehicleTypeService {
	/**
	 * 查询车辆列表
	 * @param condition
	 * @return
	 */
	ResultData<PaginationResult<VehicleTypeInfo>> queryListByCondition(PaginationCondition<VehicleTypeCondition> condition);
	/**
	 * 修改车辆类目状态
	 * @param parseLong
	 * @param normal
	 * @return
	 */
	ResultData<Void> modifyStatus(long id, Status normal);
	/**
	 * 新增
	 * @param vehicleTypeDto
	 * @return
	 */
	ResultData<Void> add(VehicleTypeDto vehicleTypeDto);
	
	/**
	 * 编辑
	 * @param editVehicleTypeDto
	 * @return
	 */
	ResultData<Void> edit(EditVehicleTypeDto editVehicleTypeDto);
	
	/**
	 * 启用的车型列表
	 * @return
	 */
	ResultData<List<VehicleTypeInfo>> normalList();
	
	
	List<VehicleTypeEntity> queryListData(PaginationCondition<VehicleTypeCondition> paginationCondition);
	
	
	VehicleTypeEntity selectById(Long id);
	
	<T> void addType(Collection<T> list);
	
	

}
