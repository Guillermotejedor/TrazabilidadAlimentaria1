package com.trazabilidad.configuracion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.trazabilidad.modelo.Usuario;
import com.trazabilidad.repositorio.UsuarioRepositorio;



@Service("userDetailsService")
public class UsuarioDetalleServicio implements UserDetailsService{

	@Autowired
	UsuarioRepositorio  usuariorepositorio;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
			Usuario usuario=usuariorepositorio.findFirstByUser(username);
			UserBuilder builder=null;
			if(usuario!=null) {
				builder=User.withUsername(username);
				builder.disabled(false);
				builder.password(usuario.getPassword());
				builder.authorities(new SimpleGrantedAuthority("ROLE_"+usuario.getRol()));
			}else {
				throw new UsernameNotFoundException("Usuario no encontrado");
			}
			
			return builder.build();
	}

}
