 package com.trazabilidad.repositorio;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.trazabilidad.modelo.Usuario;



public interface UsuarioRepositorio extends JpaRepository<Usuario,Long>{
		Usuario findFirstByUser(String username);
		Usuario findFirstByEmail(String email);
		
		@Modifying
		@Transactional
		@Query("UPDATE Usuario u SET u.password= :password where u.user=:user")
		public void updatepassword(@Param("password") String password,@Param("user") String user);
}
