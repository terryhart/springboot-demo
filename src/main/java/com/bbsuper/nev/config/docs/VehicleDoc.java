package com.bbsuper.nev.config.docs;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.google.common.base.Predicate;
/**
 * 
 * @author liwei
 * @date: 2018年10月26日 下午6:01:21
 *
 */
@Configuration
@Profile("dev")
public class VehicleDoc extends AbstractApiDoc{

	@Override
	protected String setGroupName() {
		return "车辆管理";
	}

	@Override
	protected Predicate<String> pathGroup() {
		return u->{
			return u.startsWith("/vehicle");
		};
	}

}
