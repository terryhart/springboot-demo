package com.bbsuper.nev.service;

import com.bbsuper.nev.beans.dto.user.ChangePasswordDto;
import com.bbsuper.nev.beans.dto.user.LoginRequest;
import com.bbsuper.nev.beans.vo.common.ResultData;
import com.bbsuper.nev.beans.vo.user.UserInfo;

/**
 * 用户相关服务
 * @author liwei
 * @date: 2018年10月25日 上午9:37:37
 *
 */
public interface UserService {
	
	/**
	 * 生成图片验证码
	 * @param deviceId
	 */
	void generateImageCode(String deviceId) throws Exception;
	/**
	 * 登录，返回token
	 * @param loginRequest
	 * @return
	 */
	ResultData<String> login(LoginRequest loginRequest);
	
	/**
	 * 退出登录
	 * @return
	 */
	ResultData<Void> exit();
	
	/**
	 * 修改密码
	 * @param pwd
	 * @return
	 */
	ResultData<Void> changePassword(UserInfo currentUser,ChangePasswordDto pwd);


}
