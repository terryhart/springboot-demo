package com.bbsuper.nev.beans.dto.vehicle;

import java.math.BigDecimal;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.alibaba.fastjson.JSON;
import com.bbsuper.nev.beans.po.VehicleInfoEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * 车辆信息
 * @author liwei
 * @date: 2018年11月1日 下午2:56:41
 *
 */
@ApiModel(description = "车辆信息")
public class VehicleDto {
	
	@NotNull
	@ApiModelProperty("车辆类型id")
	private Long vehicleTypeId;
	
	@NotEmpty
	@ApiModelProperty("车牌号")
	private String plateNumbers;
	
	@NotEmpty
	@ApiModelProperty("售价")
	private String price;

	public Long getVehicleTypeId() {
		return vehicleTypeId;
	}

	public void setVehicleTypeId(Long vehicleTypeId) {
		this.vehicleTypeId = vehicleTypeId;
	}

	public String getPlateNumbers() {
		return plateNumbers;
	}

	public void setPlateNumbers(String plateNumbers) {
		this.plateNumbers = plateNumbers;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}
	
	public VehicleInfoEntity toAddEntity(){
		VehicleInfoEntity entity = new VehicleInfoEntity();
		entity.setPlateNumbers(getPlateNumbers());
		entity.setPrice(new BigDecimal(getPrice()).setScale(2, BigDecimal.ROUND_HALF_UP));
		entity.setVehicleTypeId(getVehicleTypeId());
		entity.setStatus(VehicleInfoEntity.Status.NORMAL);
		return entity;
	}
	
	

}
