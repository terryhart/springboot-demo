package com.bbsuper.nev.config;

import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import com.alibaba.fastjson.JSON;
import com.bbsuper.nev.annotation.IgnoreAuthentication;
import com.bbsuper.nev.beans.bo.user.Token;
import com.bbsuper.nev.beans.constant.LoginConstant;
import com.bbsuper.nev.beans.enums.PrivilegeEnum;
import com.bbsuper.nev.beans.enums.result.BaseRetCode;
import com.bbsuper.nev.beans.po.AccountEntity;
import com.bbsuper.nev.beans.po.AccountRoleEntity;
import com.bbsuper.nev.beans.vo.common.ResultData;
import com.bbsuper.nev.beans.vo.user.Privilege;
import com.bbsuper.nev.beans.vo.user.UserInfo;
import com.bbsuper.nev.dao.RedisDao;
import com.bbsuper.nev.service.cache.UserCacheService;
import com.bbsuper.nev.utils.ControllerUtil;

/**
 * 权限拦截器
 * @author liwei
 * @date: 2018年10月24日 下午3:50:22
 *
 */
public class AuthenticationInterceptor implements HandlerInterceptor{
	
	private static Logger logger = LoggerFactory.getLogger(AuthenticationInterceptor.class);
	
	@Resource
	private UserCacheService userCacheService;
	
	@Resource
	private RedisDao redisDao;


	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        //1.获取当前用户信息
        String token = request.getHeader(LoginConstant.TOKEN);
        ResultData<Token> tokenRet = queryToken(token);
        if(tokenRet.success()){
        	//用户信息预处理
        	beforeUserInfo(token,tokenRet.getData());
        }
        Method method = ((HandlerMethod) handler).getMethod();
        //2.判断接口是否需要登录
        if (request.getRequestURI().startsWith("/swagger-resources")) {
        	return true;
        }
        IgnoreAuthentication methodAnnotation = method.getAnnotation(IgnoreAuthentication.class);
        if (methodAnnotation != null
        	&&(tokenRet.success()||!methodAnnotation.needLogin())){
        		return true;
        }
        //3.校验权限
        ResultData<Void> result = checkPrivilege(request.getRequestURI(),tokenRet);
        if(!result.success()){
        	return failResult(request,response,result);
        }
        return true;
	}
	
	private void beforeUserInfo(String token, Token data) {
		//刷新token有效期，保持半小时
    	redisDao.expire(token, 30, TimeUnit.MINUTES);
    	//放入ThreadLocal，当前线程可获取
    	UserInfo userInfo = userCacheService.queryUserInfoById(data.getId());
    	if(userInfo!=null){
    		ControllerUtil.putUser(userInfo);
        	//添加MDC信息，可全局记录用户日志
        	MDC.put("user", builderUserStr(userInfo));
    	}
	}

	private String builderUserStr(UserInfo userInfo) {
		StringBuilder builder = new StringBuilder("[");
		builder.append(userInfo.getRoleName()).append(",")
		.append(userInfo.getName()).append(",")
		.append(userInfo.getAccount()).append("]");
		return builder.toString();
	}

	private ResultData<Token> queryToken(String token) {
		if(StringUtils.isEmpty(token)){
			return ResultData.getInstance(BaseRetCode.NOT_LOGIN);
		}
		String tokens = redisDao.get(token);
		if(StringUtils.isEmpty(tokens)){
			return ResultData.getInstance(BaseRetCode.NOT_LOGIN);
		}
		Token tokenBean = JSON.parseObject(tokens, Token.class);
		return ResultData.getInstance(tokenBean);
	}

	private ResultData<Void> checkPrivilege(String uri, ResultData<Token> token) {
		//1.用户是否登录成功
		if(!token.success()){
        	return  ResultData.getInstance(token);
        }
		//2.账号是否还存在
		UserInfo userInfoCache = ControllerUtil.getCurrentUser();
		if(userInfoCache == null){
			return ResultData.getInstance(BaseRetCode.NOT_USER_EXIST);
		}
		//3.用户的账号密码是否变更
		Token data = token.getData();
		if(!data.getAccount().equals(userInfoCache.getAccount())
				||!data.getPassword().equals(userInfoCache.getPassword())){
			return ResultData.getInstance(BaseRetCode.USER_CHANGE);
		}
		//4.用户账号状态是否正常
		if(userInfoCache.getStatus()!=AccountEntity.Status.NORMAL
				||userInfoCache.getRoleStatus()!=AccountRoleEntity.Status.NORMAL){
			return ResultData.getInstance(BaseRetCode.USER_DISABLED);
		}
		//5.是否有访问权限
		String privilegeCode = userInfoCache.getPrivilegeCode();
		if(StringUtils.isEmpty(privilegeCode)){
			return ResultData.getInstance(BaseRetCode.UNAUTHORIZED);
		}
		String[] codes = privilegeCode.split(",");
		List<Privilege> privilegeByCodes = PrivilegeEnum.getPrivilegeByCodes(codes);
		boolean success = privilegeByCodes.stream().anyMatch(p->{
			return uri.startsWith(p.getUri());
		});
		return success?ResultData.getInstance():ResultData.getInstance(BaseRetCode.UNAUTHORIZED);
	}

	public boolean failResult(HttpServletRequest request,HttpServletResponse response, ResultData<Void> retCode) throws Exception {
		logger.error("AuthenticationInterceptor,uri:{},retCode:{}",request.getRequestURI(),retCode);
		response.setContentType("application/json;charset=UTF-8");
        PrintWriter writer = response.getWriter();
        writer.print(JSON.toJSONString(retCode));
        writer.close();
        response.flushBuffer();
        return false;
    }

}
