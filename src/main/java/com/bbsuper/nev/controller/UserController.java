package com.bbsuper.nev.controller;


import javax.annotation.Resource;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bbsuper.nev.annotation.IgnoreAuthentication;
import com.bbsuper.nev.annotation.Slf4j;
import com.bbsuper.nev.beans.dto.user.ChangePasswordDto;
import com.bbsuper.nev.beans.dto.user.LoginRequest;
import com.bbsuper.nev.beans.enums.result.BaseRetCode;
import com.bbsuper.nev.beans.vo.common.ResultData;
import com.bbsuper.nev.beans.vo.user.UserInfo;
import com.bbsuper.nev.service.UserService;
import com.bbsuper.nev.utils.ControllerUtil;
import com.bbsuper.nev.utils.EncryptionUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 账号登录查询等服务
 * 不限制访问权限
 * @author liwei
 * @date: 2018年10月25日 上午9:04:08
 *
 */
@Api(tags="用户相关服务")
@RequestMapping("/user")
@RestController
public class UserController {
	
	@Resource
	private UserService userService;
	
	@PostMapping("/generateDeviceId")
	@ApiOperation(value = "生成设备ID", notes = "打开登录页面时调用，生成设备ID，获取验证码和登录时作为请求参数")
	@Slf4j
	@IgnoreAuthentication
	public ResultData<String> generateDeviceId(){
		return ResultData.getInstance(EncryptionUtils.random(16));
	}
	
	@GetMapping("/generateImageCode")
	@ApiOperation(value = "生成图片验证码", notes = "生成图片验证码")
	@Slf4j
	@IgnoreAuthentication
	public void generateImageCode(@ApiParam(value = "设备ID") @RequestParam("deviceId")String deviceId) throws Exception{
		userService.generateImageCode(deviceId);
	}
	
	@PostMapping("/login")
	@ApiOperation(value = "登录", notes = "登录成功后返回token，后续的请求都需要在请求头带上token，否则判定为没有登录")
	@Slf4j
	@IgnoreAuthentication
	public ResultData<String> login(@Validated LoginRequest loginRequest){
		return userService.login(loginRequest);
	}
	
	@PostMapping("/exit")
	@ApiOperation(value = "退出", notes = "退出登录")
	@Slf4j
	@IgnoreAuthentication(needLogin = true)
	public ResultData<Void> exit(){
		return userService.exit();
	}
	
	@PostMapping("/changePassword")
	@ApiOperation(value = "修改密码", notes = "修改密码")
	@Slf4j
	@IgnoreAuthentication(needLogin = true)
	public ResultData<Void> changePassword(@Validated ChangePasswordDto pwd){
		return userService.changePassword(ControllerUtil.getCurrentUser(),pwd);
	}
	
	@PostMapping("/query")
	@ApiOperation(value = "获取当前用户信息", notes = "获取当前用户信息,包含权限信息")
	@Slf4j
	@IgnoreAuthentication(needLogin = true)
	public ResultData<UserInfo> query(){
		UserInfo currentUser = ControllerUtil.getCurrentUser();
		if(currentUser==null){
			return ResultData.getInstance(BaseRetCode.NOT_USER_EXIST);
		}
		currentUser.setPassword(null);
		return ResultData.getInstance(currentUser);
	}


}
