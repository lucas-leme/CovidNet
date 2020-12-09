package net.javaguides.usermanagement.model;

import java.time.LocalDateTime;

public class User {

	private String email, psswd;
	private LocalDateTime dataLogin;
	
	public User(String email, String psswd)
	{
		this.setEmail(email);
		this.setPsswd(psswd);
		
		setDataLogin(LocalDateTime.now());
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPsswd() {
		return psswd;
	}

	private void setPsswd(String psswd) {
		this.psswd = psswd;
	}

	public LocalDateTime getDataLogin() {
		return dataLogin;
	}

	public void setDataLogin(LocalDateTime dataLogin) {
		this.dataLogin = dataLogin;
	}
	
}
