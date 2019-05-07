/**
 * com.playd.microservice.microservices.servers.web.login.web.
 *
 * @author 이상준
 */
package com.playd.microservices.servers.web.login.web;

import com.playd.microservices.servers.web.common.config.BaseCode;
import com.playd.microservices.servers.web.login.model.LoginInfo;
import com.playd.microservices.servers.web.login.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * LoginController 로그인을 위한 Controller.
 */
@RestController
@RequestMapping("/api/auth")
@Slf4j
public class LoginController {

	/**
	 * authenticationManager 권한 매니저.
	 */
	@Autowired
	private AuthenticationManager authenticationManager;

	/**
	 * loginService 로그인 서비스.
	 */
	@Autowired
	private LoginService loginService;

	// 2019.03.15
	// 추후 DB Config 설정값으로 빠질 예정
	private final int LOGIN_LOCK_CONUNT = 5;

	/**
	 * login Method.
	 *
	 * @param loginInfo loginInfo
	 * @param session    session
	 * @return login 페이지 이동
	 * Acconunt_Status 정의
	 * JOB0001 - 로그인 성공
	 * JOB0002 - 로그인 실패
	 * JOB0003 - 잠금 상태
	 * JOB0004 - 권한 실패 (AccessDenied)
	 * JOB0005 - 초기화 상태
	 * JOB0006 - 사용기간 정지
	 * @throws Exception 에러확인
	 */
	@RequestMapping(value = "/login", method = {RequestMethod.GET, RequestMethod.POST})
	public ResponseEntity<Map<String, Object>> login(@RequestBody final LoginInfo loginInfo, final HttpSession session) throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();
		LoginInfo selectLoginUser = loginService.selectLoginUser(loginInfo);
		selectLoginUser.setPassword("");
		//User 계정 체크
		if (selectLoginUser != null) {

			if (loginResetCheck(selectLoginUser)) {
				map.put("result", selectLoginUser.getAccountStatus());
				loginService.updateLoginAccessStatus(selectLoginUser);
				return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
			}

			if (loginLockCheck(selectLoginUser)) {
				map.put("result", selectLoginUser.getAccountStatus());
				loginService.updateLoginAccessStatus(selectLoginUser);
				return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
			}

			if (loginUseDateCheck(selectLoginUser)) {
				map.put("result", selectLoginUser.getAccountStatus());
				loginService.updateLoginAccessStatus(selectLoginUser);
				return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
			}

			UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(loginInfo.getUserId(), loginInfo.getPassword());
			try {
				Authentication authentication = authenticationManager.authenticate(token);
				SecurityContextHolder.getContext().setAuthentication(authentication);
				session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());

				selectLoginUser.setAccountStatus(BaseCode.JOB_0001);
				loginService.updateLoginAccessStatus(selectLoginUser);
				map.put("result", selectLoginUser);
			} catch (BadCredentialsException e) {
				//패스워드 실패
				loginFailureHandler(selectLoginUser);
				map.put("result", selectLoginUser.getAccountStatus());
			} catch (AccessDeniedException e1) {
				//권한 실패
				selectLoginUser.setAccountStatus(BaseCode.JOB_0004);
				loginService.updateLoginAccessStatus(selectLoginUser);
				map.put("result", selectLoginUser.getAccountStatus());
			}

		} else {
			//사용자 계정이 없습니다.
			selectLoginUser = new LoginInfo();
			selectLoginUser.setAccountStatus(BaseCode.JOB_0002);
			map.put("result", selectLoginUser.getAccountStatus());
		}

		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}

	/**
	 * login 실패 핸들러.
	 *
	 * @param loginInfo LoginInfo 객체.
	 *                   Acconunt_Status 상태
	 *                   JOB_0002
	 */
	private void loginFailureHandler(LoginInfo loginInfo) {
		loginInfo.setAccountStatus(BaseCode.JOB_0002);
		loginService.updateLoginAccessStatus(loginInfo);

	}

	/**
	 * login 패스워드 초기화 체크 여부.
	 *
	 * @param loginInfo LoginInfo 객체.
	 *                   Acconunt_Status 상태
	 *                   JOB_0005
	 * @return 체크 여부 true, false
	 */
	private boolean loginResetCheck(LoginInfo loginInfo) {
		if (loginInfo.getPasswordResetYn() != null) {
			if (loginInfo.getPasswordResetYn().equalsIgnoreCase(BaseCode.Y)) {
				loginInfo.setAccountStatus(BaseCode.JOB_0005);
				return true;
			}
		}
		return false;
	}

	/**
	 * login 락 체크 여부.
	 * LOGIN_LOCK_CONUNT 카운트에 따른 횟수 체크
	 *
	 * @param loginInfo LoginInfo 객체.
	 *                   Acconunt_Status 상태
	 *                   JOB_0003
	 * @return 체크 여부 true, false
	 */
	private boolean loginLockCheck(LoginInfo loginInfo) {
		if (loginInfo.getLoginFailCount() > LOGIN_LOCK_CONUNT) {
			loginInfo.setAccountStatus(BaseCode.JOB_0003);
			return true;
		}
		return false;
	}

	/**
	 * 사용자 사용 날짜 체크 여부.
	 * start_date , end_date 차이로 체크함.
	 *
	 * @param loginInfo LoginInfo 객체.
	 *                   Acconunt_Status 상태
	 *                   JOB_0006
	 * @return 체크 여부 true, false
	 */
	private boolean loginUseDateCheck(LoginInfo loginInfo) {

		if (loginInfo.getUseStartDate() != null && loginInfo.getUseEndDate() != null) {
			Date today = new Date();

			if ((today.getTime() > loginInfo.getUseStartDate().getTime()) && (today.getTime() < loginInfo.getUseEndDate().getTime())) {
				return false;
			}
			loginInfo.setAccountStatus(BaseCode.JOB_0006);
			loginService.updateLoginAccessStatus(loginInfo);
			return true;
		}
		return false;

	}
}
