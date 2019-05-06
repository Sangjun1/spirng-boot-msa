package com.igloosec.smartguard.microservices;

import com.igloosec.smartguard.microservices.servers.registration.RegistrationServer;

/**
 * MicroServiceMain 메인 함수.
 *
 *
 */
public class MicroServiceMain {

	/**
	 * MicroServiceMain Main Class.
	 *
	 * @param args 실행시 필요 파라메타 값
	 */
	public static void main(final String[] args) {
		RegistrationServer.main(args);
	}

}
