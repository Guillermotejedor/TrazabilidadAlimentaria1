package com.trazabilidad.modelo;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="token")
public class Token {
	@Id
	private String token;
	private String email;
	private boolean enable;
	
	
	
	
	public Token() {
		super();
	}


	public Token(String token, String email, boolean enable) {
		super();
		this.token = token;
		this.email = email;
		this.enable = enable;
	}


	public String getToken() {
		return token;
	}


	public void setToken(String token) {
		this.token = token;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public boolean isEnable() {
		return enable;
	}


	public void setEnable(boolean enable) {
		this.enable = enable;
	}


	@Override
	public int hashCode() {
		return Objects.hash(email, enable, token);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Token other = (Token) obj;
		return Objects.equals(email, other.email) && enable == other.enable && Objects.equals(token, other.token);
	}


	@Override
	public String toString() {
		return "Token [token=" + token + ", email=" + email + ", enable=" + enable + "]";
	}
	
	
	

}
