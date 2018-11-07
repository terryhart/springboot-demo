package com.bbsuper.nev.beans.po;

import java.math.BigDecimal;

import com.bbsuper.nev.beans.po.common.Entity;
/**
 * 收款记录
 * @author liwei
 * @date: 2018年11月1日 下午3:11:54
 *
 */
public class ReceiptEntity extends Entity{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4184565574281612118L;

	/**
	 * 客户id
	 */
    private Long customerId;

    /**
     * 收款金额
     */
    private BigDecimal amount;

    /**
     * 收款类型
     */
    private Type type;
    
    /**
     * 购买车辆ID
     */
    private Long vehicleId;
    
    public enum Type{
    	/**
    	 * 定金
    	 */
    	HANDSEL,
    	/**
    	 * 首付款
    	 */
    	DOWN_PAYMENT,
    	/**
    	 * 全款
    	 */
    	TOTAL;
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

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

}