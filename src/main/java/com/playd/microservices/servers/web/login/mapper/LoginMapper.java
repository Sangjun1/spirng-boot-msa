/**
 * com.igloosec.smartguard.microservices.servers.web.login.mapper .
 * 패키지 위치
 *
 */
package com.playd.microservices.servers.web.login.mapper;

import com.playd.microservices.servers.web.login.model.LoginInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * LoginMapper 로그인을 위한 Mapper.
 */
@Mapper
@Repository
public interface LoginMapper {

	public LoginInfo selectLoginUser(Object parameter);
	public int selectLoginCount(Object parameter);
	public void updateLoginAccessStatus(Object parameter);
}
