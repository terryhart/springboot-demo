package com.bbsuper.nev.beans.vo.customer;

import java.math.BigDecimal;

import com.bbsuper.nev.beans.po.CustomerEntity.BuyWay;
import com.bbsuper.nev.beans.po.CustomerEntity.Source;
import com.bbsuper.nev.beans.po.CustomerEntity.Status;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * 客户信息
 * @author liwei
 * @date: 2018年10月30日 下午3:17:48
 *
 */
@ApiModel("客户信息")
public class CustomerInfo {
	
	@ApiModelProperty("id")
	private Long id;
	
	@ApiModelProperty("姓名")
 	private String name;

	@ApiModelProperty("手机号")
    private String mobile;

	@ApiModelProperty("来源")
    private Source source;

	@ApiModelProperty("城市ID")
    private Long cityId;
	
	@ApiModelProperty("地域")
    private String cityName;

	@ApiModelProperty("详细地址")
    private String address;

    @ApiModelProperty("留言")
    private String message;
    
	@ApiModelProperty("申请时间，格式：yyyy-MM-dd HH:mm:ss")
	private String applyTime;

	@ApiModelProperty("状态")
    private Status status;

    @ApiModelProperty("微信号")
    private String wechat;
    
    @ApiModelProperty("意向车型id")
    private Long intentionVehicleTypeId;
    
    @ApiModelProperty("意向车型")
    private String intentionVehicleType;
    
    @ApiModelProperty("关键词")
    private String keyword;

    @ApiModelProperty("销售id")
    private Long salesmanId;
    
    @ApiModelProperty("销售姓名")
    private String salesmanName;
    
	@ApiModelProperty("最近跟进时间，格式：yyyy-MM-dd HH:mm:ss")
	private String followTime;

	@ApiModelProperty("用户备注")
    private String remark;
	
    @ApiModelProperty("车辆id")
    private Long vehicleId;
    
    @ApiModelProperty("购买车型id")
    private Long vehicleTypeId;
    
    @ApiModelProperty("购买车型")
    private String vehicleType;
	
	@ApiModelProperty("车牌号")
	private String plateNumbers;
	
    @ApiModelProperty("成交价")
    private BigDecimal closingCost;

	@ApiModelProperty("放弃原因")
    private String discardReason;

    @ApiModelProperty("放弃日期，格式yyyy-MM-dd")
    private String discardTime;

    @ApiModelProperty("购买方式")
    private BuyWay buyWay;

    @ApiModelProperty("保险购买日期，格式yyyy-MM-dd")
    private String insureDate;

    @ApiModelProperty("合同链接，多个用逗号隔开")
    private String contractLink;
    
    

	public Long getVehicleTypeId() {
		return vehicleTypeId;
	}

	public void setVehicleTypeId(Long vehicleTypeId) {
		this.vehicleTypeId = vehicleTypeId;
	}

	public String getIntentionVehicleType() {
		return intentionVehicleType;
	}

	public void setIntentionVehicleType(String intentionVehicleType) {
		this.intentionVehicleType = intentionVehicleType;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public Source getSource() {
		return source;
	}

	public void setSource(Source source) {
		this.source = source;
	}

	public Long getCityId() {
		return cityId;
	}

	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(String applyTime) {
		this.applyTime = applyTime;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getWechat() {
		return wechat;
	}

	public void setWechat(String wechat) {
		this.wechat = wechat;
	}

	public Long getIntentionVehicleTypeId() {
		return intentionVehicleTypeId;
	}

	public void setIntentionVehicleTypeId(Long intentionVehicleTypeId) {
		this.intentionVehicleTypeId = intentionVehicleTypeId;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public Long getSalesmanId() {
		return salesmanId;
	}

	public void setSalesmanId(Long salesmanId) {
		this.salesmanId = salesmanId;
	}

	public String getSalesmanName() {
		return salesmanName;
	}

	public void setSalesmanName(String salesmanName) {
		this.salesmanName = salesmanName;
	}

	public String getFollowTime() {
		return followTime;
	}

	public void setFollowTime(String followTime) {
		this.followTime = followTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Long getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(Long vehicleId) {
		this.vehicleId = vehicleId;
	}

	public String getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

	public String getPlateNumbers() {
		return plateNumbers;
	}

	public void setPlateNumbers(String plateNumbers) {
		this.plateNumbers = plateNumbers;
	}

	public BigDecimal getClosingCost() {
		return closingCost;
	}

	public void setClosingCost(BigDecimal closingCost) {
		this.closingCost = closingCost;
	}

	public String getDiscardReason() {
		return discardReason;
	}

	public void setDiscardReason(String discardReason) {
		this.discardReason = discardReason;
	}

	public String getDiscardTime() {
		return discardTime;
	}

	public void setDiscardTime(String discardTime) {
		this.discardTime = discardTime;
	}

	public BuyWay getBuyWay() {
		return buyWay;
	}

	public void setBuyWay(BuyWay buyWay) {
		this.buyWay = buyWay;
	}

	public String getInsureDate() {
		return insureDate;
	}

	public void setInsureDate(String insureDate) {
		this.insureDate = insureDate;
	}

	public String getContractLink() {
		return contractLink;
	}

	public void setContractLink(String contractLink) {
		this.contractLink = contractLink;
	}
    
    

}
