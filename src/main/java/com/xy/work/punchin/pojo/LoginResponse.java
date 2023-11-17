package com.xy.work.punchin.pojo;

import lombok.Data;

@Data
public class LoginResponse {
	private String access_token;
	private String refresh_token;
	private String license;
	private String accountId;
	private String headerImage;
	private String scope;
	private String orgInfo;
	private String name;
	private String tokenType;
	private int expiresIn;


}
