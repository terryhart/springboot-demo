package com.bbsuper.nev.dao.mapping;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import com.bbsuper.nev.beans.dto.paging.PaginationCondition;
import com.bbsuper.nev.beans.dto.role.RoleCondition;
import com.bbsuper.nev.beans.po.AccountRoleEntity;
/**
 * 
 * @author liwei
 * @date: 2018年11月7日 上午11:19:21
 *
 */
@Mapper
public interface AccountRoleEntityMapper {
	
    int deleteById(Long id);

    int insert(AccountRoleEntity record);

    AccountRoleEntity selectById(Long id);

    int updateById(AccountRoleEntity record);
	
	List<AccountRoleEntity> queryListByCondition(PaginationCondition<RoleCondition> queryCondition);

	int queryCountByCondition(PaginationCondition<RoleCondition> queryCondition);
}