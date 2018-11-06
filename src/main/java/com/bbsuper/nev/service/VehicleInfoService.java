package com.bbsuper.nev.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.bbsuper.nev.beans.dto.customer.ChangeVehicleDto;
import com.bbsuper.nev.beans.dto.customer.FollowCustomerDto;
import com.bbsuper.nev.beans.dto.paging.PaginationCondition;
import com.bbsuper.nev.beans.dto.paging.PaginationResult;
import com.bbsuper.nev.beans.dto.vehicle.EditVehicleDto;
import com.bbsuper.nev.beans.dto.vehicle.VehicleCondition;
import com.bbsuper.nev.beans.dto.vehicle.VehicleDto;
import com.bbsuper.nev.beans.po.VehicleInfoEntity.Status;
import com.bbsuper.nev.beans.vo.common.ResultData;
import com.bbsuper.nev.beans.vo.vehicle.VehicleInfo;

/**
 * 车辆信息
 * @author liwei
 * @date: 2018年10月30日 上午10:43:19
 *
 */
public interface VehicleInfoService {
	/**
	 * 查询车辆列表
	 * @param condition
	 * @return
	 */
	ResultData<PaginationResult<VehicleInfo>> queryList(PaginationCondition<VehicleCondition> condition,List<Status> status);

	/**
	 * 添加车辆
	 * @param vehicleDto
	 * @return
	 */
	ResultData<Void> add(VehicleDto vehicleDto);
	
	/**
	 * 编辑车辆
	 * @param editVehicleDto
	 * @return
	 */
	ResultData<Void> edit(EditVehicleDto editVehicleDto);
	
	/**
	 * 修改车辆状态
	 * @param id
	 * @param delete
	 * @return
	 */
	ResultData<Void> modifyStatus(long id, Status status);

	/**
	 * 导入车辆
	 * @param file
	 * @return
	 */
	ResultData<Void> importVehicleList(MultipartFile file);
	
	/**
	 * 客户跟进，绑定车辆信息
	 * @param followCustomerDto
	 * @return
	 */
	ResultData<Long> lockVehicle(FollowCustomerDto followCustomerDto);
	
	
	/**
	 * 车牌号是否可用
	 * @param plateNumbers
	 * @return
	 */
	ResultData<Void> existVehicle(String plateNumbers);
	/**
	 * 更换车辆，锁定车辆
	 * @param changeVehicleDto
	 * @return
	 */
	ResultData<Void> lockVehicle(ChangeVehicleDto changeVehicleDto);

	/**
	 * 退回车辆
	 * @param vehicleId
	 * @param string
	 */
	void back(Long vehicleId, String reason);

}
