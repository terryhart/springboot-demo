package com.bbsuper.nev.beans.dto.paging;



import java.io.Serializable;
import java.util.Collection;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 分页结果
 * @author liwei
 * @date: 2018年10月12日 下午3:09:07
 *
 */
@ApiModel(description = "分页结果")
public class PaginationResult<T> implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 4049887516280861905L;
	/**
	 * 分页信息
	 */
	@ApiModelProperty("分页信息")
	private PageInfo pageInfo;
    /**
     * 记录数
     */
	@ApiModelProperty("总记录数")
    private Integer records;
    /**
     * 分页数
     */
	@ApiModelProperty("总页数")
    private Integer pageCount;
    /**
     * 数据
     */
	@ApiModelProperty("列表数据")
    private Collection<T> rows;
    
    
    public PageInfo getPageInfo() {
		return pageInfo;
	}


	public void setPageInfo(PageInfo pageInfo) {
		this.pageInfo = pageInfo;
	}


	public Integer getRecords() {
		return records;
	}


	public void setRecords(Integer records) {
		this.records = records;
	}


	public Integer getPageCount() {
		return pageCount;
	}


	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}


	public Collection<T> getRows() {
		return rows;
	}


	public void setRows(Collection<T> rows) {
		this.rows = rows;
	}


	public static <T> PaginationResult<T> getInstance(Collection<T> data,PageInfo pageInfo,int count){
    	PaginationResult<T> paginationResult = new PaginationResult<T>();
        paginationResult.setRecords(count);
        paginationResult.setPageCount(count/pageInfo.getPageSize()+((count%pageInfo.getPageSize()!=0)?1:0));
        paginationResult.setPageInfo(pageInfo);
        paginationResult.setRows(data);
        return paginationResult;
    }

}
