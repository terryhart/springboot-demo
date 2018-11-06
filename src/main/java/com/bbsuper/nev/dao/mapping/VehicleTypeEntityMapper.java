package com.bbsuper.nev.dao.mapping;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.bbsuper.nev.beans.dto.paging.PaginationCondition;
import com.bbsuper.nev.beans.dto.vehicle_type.VehicleTypeCondition;
import com.bbsuper.nev.beans.po.VehicleTypeEntity;
@Mapper
public interface VehicleTypeEntityMapper {

    int insert(VehicleTypeEntity record);

    VehicleTypeEntity selectById(Long id);

    int updateById(VehicleTypeEntity record);
    
	List<VehicleTypeEntity> queryListByCondition(PaginationCondition<VehicleTypeCondition> queryCondition);

	int queryCountByCondition(PaginationCondition<VehicleTypeCondition> queryCondition);
	
	/**
	 * 查询所有车型
	 * @return
	 */
	List<VehicleTypeEntity> queryAll();
}