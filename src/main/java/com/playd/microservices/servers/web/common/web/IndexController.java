/**
 * com.playd.microservice.microservices.servers.web.common.web .
 * 패키지 위치
 *
 */
package com.playd.microservices.servers.web.common.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * IndexController 최초 Index.html 호출을 위한 Controller.
 */
@Controller
@Slf4j
public class IndexController {

	/**
	 * login 권한 부여 Method.
	 *
	 * @param request request
	 * @param response response
	 * @return index.html html 리턴
	 */
	@RequestMapping("/")
	public String index(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "index";
	}
}
