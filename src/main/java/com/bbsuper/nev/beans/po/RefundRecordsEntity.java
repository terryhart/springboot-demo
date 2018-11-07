package com.bbsuper.nev.beans.po;

import java.math.BigDecimal;

import com.bbsuper.nev.beans.po.common.Entity;
/**
 * 退款记录
 * @author liwei
 * @date: 2018年11月1日 下午3:12:48
 *
 */
public class RefundRecordsEntity extends Entity{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5036943491586052821L;

	/**
	 * 客户id
	 */
    private Long customerId;

    /**
     * 退款金额
     */
    private BigDecimal amount;

    /**
     * 退款原因
     */
    private String reason;
    
    /**
     * 购买车辆id
     */
    private Long vehicleId;

    /**
     * 状态
     */
    private Status status;
    
    public enum Status{
    	/**
    	 * 退款待审核
    	 */
    	STAY_REFUND,
    	/**
    	 * 退款
    	 */
    	REFUND
    }

    
    public Long getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(Long vehicleId) {
		this.vehicleId = vehicleId;
	}

	public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}