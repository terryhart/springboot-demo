package com.bbsuper.nev.config.docs;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.bbsuper.nev.beans.constant.LoginConstant;
import com.google.common.base.Predicate;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
/**
 * 接口文档
 * @author liwei
 * @date: 2018年10月24日 上午10:54:39
 *
 */
public abstract class AbstractApiDoc extends Docket{

	AbstractApiDoc(){
        super(DocumentationType.SWAGGER_2);
        this.apiInfo(apiInfo()).groupName(setGroupName())
                .select()
                //.apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .apis(RequestHandlerSelectors.basePackage("com.bbsuper.nev.controller"))
                .paths(pathGroup())
                .build()
                .directModelSubstitute(LocalDateTime.class, Date.class)
                .useDefaultResponseMessages(false)
                .globalOperationParameters(parameters());
    }
	/**
	 * 分组名
	 * @return
	 */
	protected abstract String setGroupName();

	/**
     * api文档分组 
     * 
     * @return
     */
    protected abstract Predicate<String> pathGroup();


    protected ApiInfo apiInfo() {
    	return new ApiInfoBuilder()
    			.license("叭叭速配")
    			.contact(new Contact("叭叭速配", "https://www.babasuper.com/", ""))
    			.licenseUrl("https://www.babasuper.com/")
				.title("新能源车后台管理系统")
				.description("新能源车后台管理系统")
				.termsOfServiceUrl("https://www.babasuper.com/")
				.version("1.0")
				.build();
    }

    protected List<Parameter> parameters() {
        List<Parameter> parameters = new ArrayList<>();
        ParameterBuilder parameterBuilder = new ParameterBuilder()
                .name(LoginConstant.TOKEN)
                .description("权限token,登录之后的请求需要带上")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .required(false);

        parameters.add(parameterBuilder.build());

        return parameters;
    }

}
