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
	private long id;
	private boolean enable;
	
	
	
	
	public Token() {
		super();
	}




	public Token(String token, long id, boolean enable) {
		super();
		this.token = token;
		this.id = id;
		this.enable = enable;
	}




	public String getToken() {
		return token;
	}


	public void setToken(String token) {
		this.token = token;
	}





	public long getId() {
		return id;
	}




	public void setId(long id) {
		this.id = id;
	}




	public boolean isEnable() {
		return enable;
	}


	public void setEnable(boolean enable) {
		this.enable = enable;
	}




	@Override
	public int hashCode() {
		return Objects.hash(enable, id, token);
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
		return enable == other.enable && Objects.equals(id, other.id) && Objects.equals(token, other.token);
	}




	@Override
	public String toString() {
		return "Token [token=" + token + ", id=" + id + ", enable=" + enable + "]";
	}


	



	
	

}
