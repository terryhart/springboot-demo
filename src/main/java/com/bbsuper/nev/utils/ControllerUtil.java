package com.bbsuper.nev.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.bbsuper.nev.beans.vo.user.UserInfo;
/**
 * Controller工具
 * @author liwei
 * @date: 2018年10月22日 下午3:11:06
 *
 */
public class ControllerUtil {
	
	/**
	 * 当前登录用户
	 */
	private static final ThreadLocal<UserInfo> USERS = new InheritableThreadLocal<>();
	
	public static HttpServletRequest getRequest() {
		RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
		if (requestAttributes != null && requestAttributes instanceof ServletRequestAttributes) {
			return ((ServletRequestAttributes) requestAttributes).getRequest();
		}
		return null;
	}
	
	public static HttpServletResponse getResponse() {
		RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
		if (requestAttributes != null && requestAttributes instanceof ServletRequestAttributes) {
			return ((ServletRequestAttributes) requestAttributes).getResponse();
		}
		return null;
	}
	
	public static UserInfo getCurrentUser() {
		return USERS.get();
	}
	
	public static void putUser(UserInfo user) {
		USERS.set(user);
	}

	public static void removeUser() {
		USERS.remove();
	}

}
