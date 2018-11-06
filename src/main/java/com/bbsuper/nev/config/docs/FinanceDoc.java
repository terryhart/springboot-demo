package com.bbsuper.nev.config.docs;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.google.common.base.Predicate;
/**
 * 
 * @author liwei
 * @date: 2018年10月26日 下午6:01:07
 *
 */
@Configuration
@Profile("dev")
public class FinanceDoc extends AbstractApiDoc{

	@Override
	protected String setGroupName() {
		return "财务管理";
	}

	@Override
	protected Predicate<String> pathGroup() {
		return u->{
			return u.startsWith("/finance");
		};
	}

}
