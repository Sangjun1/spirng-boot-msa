package com.playd.microservices.servers.commons.common.web;

import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class IndexController {


	@RequestMapping("/")
	public String helloWorld() throws Exception{
		return "Hello World \n";
	}

	@RequestMapping("actuator/info")
	public String info() throws Exception{
		return "Hello World \n";
	}

	@RequestMapping("/common/test")
	public String test() {

		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("userId","jun");
		jsonObject.addProperty("seq","1");
		jsonObject.addProperty("userName","이상준");

		return jsonObject.toString();
	}
}