package com.trazabilidad.configuracion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration
@EnableWebSecurity
public class ConfiguracionSeguridad extends WebSecurityConfigurerAdapter {

	@Autowired
	UserDetailsService userdetailsservice;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userdetailsservice).passwordEncoder(passwordEncoder());
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
 

	@Override
	protected void configure(HttpSecurity http) throws Exception {
	http
		.authorizeRequests()
		.antMatchers("/ProductoPrimario/**").hasRole("ADMIN")
		.antMatchers("/ListaProductosPrimarios/**").hasRole("ADMIN")
		.antMatchers("/Receta/**").hasRole("ADMIN")
		.antMatchers("/AdministracionUsuario/**").hasRole("ADMIN")
		.antMatchers("/HistoricoProductoPrimario/**").hasRole("ADMIN")
		.antMatchers("/ActivarProductoPrimario/**").hasRole("ADMIN")
		.antMatchers("/css/**").permitAll()
		.antMatchers("/Login/**").permitAll()
		.antMatchers("/RecuperarPassword/**").permitAll() 
		.antMatchers("/ValidarEmail/**").permitAll() 
		.antMatchers("/ModificarPassword/**").permitAll()
		.antMatchers("/ActializarPassword/**").permitAll() 
		.anyRequest().authenticated() 
		.and()
		.formLogin()
		.loginPage("/login")
		.permitAll()
		.defaultSuccessUrl("/ListaRecetas")
		.and().logout().permitAll();
		//.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		//.logoutSuccessUrl("/")
		//.and()
		//.csrf().disable();

	
		
	}
	
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		  web.ignoring().antMatchers("/resources/**","/static/**","/css/**","/js/**");
	}
	
	

}
