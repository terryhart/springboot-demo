package com.bbsuper.nev.beans.dto.vehicle;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.bbsuper.nev.beans.po.VehicleInfoEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 车辆查询条件
 * @author liwei
 * @date: 2018年10月30日 上午9:57:55
 *
 */
@ApiModel(description = "车辆查询条件")
public class VehicleCondition {
	
	@ApiModelProperty("车辆类型id")
	private Long vehicleTypeId;
	
	@ApiModelProperty("车牌号")
	private String plateNumbers;
	
	@ApiModelProperty(value= "状态",hidden = true)
	private List<VehicleInfoEntity.Status> statuss;
	
	@ApiModelProperty(value= "状态")
	private VehicleInfoEntity.Status status;
	
	@ApiModelProperty("添加日期起，格式：yyyy-MM-dd")
	private String addStartDate;
	
	@ApiModelProperty("添加日期止，格式：yyyy-MM-dd")
	private String addEndDate;
	
	@ApiModelProperty("销售日期起，格式：yyyy-MM-dd")
	private String soldStartDate;
	
	@ApiModelProperty("销售日期止，格式：yyyy-MM-dd")
	private String soldEndDate;
	
	@ApiModelProperty("退回日期起，格式：yyyy-MM-dd")
	private String backStartDate;
	
	@ApiModelProperty("退回日期止，格式：yyyy-MM-dd")
	private String backEndDate;
	
	

	public VehicleInfoEntity.Status getStatus() {
		return status;
	}

	public void setStatus(VehicleInfoEntity.Status status) {
		this.status = status;
	}

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

	public String getAddStartDate() {
		return addStartDate;
	}

	public void setAddStartDate(String addStartDate) {
		this.addStartDate = addStartDate;
	}

	public String getAddEndDate() {
		return addEndDate;
	}

	public void setAddEndDate(String addEndDate) {
		this.addEndDate = addEndDate;
	}

	public String getSoldStartDate() {
		return soldStartDate;
	}

	public void setSoldStartDate(String soldStartDate) {
		this.soldStartDate = soldStartDate;
	}

	public String getSoldEndDate() {
		return soldEndDate;
	}

	public void setSoldEndDate(String soldEndDate) {
		this.soldEndDate = soldEndDate;
	}

	public String getBackStartDate() {
		return backStartDate;
	}

	public void setBackStartDate(String backStartDate) {
		this.backStartDate = backStartDate;
	}

	public String getBackEndDate() {
		return backEndDate;
	}

	public void setBackEndDate(String backEndDate) {
		this.backEndDate = backEndDate;
	}

	public List<VehicleInfoEntity.Status> getStatuss() {
		return statuss;
	}

	public void setStatuss(List<VehicleInfoEntity.Status> statuss) {
		this.statuss = statuss;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}
	
	

}
