/**
 * WebService package.
 *
 */
package com.igloosec.smartguard.microservices.servers.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.client.RestTemplate;

/**
 * WebService module class MicroService WebModule.
 *
 */
@ComponentScan
@SpringBootApplication
@EnableDiscoveryClient
public class WebServer {

	private final int cacheSeconds = 60;

	/**
	 * WebService Main Class.
	 *
	 * @param args 실행시 필요 파라메타 값
	 */
	public static void main(final String[] args) {
		System.setProperty("spring.config.name", "conf/web/application");
		SpringApplication.run(WebServer.class, args);
	}

	/**
	 * ReloadableResourceBundleMessageSource 설정 빈객체 생성.
	 *
	 * @param
	 * @return Bean 객체
	 */
	@Bean
	public ReloadableResourceBundleMessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasenames("classpath:/messages/messages");
		messageSource.setDefaultEncoding("UTF-8");
		messageSource.setCacheSeconds(cacheSeconds);
		return messageSource;
	}

	/**
	 * messageSource 다국어 사용하기위한 MessageSourceAccessor 생성.
	 *
	 * @param
	 * @return Bean 객체
	 */
	@Bean
	public MessageSourceAccessor getMessageSourceAccessor() {
		ReloadableResourceBundleMessageSource m = messageSource();
		return new MessageSourceAccessor(m);
	}


	/**
	 * RestTemplate 사용하기 위한 Bean 생성.
	 *
	 * @param
	 * @return Bean 객체
	 */
	@LoadBalanced
	@Bean
	RestTemplate restTemplate() {
		return new RestTemplate();
	}


}
