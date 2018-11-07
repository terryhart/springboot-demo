package com.bbsuper.nev;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableAsync;


import springfox.documentation.swagger2.annotations.EnableSwagger2;
/**
 * 
 * @author liwei
 * @date: 2018年11月7日 上午10:41:44
 *
 */

@SpringBootApplication
@EnableAsync
@EnableSwagger2
@PropertySource({ "classpath:redis.properties", "classpath:ftp.properties", "classpath:mysql.properties",
		"classpath:threadPool.properties" })
public class FrameworkApplication {

	public static void main(String[] args) {
		SpringApplication.run(FrameworkApplication.class, args);
	}
}
