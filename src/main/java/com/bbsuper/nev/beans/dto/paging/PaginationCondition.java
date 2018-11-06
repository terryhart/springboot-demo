package com.bbsuper.nev.beans.dto.paging;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 分页查询的条件
 * @author liwei
 * @date: 2018年10月12日 下午3:09:24
 *
 */
@ApiModel(description = "分页查询的条件")
public class PaginationCondition<T> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8142615576876381404L;
	
	@NotNull
	@ApiModelProperty("分页信息")
	private PageInfo pageInfo;
	
	@ApiModelProperty("查询条件")
	private T condition;

	public PageInfo getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(PageInfo pageInfo) {
		this.pageInfo = pageInfo;
	}

	public T getCondition() {
		return condition;
	}

	public void setCondition(T condition) {
		this.condition = condition;
	}

	public PaginationCondition(PageInfo pageInfo, T condition) {
		super();
		this.pageInfo = pageInfo;
		this.condition = condition;
	}
	
	

	public PaginationCondition(T condition) {
		super();
		this.condition = condition;
	}

	public PaginationCondition() {
		super();
	
	}
	
	
	
	
	

}
