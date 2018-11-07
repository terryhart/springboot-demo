package com.bbsuper.nev.dao.mapping;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.bbsuper.nev.beans.dto.paging.PaginationCondition;
import com.bbsuper.nev.beans.dto.user.AccountCondition;
import com.bbsuper.nev.beans.po.AccountEntity;
import com.bbsuper.nev.beans.vo.user.UserInfo;
/**
 * 
 * @author liwei
 * @date: 2018年11月7日 上午10:42:11
 *
 */
@Mapper
public interface AccountEntityMapper {
	/**
	 * 删除
	 * @param id
	 * @return
	 */
    int deleteById(Long id);
    
    /**
     * 插入
     * @param record
     * @return
     */
    int insert(AccountEntity record);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    AccountEntity selectById(Long id);

    /**
     * 根据id更新
     * @param record
     * @return
     */
    int updateById(AccountEntity record);

    /**
     * 根据用户名查询用户详情
     * @param account
     * @return
     */
	UserInfo queryUserInfoByAccount(String account);

	/**
	 * 根据id查询用户详情
	 * @param id
	 * @return
	 */
	UserInfo queryUserInfoById(Long id);
	
	/**
	 * 根据条件查询用户列表
	 * @param queryCondition
	 * @return
	 */
	List<UserInfo> queryListByCondition(PaginationCondition<AccountCondition> queryCondition);

	/**
	 * 根据条件查询用户数量
	 * @param queryCondition
	 * @return
	 */
	int queryCountByCondition(PaginationCondition<AccountCondition> queryCondition);

	
}