package com.bbsuper.nev.beans.vo.customer;

import com.alibaba.fastjson.JSON;
import com.bbsuper.nev.beans.po.FollowRecordsEntity.Status;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 跟进记录
 * @author liwei
 * @date: 2018年10月31日 上午11:42:26
 *
 */
@ApiModel("跟进记录")
public class FollowRecordsInfo {
	
	@ApiModelProperty("跟进时间")
	private String followTime;

	@ApiModelProperty("销售")
    private String saleName;

	@ApiModelProperty("状态")
    private Status status;

	@ApiModelProperty("关键字")
    private String keyword;

	@ApiModelProperty("跟进备注")
    private String remark;

	public String getFollowTime() {
		return followTime;
	}

	public void setFollowTime(String followTime) {
		this.followTime = followTime;
	}

	public String getSaleName() {
		return saleName;
	}

	public void setSaleName(String saleName) {
		this.saleName = saleName;
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

	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}

}
