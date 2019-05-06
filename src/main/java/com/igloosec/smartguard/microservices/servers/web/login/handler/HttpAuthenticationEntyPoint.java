/**
 * com.igloosec.smartguard.microservices.servers.web.login.handler .
 * 패키지 위치
 *
 */
package com.igloosec.smartguard.microservices.servers.web.login.handler;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Component 생성.
 * HttpAuthenticationEntyPoint 생성
 * 인터셉터 처리 부분 로직 추가
 * 401 에러 반환
 */
@Component
public class HttpAuthenticationEntyPoint implements AuthenticationEntryPoint {

	/**
	 * 권한에 맞지않는 입터센터 부분 commence 생성.
	 *
	 * @param httpServletRequest Request
	 * @param httpServletResponse Response
	 * @param e AuthenticationException 값 추출
	 */
	@Override
	public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
		//401 에러 전송
		httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
	}
}
