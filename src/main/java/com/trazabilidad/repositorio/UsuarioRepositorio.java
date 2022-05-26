 package com.trazabilidad.repositorio;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.trazabilidad.modelo.Usuario;



public interface UsuarioRepositorio extends JpaRepository<Usuario,Long>{
		Usuario findFirstByUser(String username);
		Usuario findFirstByEmail(String email);
		@Query("select u from Usuario u")
		List<Usuario> alluser();
		@Modifying
		@Transactional
		@Query("UPDATE Usuario u SET u.rol= :rol, u.email=:email where u.user=:user")
		public void updateUsuario(@Param("rol") String roll,@Param("email") String email,@Param("user") String user);
		
		@Modifying
		@Transactional
		@Query("UPDATE Usuario u SET u.password= :password where u.user=:user")
		public void updatepassword(@Param("password") String password,@Param("user") String user);
}
