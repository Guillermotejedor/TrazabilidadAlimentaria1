package com.trazabilidad.modelo;

import java.util.Objects;


import javax.validation.constraints.Size;

import org.springframework.stereotype.Service;
@Service
public class ModificarPassword {
	
	@Size(min=8,message="Especifique una password minimo 8 caracteres")
	private String password1;
	@Size(min=8,message="Confirme la password")
	private String password2;
	private String email;
	
	
	
	
	
	public ModificarPassword() {
		super();
	}





	public ModificarPassword(String password1, String password2, String email) {
		super();
		this.password1 = password1;
		this.password2 = password2;
		this.email = email;
	}





	public String getPassword1() {
		return password1;
	}





	public void setPassword1(String password1) {
		this.password1 = password1;
	}





	public String getPassword2() {
		return password2;
	}





	public void setPassword2(String password2) {
		this.password2 = password2;
	}





	public String getEmail() {
		return email;
	}





	public void setEmail(String email) {
		this.email = email;
	}





	@Override
	public int hashCode() {
		return Objects.hash(email, password1, password2);
	}





	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ModificarPassword other = (ModificarPassword) obj;
		return Objects.equals(email, other.email) && Objects.equals(password1, other.password1)
				&& Objects.equals(password2, other.password2);
	}

	@Override
	public String toString() {
		return "ModificarPassword [password1=" + password1 + ", password2=" + password2 + ", email=" + email + "]";
	}
	
	

}
