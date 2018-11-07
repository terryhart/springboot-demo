package com.bbsuper.nev.service.impl;

import java.awt.image.BufferedImage;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.bbsuper.nev.beans.bo.user.Token;
import com.bbsuper.nev.beans.constant.LoginConstant;
import com.bbsuper.nev.beans.dto.user.ChangePasswordDto;
import com.bbsuper.nev.beans.dto.user.LoginRequest;
import com.bbsuper.nev.beans.enums.result.BaseRetCode;
import com.bbsuper.nev.beans.enums.result.LoginCodeEnum;
import com.bbsuper.nev.beans.enums.result.SystemCodeEnum;
import com.bbsuper.nev.beans.po.AccountEntity;
import com.bbsuper.nev.beans.po.AccountRoleEntity;
import com.bbsuper.nev.beans.vo.common.ResultData;
import com.bbsuper.nev.beans.vo.user.UserInfo;
import com.bbsuper.nev.dao.RedisDao;
import com.bbsuper.nev.dao.mapping.AccountEntityMapper;
import com.bbsuper.nev.service.UserService;
import com.bbsuper.nev.service.cache.UserCacheService;
import com.bbsuper.nev.service.handler.ParamCheckService;
import com.bbsuper.nev.utils.ControllerUtil;
import com.bbsuper.nev.utils.EncryptionUtils;
import com.bbsuper.nev.utils.ImageCodeUtil;
/**
 * 
 * @author liwei
 * @date: 2018年11月7日 上午10:44:14
 *
 */
@Service
public class UserServiceImpl implements UserService{
	
	@Resource
	private RedisDao redisDao;
	
	@Resource
	private ParamCheckService paramCheckService;
	
	@Resource
	private UserCacheService userCacheService;
	
	@Resource
	private AccountEntityMapper accountEntityMapper;


	@Override
	public void generateImageCode(String deviceId) throws Exception {
		if(StringUtils.isEmpty(deviceId)){
			return;
		}
		HttpServletResponse response = ControllerUtil.getResponse();
		//设置不缓存图片  
		response.setHeader("Pragma", "No-cache");  
		response.setHeader("Cache-Control", "No-cache");  
		response.setDateHeader("Expires", 0) ;  
		//指定生成的相应图片  
		response.setContentType("image/jpeg") ;  
		//生成验证码
		BufferedImage image = new BufferedImage(ImageCodeUtil.WIDEH , ImageCodeUtil.HEIGHT , BufferedImage.TYPE_INT_RGB);  
		String validCode = ImageCodeUtil.imageCode(image);
		redisDao.set(LoginConstant.IMAGE_CODE+deviceId, validCode, 5, TimeUnit.MINUTES);
		ImageIO.write(image, "JPEG", response.getOutputStream());
		response.getOutputStream().flush();
	}


	@Override
	public ResultData<String> login(LoginRequest loginRequest) {
		//校验图形验证码
		String validCode = redisDao.get(LoginConstant.IMAGE_CODE+loginRequest.getDeviceId());
		if(!loginRequest.getImageCode().equalsIgnoreCase(validCode)){
			return ResultData.getInstance(LoginCodeEnum.IMG_CODE_ERR);
		}
		//判断是否被锁定
		String count = redisDao.get(LoginConstant.PWD_FAIL_COUNT+loginRequest.getAccount());
		if(StringUtils.isNotEmpty(count)&&Integer.parseInt(count)>=3){
			return ResultData.getInstance(LoginCodeEnum.LOCKING);
		}
		//判断账号密码
		UserInfo user= userCacheService.queryUserInfoByAccount(loginRequest.getAccount());
		if(user==null){
			return ResultData.getInstance(LoginCodeEnum.NOT_EXIST);
		}
		String pwd = EncryptionUtils.md5(loginRequest.getPassword());
		if(!pwd.equals(user.getPassword())){
			tryLockAccount(loginRequest.getAccount());
			return ResultData.getInstance(LoginCodeEnum.PWD_ERR);
		}
		//判断账号状态
		if(user.getStatus()!=AccountEntity.Status.NORMAL
				||user.getRoleStatus()!=AccountRoleEntity.Status.NORMAL){
			return ResultData.getInstance(LoginCodeEnum.DISABLED);
		}
		//生成token,并保存
		String token = generateAndSaveToken(user);
		//缓存用户数据
		userCacheService.putCache(user);
		return ResultData.getInstance(token);
	}


	private String generateAndSaveToken(UserInfo user) {
		String tokens = LoginConstant.TOKEN + EncryptionUtils.random(20);
		Token token = new Token();
		token.setAccount(user.getAccount());
		token.setPassword(user.getPassword());
		token.setId(user.getId());
		redisDao.set(tokens, JSON.toJSONString(token));
		return tokens;
	}


	private void tryLockAccount(String account) {
		String count = redisDao.get(LoginConstant.PWD_FAIL_COUNT+account);
		if(StringUtils.isEmpty(count)){
			redisDao.set(LoginConstant.PWD_FAIL_COUNT+account, "1",10,TimeUnit.MINUTES);
		}else{
			String c = String.valueOf(Integer.parseInt(count)+1);
			redisDao.set(LoginConstant.PWD_FAIL_COUNT+account, c,10,TimeUnit.MINUTES);
		}
		
	}


	@Override
	public ResultData<Void> exit() {
		String token = ControllerUtil.getRequest().getHeader(LoginConstant.TOKEN);
		return redisDao.delete(token)?ResultData.getInstance():ResultData.getInstance(BaseRetCode.FAIL);
	}


	@Override
	@CacheEvict(key = "#currentUser.id",value = "userInfo")
	public ResultData<Void> changePassword(UserInfo currentUser,ChangePasswordDto pwd) {
		if(currentUser==null){
			return ResultData.getInstance(BaseRetCode.NOT_USER_EXIST);
		}
		if(pwd.getNewPwd().equals(pwd.getOldPwd())){
			return ResultData.getInstance(SystemCodeEnum.PWD_SAME);
		}
		if(!currentUser.getPassword().equals(EncryptionUtils.md5(pwd.getOldPwd()))){
			return ResultData.getInstance(SystemCodeEnum.OLD_PWD_ERR);
		}
		AccountEntity accountEntity = new AccountEntity();
		accountEntity.setId(currentUser.getId());
		accountEntity.setPassword(EncryptionUtils.md5(pwd.getNewPwd()));
		int updateById = accountEntityMapper.updateById(accountEntity);
		return updateById==1?ResultData.getInstance():ResultData.getInstance(BaseRetCode.FAIL);
	}

	

}
