package com.bbsuper.nev.dao.mapping;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.bbsuper.nev.beans.dto.paging.PaginationCondition;
import com.bbsuper.nev.beans.dto.vehicle.VehicleCondition;
import com.bbsuper.nev.beans.po.VehicleInfoEntity;
import com.bbsuper.nev.beans.vo.vehicle.VehicleInfo;
@Mapper
public interface VehicleInfoEntityMapper {
    int deleteById(Long id);

    int insert(VehicleInfoEntity record);

    VehicleInfoEntity selectById(Long id);

    int updateById(VehicleInfoEntity record);
    
	List<VehicleInfo> queryListByCondition(PaginationCondition<VehicleCondition> queryCondition);

	int queryCountByCondition(PaginationCondition<VehicleCondition> queryCondition);
	
	/**
	 * 批量插入
	 * @param data
	 * @return
	 */
	int insertBatch(List<VehicleInfoEntity> list);
	
	/**
	 * 根据车牌号查询可用车辆信息
	 * @param plateNumbers
	 * @return
	 */
	VehicleInfoEntity queryAvailableByPlateNumbers(String plateNumbers);
	
}