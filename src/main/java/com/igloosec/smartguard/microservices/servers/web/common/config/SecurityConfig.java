/**
 * com.igloosec.smartguard.microservices.servers.web.common.config
 * 패키지 위치.
 */
package com.igloosec.smartguard.microservices.servers.web.common.config;


import com.igloosec.smartguard.microservices.servers.web.login.handler.HttpAuthenticationEntyPoint;
import com.igloosec.smartguard.microservices.servers.web.login.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * SecurityConfig .
 * SecurityConfig 위한 Class.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	/**
	 * HttpAuthenticationEntyPoint 객체 생성
	 */
	@Autowired
	private HttpAuthenticationEntyPoint httpAuthenticationEntyPoint;

	/**
	 * LoginService 객체 생성
	 */
	@Autowired
	LoginService loginService;


	/**
	 * PasswordEncoder Bean 객체 생성
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	/**
	 * WebSecurity 사용을위한 configure 생성
	 */
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**");
	}

	/**
	 * HttpSecurity 사용을위한 configure 생성
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.csrf().disable()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.NEVER)
				.and()
				.exceptionHandling().authenticationEntryPoint(httpAuthenticationEntyPoint)
				.and()
				.authorizeRequests()
				.antMatchers("/api/auth/login").permitAll()
				.antMatchers("/api/**").hasAuthority("ROLE_ADMIN")
				.anyRequest().authenticated()
				.and()
				.formLogin()
				.and().logout().logoutUrl("/api/auth/logout");
	}

	/**
	 * 인증을 위한 Service 객체 주입
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(loginService);
	}

	/**
	 * 인증을 위한 AuthenticationManager bean 생성
	 */
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}


}