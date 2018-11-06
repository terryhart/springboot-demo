package com.bbsuper.nev.beans.dto.customer;

import javax.validation.constraints.NotEmpty;

import com.alibaba.fastjson.JSON;
import com.bbsuper.nev.beans.po.CustomerEntity;
import com.bbsuper.nev.beans.po.CustomerEntity.Source;
import com.bbsuper.nev.beans.po.CustomerEntity.Status;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * 客户
 * @author liwei
 * @date: 2018年10月31日 上午10:30:08
 *
 */
@ApiModel(description = "客户")
public class CustomerDto {
	
	@NotEmpty
	@ApiModelProperty("姓名")
    private String name;

	@NotEmpty
	@ApiModelProperty("手机号")
    private String mobile;
	
    @ApiModelProperty("微信号")
    private String wechat;

	@ApiModelProperty("城市ID")
    private Long cityId;

	@ApiModelProperty("详细地址")
    private String address;
	
	@ApiModelProperty("用户备注")
    private String remark;

	@ApiModelProperty("意向车型id")
	private Long intentionVehicleTypeId;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getWechat() {
		return wechat;
	}

	public void setWechat(String wechat) {
		this.wechat = wechat;
	}

	public Long getCityId() {
		return cityId;
	}

	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	

	public Long getIntentionVehicleTypeId() {
		return intentionVehicleTypeId;
	}

	public void setIntentionVehicleTypeId(Long intentionVehicleTypeId) {
		this.intentionVehicleTypeId = intentionVehicleTypeId;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}

	public CustomerEntity toAddEntity() {
		CustomerEntity entity = new CustomerEntity();
		entity.setName(name);
		entity.setMobile(mobile);
		entity.setWechat(wechat);
		entity.setCityId(cityId==null?0L:cityId);
		entity.setAddress(address);
		entity.setRemark(remark);
		entity.setVehicleTypeId(intentionVehicleTypeId);
		entity.setSource(Source.SEO);
		entity.setStatus(Status.FOLLOW_1);
		return entity;
	}
	
	



}
