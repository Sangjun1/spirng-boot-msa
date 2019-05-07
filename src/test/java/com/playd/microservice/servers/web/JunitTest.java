package com.playd.microservice.servers.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
public class JunitTest {

	@Test
	public void test() throws Exception {

		Date date = new Date();

		System.out.println(date);

	}


}
