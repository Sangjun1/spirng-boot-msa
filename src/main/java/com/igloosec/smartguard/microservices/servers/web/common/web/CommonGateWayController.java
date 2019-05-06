/**
 * com.igloosec.smartguard.microservices.servers.web.common.web .
 * 패키지 위치
 *
 */
package com.igloosec.smartguard.microservices.servers.web.common.web;

import com.igloosec.smartguard.microservices.servers.commons.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * CommonGateWayController .
 * Common Service 를 호출하기위한 GateWay Controller.
 */
@RestController
@RequestMapping("/api/common")
public class CommonGateWayController {

	/**
	 * COMMON_SERVICE_URL CommonService 호출을 위한 URL 정의
	 */
	private static String COMMON_SERVICE_URL = "http://localhost:18444";

	@Autowired
	private RestTemplate restTemplate;

	@RequestMapping(value = "/user/insertUser", method = {RequestMethod.GET, RequestMethod.POST})
	public ResponseEntity<Map<String, Object>> insertUser(@RequestParam User userModel, HttpServletRequest request, HttpServletResponse response) throws Exception {

		String url = COMMON_SERVICE_URL + request.getRequestURI();

		String testStr = restTemplate.getForObject(url,String.class);



		Map<String, Object> map = new HashMap<String, Object>();
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}

	@RequestMapping(value = "/user/test", method = {RequestMethod.GET, RequestMethod.POST})
	public ResponseEntity<Map<String, Object>> test(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {

		String url = COMMON_SERVICE_URL + request.getRequestURI();

		Map<String, Object> map = new HashMap<String, Object>();

		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}
}
