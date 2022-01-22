package com.henz.joel.other;

public enum UserRoles {

	USER("user"),
	ADMIN("admin");
	
	private String role;

	UserRoles(String role) {
		// TODO Auto-generated constructor stub
		this.role = role;
	}
	
	public String getRole() {
		return this.role;
	}
}
