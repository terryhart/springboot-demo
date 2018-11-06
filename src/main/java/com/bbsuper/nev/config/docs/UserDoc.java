package com.bbsuper.nev.config.docs;


import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.google.common.base.Predicate;

/**
 * 用户相关
 * @author liwei
 * @date: 2018年10月26日 下午5:46:01
 *
 */
@Configuration
@Profile({"dev","prod"})
public class UserDoc extends AbstractApiDoc{

	@Override
	protected String setGroupName() {
		return "用户相关服务";
	}

	@Override
	protected Predicate<String> pathGroup() {
		//return PathSelectors.any();//默认所有
		return u->{
			return u.startsWith("/user");
		};
	}	
	
	
	

}
