package com.henz.joel.model;

public class User {
	
	private final int userId;
	private final String firstName;
	private final String lastName;
	private final String email;
	private final String password;
	private final String role;
	
	private User(final int userId, final String firstName, final String lastName, final String email, final String password, final String role) {
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.role = role;
	}
	
	public int getUserId() {
		return userId;
	}



	public String getFirstName() {
		return firstName;
	}



	public String getLastName() {
		return lastName;
	}



	public String getEmail() {
		return email;
	}



	public String getPassword() {
		return password;
	}



	public String getRole() {
		return role;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", password=" + password + ", role=" + role + "]";
	}

	public static class Builder{
		
		private int userId;
		private String firstName;
		private String lastName;
		private String email;
		private String password;
		private String role;
		
		public Builder withUserId(final int userId) {
			this.userId = userId;
			return this;
		}
		
		public Builder withFirstName(final String firstName) {
			this.firstName = firstName;
			return this;
		}
		
		public Builder withLastName(final String lastName) {
			this.lastName = lastName;
			return this;
		}
		
		public Builder withEmail(final String email) {
			this.email = email;
			return this;
		}
		
		public Builder withPassword(final String password) {
			this.password = password;
			return this;
		}
		
		public Builder withRole(final String role) {
			this.role = role;
			return this;
		}
		
		public User build() {
			return new User(
					this.userId,
					this.firstName,
					this.lastName,
					this.email,
					this.password,
					this.role
			);
		}
	}
}
