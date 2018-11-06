package com.bbsuper.nev.controller.system;

import javax.annotation.Resource;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bbsuper.nev.annotation.Slf4j;
import com.bbsuper.nev.beans.dto.paging.PaginationCondition;
import com.bbsuper.nev.beans.dto.paging.PaginationResult;
import com.bbsuper.nev.beans.dto.vehicle_type.EditVehicleTypeDto;
import com.bbsuper.nev.beans.dto.vehicle_type.VehicleTypeCondition;
import com.bbsuper.nev.beans.dto.vehicle_type.VehicleTypeDto;
import com.bbsuper.nev.beans.po.VehicleTypeEntity;
import com.bbsuper.nev.beans.vo.common.ResultData;
import com.bbsuper.nev.beans.vo.vehicle_type.VehicleTypeInfo;
import com.bbsuper.nev.service.VehicleTypeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 车辆类目
 * @author liwei
 * @date: 2018年10月29日 下午2:20:15
 *
 */
@Api(tags="车辆类目")
@RequestMapping("/system/vehicleType")
@RestController
public class VehicleTypeController {
	
	@Resource
	private VehicleTypeService vehicleTypeService;
	
	@PostMapping("/queryList")
	@ApiOperation(value = "查询列表", notes = "按条件查询列表")
	@Slf4j(logError = true)
	public ResultData<PaginationResult<VehicleTypeInfo>> queryList(@RequestBody @Validated PaginationCondition<VehicleTypeCondition> condition){

		return vehicleTypeService.queryListByCondition(condition);
	}
	
	@PostMapping("/disabled")
	@ApiOperation(value = "禁用车辆类目", notes = "禁用车辆类目")
	@Slf4j
	public ResultData<Void> disabled(@ApiParam("车辆类目id") @RequestParam("id") String id){
		return vehicleTypeService.modifyStatus(Long.parseLong(id), VehicleTypeEntity.Status.DISABLED);
	}
	
	@PostMapping("/enabled")
	@ApiOperation(value = "启用车辆类目", notes = "启用车辆类目")
	@Slf4j
	public ResultData<Void> enabled(@ApiParam("车辆类目id") @RequestParam("id") String id){
		return vehicleTypeService.modifyStatus(Long.parseLong(id), VehicleTypeEntity.Status.NORMAL);
	}
	
	@PostMapping("/add")
	@ApiOperation(value = "新增车辆类目", notes = "新增车辆类目")
	@Slf4j
	public ResultData<Void> add(@Validated VehicleTypeDto vehicleTypeDto){
		return vehicleTypeService.add(vehicleTypeDto);
	}
	
	@PostMapping("/edit")
	@ApiOperation(value = "编辑车辆类目", notes = "编辑车辆类目")
	@Slf4j
	public ResultData<Void> edit(@Validated EditVehicleTypeDto editVehicleTypeDto){
		return vehicleTypeService.edit(editVehicleTypeDto);
	}

}
