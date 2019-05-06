package com.igloosec.smartguard.microservices.servers.commons.user.model;

import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

@Data
public class User implements Serializable {

	private String userId;
	private String username;
	private String name;
	private String password;
	private String telephone;
	private String email;
	private String applyReason;
	private String useStartDate;
	private String useEndDate;
	private String passwordResetYn;
	private String uacCd;
	private String apiAccessKey;
	private String accountStatus;
	private String createUserId;
	private String modifyUserId;

	private Date createDate;
	private Date modifyUserDate;
}
