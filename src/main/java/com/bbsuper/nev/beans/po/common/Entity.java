package com.bbsuper.nev.beans.po.common;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.JSON;

/**
 * 表基础信息
 * @author liwei
 * @date: 2018年9月28日 下午1:17:52
 *
 */
public class Entity implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8919436389378773884L;

	/**
	 * 主键id
	 */
	private Long id;
	
	/**
	 * 更新时间
	 */
    private Date updateTime;

    /**
     * 创建时间
     */
    private Date createTime;
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}
	
	

}
