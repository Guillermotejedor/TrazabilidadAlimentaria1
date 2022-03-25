package com.trazabilidad.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Usuario {
	@Id
	@GeneratedValue
	private long id;
	@Column(unique=true)
	@NotNull
	@Size(min=4,message="Especifique el User del Usuario")
	private String user;
	@NotNull
	@Size(min=8,message="Especifique la password del Usuario. Minimo 8 caracteres")
	private String password;
	@NotNull
	@Size(min=2,message="Especifique el nombre del Usuario")
	private String nombre;
	@NotNull
	@Size(min=2,message="Especifique el apellido 1 del Usuario")
	private String apellido1;
	@NotNull
	@Size(min=2,message="Especifique el apellido 2 del Usuario")
	private String apellido2;
	@NotEmpty(message="Especifique un rol")
	private String rol;
	@Email(message="Dirección de Email no validad")
	private String email;

	private boolean enable;
	
	public Usuario() {
		super();
	}
	public Usuario(String user, String password, String nombre, String apellido1, String apellido2, String roll,
			String email) {
		super();
		this.user = user;
		this.password = password;
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.rol = roll;
		this.email = email;
	}
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	

	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido1() {
		return apellido1;
	}
	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}
	public String getApellido2() {
		return apellido2;
	}
	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}
	public String getRol() {
		return rol;
	}
	public void setRol(String roll) {
		this.rol = roll;
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
	

}
