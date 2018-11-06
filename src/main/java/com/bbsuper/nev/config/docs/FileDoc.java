package com.bbsuper.nev.config.docs;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.google.common.base.Predicate;
import com.google.common.collect.Lists;

import springfox.documentation.service.Parameter;

/**
 * 文件服务
 * @author liwei
 * @date: 2018年11月6日 下午2:27:59
 *
 */
@Configuration
@Profile({"dev","prod"})
public class FileDoc extends AbstractApiDoc{

	@Override
	protected String setGroupName() {
		return "文件上传服务";
	}

	@Override
	protected Predicate<String> pathGroup() {
		return u->{
			return u.startsWith("/file");
		};
	}

	@Override
	protected List<Parameter> parameters() {
		return Lists.newArrayList();
	}
	
	

}
