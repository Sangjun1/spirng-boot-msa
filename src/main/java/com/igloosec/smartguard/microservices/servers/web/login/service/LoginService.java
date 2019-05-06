/**
 * com.igloosec.smartguard.microservices.servers.web.login.service .
 * 패키지 위치
 *
 */
package com.igloosec.smartguard.microservices.servers.web.login.service;

import com.igloosec.smartguard.microservices.servers.web.common.config.BaseCode;
import com.igloosec.smartguard.microservices.servers.web.login.mapper.LoginMapper;
import com.igloosec.smartguard.microservices.servers.web.login.model.LoginInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * LoginSerive 로그인을 위한 Service.
 */
@Service
public class LoginService implements UserDetailsService {

	/**
	 * LoginMapper Mapper 생성.
	 */
	@Autowired
	private LoginMapper loginMapper;

	/**
	 * login 계정, 패스워드 체크.
	 *
	 * @param username 유저 이름
	 * @return LoginInfo 모델객체 리턴
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		LoginInfo loginInfo = loginMapper.selectLoginUser(username);
		loginInfo.setAuthorities(getAuthorities(BaseCode.ROLE_ADMIN));
		return loginInfo;
	}

	/**
	 * login 권한 부여 Method.
	 *
	 * @param role 정책 Role
	 * @return authorities 권한정책 리턴
	 */
	public Collection<GrantedAuthority> getAuthorities(String role) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(role));
		return authorities;
	}

	/**
	 * login 정보 select Method.
	 *
	 * @param parameter Object 값 전달
	 * @return LoginInfo 모델객체 리턴
	 */
	@Transactional(readOnly = true)
	public LoginInfo selectLoginUser(Object parameter) {
		return loginMapper.selectLoginUser(parameter);
	}

	/**
	 * login Count select Method.
	 *
	 * @param parameter Object 값 전달
	 * @return int 갯수 리턴
	 */
	@Transactional(readOnly = true)
	public int selectLoginCount(Object parameter) {
		return loginMapper.selectLoginCount(parameter);
	}

	/**
	 * login 상태 Update Method.
	 *
	 * @param parameter Object 값 전달
	 */
	@Transactional(readOnly = false)
	public void updateLoginAccessStatus(Object parameter) {
		loginMapper.updateLoginAccessStatus(parameter);
	}
}
