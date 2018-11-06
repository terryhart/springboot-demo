package com.bbsuper.nev.dao.mapping;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.bbsuper.nev.beans.dto.paging.PaginationCondition;
import com.bbsuper.nev.beans.dto.user.AccountCondition;
import com.bbsuper.nev.beans.po.AccountEntity;
import com.bbsuper.nev.beans.vo.user.UserInfo;
@Mapper
public interface AccountEntityMapper {
	
    int deleteById(Long id);

    int insert(AccountEntity record);

    AccountEntity selectById(Long id);

    int updateById(AccountEntity record);

	UserInfo queryUserInfoByAccount(String account);

	UserInfo queryUserInfoById(Long id);
	
	List<UserInfo> queryListByCondition(PaginationCondition<AccountCondition> queryCondition);

	int queryCountByCondition(PaginationCondition<AccountCondition> queryCondition);

	
}