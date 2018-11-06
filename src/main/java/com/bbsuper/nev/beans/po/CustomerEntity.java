package com.bbsuper.nev.beans.po;


import java.math.BigDecimal;

import com.bbsuper.nev.beans.po.common.Entity;
/**
 * 客户信息
 * @author liwei
 * @date: 2018年11月1日 下午3:00:50
 *
 */
public class CustomerEntity extends Entity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1874021542809777460L;

	/**
	 * 姓名
	 */
    private String name;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 来源
     */
    private Source source;

    /**
     * 城市id
     */
    private Long cityId;

    /**
     * 详细地址
     */
    private String address;

    /**
     * 留言
     */
    private String message;

    /**
     * 跟进状态
     */
    private Status status;

    /**
     * 微信号
     */
    private String wechat;

    /**
     * 意向车型id
     */
    private Long vehicleTypeId;
    
    /**
     * 成交价格
     */
    private BigDecimal closingCost;

    /**
     * 销售id
     */
    private Long salesmanId;

    /**
     * 用户备注
     */
    private String remark;
    
    /**
     * 关键字
     */
    private String keyword;

    /**
     * 放弃原因
     */
    private String discardReason;

    /**
     * 放弃时间，格式yyyy-MM-dd'
     */
    private String discardTime;

    /**
     * 放弃时的状态
     */
    private Status discardStatus;

    /**
     * 购买方式
     */
    private BuyWay buyWay;

    /**
     * 保险日期，格式yyyy-MM-dd
     */
    private String insureDate;

    /**
     * 合同链接，多个用逗号分隔
     */
    private String contractLink;
    
    public enum Status{
    	
    	INTERESTING(1,"意向"),
    	FOLLOW_1(2,"售前跟进"),
    	FOLLOW_2(3,"试车体验"),
    	FOLLOW_3(4,"意向跟进"),
    	FOLLOW_4(5,"提交资料"),
    	FOLLOW_5(6,"交款提车"),
    	FOLLOW_6(7,"放款抵押"),
    	SOLD(8,"业务完成,已售客户"),
    	DISCARD(9,"放弃客户");
    	
    	
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
		
    }
    
    
    
    public enum BuyWay{
    	
    	INSTALLMENT("分期付款"),TOTAL("全款");
    	
    	private String desc;

		private BuyWay(String desc) {
			this.desc = desc;
		}
		public String getDesc() {
			return desc;
		}
    }
    
    public enum Source{
    	
    	PC("PC"),
    	WAP("WAP"),
    	APP("APP"),
    	SEO("推广");
    	private String desc;

		private Source(String desc) {
			this.desc = desc;
		}
		public String getDesc() {
			return desc;
		}
		public static Source getByDesc(String code){
			Source[] value = values();
			for(Source s:value){
				if(s.getDesc().equals(code)){
					return s;
				}
			}
			return SEO;
		}
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


    public Long getVehicleTypeId() {
		return vehicleTypeId;
	}

	public void setVehicleTypeId(Long vehicleTypeId) {
		this.vehicleTypeId = vehicleTypeId;
	}

	public Long getSalesmanId() {
        return salesmanId;
    }

    public void setSalesmanId(Long salesmanId) {
        this.salesmanId = salesmanId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public Status getDiscardStatus() {
        return discardStatus;
    }

    public void setDiscardStatus(Status discardStatus) {
        this.discardStatus = discardStatus;
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

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public BigDecimal getClosingCost() {
		return closingCost;
	}

	public void setClosingCost(BigDecimal closingCost) {
		this.closingCost = closingCost;
	}
	

}