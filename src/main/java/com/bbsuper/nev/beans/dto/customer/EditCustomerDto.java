package com.bbsuper.nev.beans.dto.customer;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.StringUtils;

import com.bbsuper.nev.beans.po.CustomerEntity;
import com.bbsuper.nev.beans.po.CustomerEntity.Status;
import com.bbsuper.nev.exception.MissingParameterException;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 编辑客户
 * @author liwei
 * @date: 2018年10月31日 上午10:35:47
 *
 */

@ApiModel(description = "编辑客户")
public class EditCustomerDto extends CustomerDto{
	
	@NotNull
	@ApiModelProperty("id")
	private Long id;
	
	@NotNull
	@ApiModelProperty("是否放弃客户")
	private Boolean discard;
	
	@ApiModelProperty("放弃原因")
	private String discardReason;
	
	@ApiModelProperty("销售id")
	private Long saleId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getDiscard() {
		return discard;
	}

	public void setDiscard(Boolean discard) {
		this.discard = discard;
	}

	public String getDiscardReason() {
		return discardReason;
	}

	public void setDiscardReason(String discardReason) {
		this.discardReason = discardReason;
	}

	public Long getSaleId() {
		return saleId;
	}

	public void setSaleId(Long saleId) {
		this.saleId = saleId;
	}

	public CustomerEntity toEntity() {
		CustomerEntity entity = new CustomerEntity();
		entity.setId(id);
		entity.setName(getName());
		entity.setMobile(getMobile());
		entity.setWechat(getWechat());
		entity.setCityId(getCityId()==null?0L:getCityId());
		entity.setAddress(getAddress());
		entity.setRemark(getRemark());
		entity.setVehicleTypeId(getIntentionVehicleTypeId());
		if(discard){
			entity.setStatus(Status.DISCARD);
			if(StringUtils.isEmpty(discardReason)){
				throw new MissingParameterException("discardReason");
			}
			entity.setDiscardReason(discardReason);
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			entity.setDiscardTime(format.format(new Date()));
		}
		if(saleId!=null){
			entity.setSalesmanId(saleId);
		}
		return entity;
	}
	

}
