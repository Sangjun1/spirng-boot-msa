/**
 * com.igloosec.smartguard.microservices.servers.web.login.model .
 * 패키지 위치
 *
 */
package com.igloosec.smartguard.microservices.servers.web.login.model;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;

/**
 * LoginInfo 로그인을 위한 Model.
 */
@SuppressWarnings("serial")
@Data
public class LoginInfo implements UserDetails {

	private String userId;
	private String username;
	private String name;
	private String password;
	private String telephone;
	private String email;
	private String applyReason;
	private Date useStartDate;
	private Date useEndDate;
	private String passwordResetYn;
	private String uacCd;
	private String apiAccessKey;
	private String sessionKey;
	private int loginFailCount;
	private String accountStatus;

	private boolean isAccountNonExpired = true;
	private boolean isAccountNonLocked = true;
	private boolean isCredentialsNonExpired = true;
	private boolean isEnabled = true;
	private Collection<? extends GrantedAuthority> authorities;

	private int loginStatus;

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
