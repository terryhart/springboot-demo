package com.bbsuper.nev.beans.dto.vehicle;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import com.bbsuper.nev.beans.po.VehicleInfoEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * 编辑车辆信息
 * @author liwei
 * @date: 2018年11月1日 下午2:56:26
 *
 */
@ApiModel(description = "编辑车辆信息")
public class EditVehicleDto extends VehicleDto{
	
	@NotNull
	@ApiModelProperty("id")
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public VehicleInfoEntity toEntity(){
		VehicleInfoEntity entity = new VehicleInfoEntity();
		entity.setId(id);
		entity.setPlateNumbers(getPlateNumbers());
		entity.setPrice(new BigDecimal(getPrice()).setScale(2, BigDecimal.ROUND_HALF_UP));
		entity.setVehicleTypeId(getVehicleTypeId());
		return entity;
	}

}
