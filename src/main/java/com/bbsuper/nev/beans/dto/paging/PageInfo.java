package com.bbsuper.nev.beans.dto.paging;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 分页信息
 * @author liwei
 * @date: 2018年10月12日 下午3:09:36
 *
 */
@ApiModel(description = "分页信息")
public class PageInfo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5386008732114294303L;
	
	private static int defaultPage = 1;
	
	private static int defaultSize = 15;

	/**
     * 当前页
     */
	@ApiModelProperty("当前页")
    private int currentPage;
    
    /**
     * 页面大小
     */
	@ApiModelProperty("页面大小")
    private int pageSize;
    
    /**
     * 起始位置
     */
	@ApiModelProperty(value  = "忽略，不需要",hidden = true)
    private int currentPageStartIndex;


	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		if(currentPage<1){
			this.currentPage = defaultPage;
		}else{
			this.currentPage = currentPage;
		}
		
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		if(pageSize<1){
			this.pageSize = defaultSize;
		}else{
			this.pageSize = pageSize;
		}
		
	}

	public int getCurrentPageStartIndex() {
		this.currentPageStartIndex = (currentPage-1)*pageSize;
		return currentPageStartIndex;
	}

	public void setCurrentPageStartIndex(int currentPageStartIndex) {
		this.currentPageStartIndex = currentPageStartIndex;
	}

	public PageInfo(int currentPage, int pageSize) {
		this.currentPage = currentPage<1?defaultPage:currentPage;
		this.pageSize = pageSize<1?defaultSize:pageSize;
	}

	public PageInfo() {
		this.currentPage = defaultPage;
		this.pageSize = defaultSize;
	}
}
