/**
 * com.igloosec.smartguard.microservices.servers.web.common.config
 * 패키지 위치.
 */
package com.igloosec.smartguard.microservices.servers.web.common.config;

/**
 * BaseCode .
 * BaseCode 위한 Class.
 */
public class BaseCode {

	public static final String Y = "Y";
	public static final String ROLE_ADMIN = "ROLE_ADMIN";

	/*
	 * JOB0001 - 로그인 성공
	 * JOB0002 - 로그인 실패
	 * JOB0003 - 잠금 상태
	 * JOB0004 - 권한 실패 (AccessDenied)
	 * JOB0005 - 초기화 상태
	 * JOB0006 - 사용기간 정지
	 */
	public static final String JOB_0001 = "JOB0001";
	public static final String JOB_0002 = "JOB0002";
	public static final String JOB_0003 = "JOB0003";
	public static final String JOB_0004 = "JOB0004";
	public static final String JOB_0005 = "JOB0005";
	public static final String JOB_0006 = "JOB0006";
}
