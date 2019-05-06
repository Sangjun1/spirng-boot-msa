package com.igloosec.smartguard.microservices.servers.commons.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;

/**
 * 
 * @author leesangjun
 * 
 */
@Aspect
@Slf4j
public class GlobalAspect {


	@AfterReturning(value = "within(com.igloosec.smartguard.*.commons.*.*)" , returning = "ret")
	public void after(JoinPoint jp, Object ret) {

		System.out.println("After Returning!!");
	}

	@AfterThrowing(value = "within(com.igloosec.smartguard.*.web.*)")
	public void afterThrow(JoinPoint jp) {
		System.out.println("After Throwing!!");
	}


}
