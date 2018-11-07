package com.bbsuper.nev.beans.po;

import java.math.BigDecimal;
import java.util.Date;

import com.bbsuper.nev.beans.po.common.Entity;
/**
 * 车辆信息
 * @author liwei
 * @date: 2018年11月1日 下午3:14:57
 *
 */
public class VehicleInfoEntity extends Entity{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2128864017255800518L;

	/**
	 * 车型id
	 */
    private Long vehicleTypeId;

    /**
     * 所属客户id
     */
    private Long customerId;

    /**
     * 车牌号
     */
    private String plateNumbers;

    /**
     * 售价
     */
    private BigDecimal price;

    /**
     * 成交价格
     */
    private BigDecimal closingCost;

    /**
     * 状态
     */
    private Status status;

    /**
     * 成交日期
     */
    private Date dealDate;

    /**
     * 退回日期
     */
    private Date returnDate;

    /**
     * 退回原因
     */
    private String returnReason;
    
    public enum Status{
    	/**
    	 * 正常
    	 */
    	NORMAL,
    	/**
    	 * 已售
    	 */
    	SOLD,
    	/**
    	 * 待退回
    	 */
    	STAY_BACK,
    	/**
    	 * 退回
    	 */
    	BACK,
    	/**
    	 * 删除
    	 */
    	DELETE;
    }


    public Long getVehicleTypeId() {
        return vehicleTypeId;
    }

    public void setVehicleTypeId(Long vehicleTypeId) {
        this.vehicleTypeId = vehicleTypeId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getPlateNumbers() {
        return plateNumbers;
    }

    public void setPlateNumbers(String plateNumbers) {
        this.plateNumbers = plateNumbers;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getClosingCost() {
        return closingCost;
    }

    public void setClosingCost(BigDecimal closingCost) {
        this.closingCost = closingCost;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Date getDealDate() {
		return dealDate;
	}

	public void setDealDate(Date dealDate) {
		this.dealDate = dealDate;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	public String getReturnReason() {
        return returnReason;
    }

    public void setReturnReason(String returnReason) {
        this.returnReason = returnReason;
    }
}