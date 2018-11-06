package com.bbsuper.nev.beans.po;

import com.bbsuper.nev.beans.po.common.Entity;
/**
 * 跟进记录
 * @author liwei
 * @date: 2018年11月1日 下午3:11:08
 *
 */
public class FollowRecordsEntity extends Entity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7868716740995262562L;

	/**
	 * 客户id
	 */
    private Long customerId;

    /**
     * 销售id
     */
    private Long salesmanId;

    /**
     * 跟进状态
     */
    private Status status;

    /**
     * 关键字
     */
    private String keyword;

    /**
     * 跟进备注
     */
    private String remark;
    
    
    public enum Status{
    	
    	FOLLOW_1(2,"售前跟进"),
    	FOLLOW_2(3,"试车体验"),
    	FOLLOW_3(4,"意向跟进"),
    	FOLLOW_4(5,"提交资料"),
    	FOLLOW_5(6,"交款提车"),
    	FOLLOW_6(7,"放款抵押"),
    	SOLD(8,"业务完成,已售客户"),
    	DISCARD(9,"放弃客户"),
    	AFTER_SALE(10,"售后服务");
    	
    	private int code;
    	
    	private String desc;

		public int getCode() {
			return code;
		}
		public String getDesc() {
			return desc;
		}
		
		private Status(int code, String desc) {
			this.code = code;
			this.desc = desc;
		}
		public static Status getByCode(int code){
			Status[] values = values();
			for(Status status : values){
				if(status.code == code){
					return status;
				}
			}
			return AFTER_SALE;
		}
		
    }


    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getSalesmanId() {
        return salesmanId;
    }

    public void setSalesmanId(Long salesmanId) {
        this.salesmanId = salesmanId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}