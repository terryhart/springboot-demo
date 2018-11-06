package com.bbsuper.nev.service;


import java.util.List;

import com.bbsuper.nev.beans.dto.paging.PaginationCondition;
import com.bbsuper.nev.beans.dto.paging.PaginationResult;
import com.bbsuper.nev.beans.dto.user.AccountCondition;
import com.bbsuper.nev.beans.dto.user.AccountDto;
import com.bbsuper.nev.beans.dto.user.EditAccountDto;
import com.bbsuper.nev.beans.po.AccountEntity.Status;
import com.bbsuper.nev.beans.vo.common.ResultData;
import com.bbsuper.nev.beans.vo.user.UserInfo;

/**
 * 相关服务
 * @author liwei
 * @date: 2018年10月29日 上午11:22:08
 *
 */
public interface AccountService {

	/**
	 * 查询账号列表
	 * @param condition
	 * @return
	 */
	ResultData<PaginationResult<UserInfo>> queryListByCondition(PaginationCondition<AccountCondition> condition);
	/**
	 * 修改账号状态
	 * @param parseLong
	 * @param disabled
	 * @return
	 */
	ResultData<Void> modifyStatus(long parseLong, Status disabled);
	/**
	 * 编辑账号
	 * @param editAccountDto
	 * @return
	 */
	ResultData<Void> edit(EditAccountDto editAccountDto);
	/**
	 * 新建账号
	 * @param accountDto
	 * @return
	 */
	ResultData<Void> add(AccountDto accountDto);
	
	/**
	 * 重置密码
	 * @param id
	 * @return
	 */
	ResultData<Void> resetPassword(long id);
	
	
	List<UserInfo> queryListByData(PaginationCondition<AccountCondition> paginationCondition);
	

}
