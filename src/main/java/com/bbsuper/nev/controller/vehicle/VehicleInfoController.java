package com.bbsuper.nev.controller.vehicle;

import java.util.List;

import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bbsuper.nev.annotation.IgnoreAuthentication;
import com.bbsuper.nev.annotation.Slf4j;
import com.bbsuper.nev.beans.dto.paging.PaginationCondition;
import com.bbsuper.nev.beans.dto.paging.PaginationResult;
import com.bbsuper.nev.beans.dto.vehicle.EditVehicleDto;
import com.bbsuper.nev.beans.dto.vehicle.VehicleCondition;
import com.bbsuper.nev.beans.dto.vehicle.VehicleDto;
import com.bbsuper.nev.beans.po.VehicleInfoEntity;
import com.bbsuper.nev.beans.vo.common.ResultData;
import com.bbsuper.nev.beans.vo.vehicle.VehicleInfo;
import com.bbsuper.nev.beans.vo.vehicle_type.VehicleTypeInfo;
import com.bbsuper.nev.service.VehicleInfoService;
import com.bbsuper.nev.service.VehicleTypeService;
import com.google.common.collect.Lists;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
/**
 * 车辆管理
 * @author liwei
 * @date: 2018年11月1日 下午3:34:03
 *
 */
@Api(tags="车辆管理")
@RequestMapping("/vehicle")
@RestController
public class VehicleInfoController {
	
	@Resource
	private VehicleInfoService vehicleInfoService;
	
	@Resource
	private VehicleTypeService vehicleTypeService;
	
	@PostMapping("/repertory/queryList")
	@ApiOperation(value = "查询库存车辆列表", notes = "查询库存车辆列表")
	@Slf4j
	public ResultData<PaginationResult<VehicleInfo>> queryRepertoryList(@RequestBody @Validated PaginationCondition<VehicleCondition> condition){

		return vehicleInfoService.queryList(condition,Lists.newArrayList(VehicleInfoEntity.Status.NORMAL));
	}
	
	@PostMapping("/sold/queryList")
	@ApiOperation(value = "查询已售车辆列表", notes = "查询已售车辆列表")
	@Slf4j
	public ResultData<PaginationResult<VehicleInfo>> querySoldList(@RequestBody @Validated PaginationCondition<VehicleCondition> condition){

		return vehicleInfoService.queryList(condition,Lists.newArrayList(VehicleInfoEntity.Status.SOLD));
	}
	
	@PostMapping("/back/queryList")
	@ApiOperation(value = "查询退回车辆列表", notes = "查询退回车辆列表")
	@Slf4j
	public ResultData<PaginationResult<VehicleInfo>> queryBackList(@RequestBody @Validated PaginationCondition<VehicleCondition> condition){

		return vehicleInfoService.queryList(condition,Lists.newArrayList(VehicleInfoEntity.Status.BACK,VehicleInfoEntity.Status.STAY_BACK));
	}
	
	@PostMapping("/vehicleTypeList")
	@ApiOperation(value = "启用的车型列表", notes = "启用的车型列表")
	@Slf4j
	@IgnoreAuthentication(needLogin = true)
	public ResultData<List<VehicleTypeInfo>> vehicleTypeList(){
		
		return vehicleTypeService.normalList();
	}
	
	@PostMapping("/repertory/add")
	@ApiOperation(value = "新增车辆", notes = "新增车辆")
	@Slf4j
	public ResultData<Void> add(@Validated VehicleDto vehicleDto){
		return vehicleInfoService.add(vehicleDto);
	}
	
	@PostMapping("/repertory/import")
	@ApiOperation(value = "导入车辆excel", notes = "导入车辆excel")
	@Slf4j
    public ResultData<Void> batchimport(@ApiParam("文件") @RequestParam("file")MultipartFile file){
       return vehicleInfoService.importVehicleList(file);
    }
	
	@PostMapping("/repertory/edit")
	@ApiOperation(value = "编辑车辆", notes = "编辑车辆")
	@Slf4j
	public ResultData<Void> edit(@Validated EditVehicleDto editVehicleDto){
		return vehicleInfoService.edit(editVehicleDto);
	}
	
	@PostMapping("/repertory/delete")
	@ApiOperation(value = "删除车辆", notes = "删除车辆")
	@Slf4j
	public ResultData<Void> delete(@ApiParam("车辆id") @RequestParam("id") String id){
		return vehicleInfoService.modifyStatus(Long.parseLong(id), VehicleInfoEntity.Status.DELETE);
	}
	
	@PostMapping("/repertory/check")
	@ApiOperation(value = "审核车辆", notes = "审核车辆")
	@Slf4j
	public ResultData<Void> check(@ApiParam("车辆id") @RequestParam("id") String id){
		return vehicleInfoService.modifyStatus(Long.parseLong(id), VehicleInfoEntity.Status.SOLD);
	}
	
	@PostMapping("/back/sendBack")
	@ApiOperation(value = "退还车辆", notes = "退还车辆")
	@Slf4j
	public ResultData<Void> sendBack(@ApiParam("车辆id") @RequestParam("id") String id){
		return vehicleInfoService.modifyStatus(Long.parseLong(id),VehicleInfoEntity.Status.BACK);
	}

}
