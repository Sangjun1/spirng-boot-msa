/**
 * com.igloosec.smartguard.microservices.servers.web.common.config .
 * 패키지 위치.
 */
package com.playd.microservices.servers.web.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * WebConfig .
 * WebConfig를 위한 Class.
 */
@Configuration
@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter {

	/**
	 * addCorsMappings CORS 맵핑 추가 Method.
	 *
	 * @param registry CorsRegistry 변수 추가
	 *                 Mapping 값 추가
	 */
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/api/**")
				.allowedOrigins("*")
				.allowedMethods("POST, GET, PUT, OPTIONS, DELETE")
				.allowedHeaders("x-auth-token, content-type")
				.allowCredentials(false).maxAge(3600);
	}


}
