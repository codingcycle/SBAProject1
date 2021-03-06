package com.iiht.evaluation.coronokit.model;

public class User {
	private String userName;
	private String mobile;
	private String email;

	public User(String userName) {
		super();
		this.userName = userName;
	}

	public User(String userName, String mobile, String email) {
		super();
		this.userName = userName;
		this.mobile = mobile;
		this.email = email;
	}

	public User() {
		super();
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
