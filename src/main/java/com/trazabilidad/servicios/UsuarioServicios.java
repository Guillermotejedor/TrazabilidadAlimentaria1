package com.trazabilidad.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.trazabilidad.modelo.Usuario;
import com.trazabilidad.repositorio.UsuarioRepositorio;


 
@Service
public class UsuarioServicios {
	
	@Autowired
	UsuarioRepositorio usuariorepositorio;
	@Autowired
	BCryptPasswordEncoder passwordencoder;
	
	public Usuario registrar(Usuario u) {
		u.setPassword(passwordencoder.encode(u.getPassword()));
		return usuariorepositorio.save(u);
	}
	
	public Usuario findById(long id) {
		return usuariorepositorio.findById(id).orElse(null);
	}
	public Usuario findByUser(String user) {
		return usuariorepositorio.findFirstByUser(user);
				
	}
	public Usuario findByEmail(String email) {
		return usuariorepositorio.findFirstByEmail(email);
	}
	
	public void ResetPassword(String pass,String email) {
		usuariorepositorio.updatepassword(passwordencoder.encode(pass), email);
	}
	
	public List<Usuario> TodosUsuarios(){
		return usuariorepositorio.alluser();
	}
	
	public void EliminarUsuario(Usuario usuario) {
		usuariorepositorio.delete(usuario);
	}
	
	public void ActualizarUsuario(String rol,String email,String user) {
		usuariorepositorio.updateUsuario(rol, email, user);
	}
}
