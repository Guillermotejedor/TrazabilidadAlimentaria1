package com.trazabilidad.controladores;



import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.trazabilidad.modelo.Usuario;
import com.trazabilidad.servicios.UsuarioServicios;



@Controller
public class ControladorUsuario {
	
	@Autowired
	UsuarioServicios usuarioservicios;
	
	@GetMapping("/Usuario")
	public String NuevoUsuario(Model model) {
		model.addAttribute("usuario",new Usuario());
		return "/admin/Usuario";
	}

	@PostMapping("/Usuario/new/Submit")
	public String ProcesarUsuario(@Valid Usuario usuario,BindingResult validar,Model model) {
				
		if(validar.hasErrors()) {			
			return "/admin/Usuario";
		}else {
			try {
				
				//if(usuario.getId()==0)
					System.out.println("ID-->"+usuario.getId());
					usuarioservicios.registrar(usuario);
					System.out.println("ID 1 -->"+usuario.getId());
				//else
				//	usuarioservicios.ActualizarUsuario(usuario.getRol(), usuario.getEmail(), usuario.getUser());
				return "redirect:/AdministracionUsuario";				
			}catch(Exception e) {				
				model.addAttribute("errorduplicado", "El user esta duplicado");
				usuario.setId(0);
				model.addAttribute("usuario",usuario);
				//System.out.println("ID 1 -->"+usuario.getId());
				return "/admin/Usuario";
			}		
		}
		
	}
	
	@GetMapping("/Usuario/edit/Submit/{email}/{rol}/{user}")
	public String UsuarioEditado(@PathVariable("user") String user,@PathVariable("email") String email,@PathVariable("rol") String rol) {
		System.out.println("Valor user-->"+user);
		usuarioservicios.ActualizarUsuario(rol, email, user);
		return "redirect:/AdministracionUsuario";
	}
	
	@GetMapping("/EliminarUsuario/{id}/eliminar")
	public String EliminarUsuario(@PathVariable long id) {
		Usuario usuario=usuarioservicios.findById(id);
		usuarioservicios.EliminarUsuario(usuario);
		return "redirect:/AdministracionUsuario";
	}
	@GetMapping("/Usuario/{id}")
	public String EditarUsuario(Model model,@PathVariable long id) {
		model.addAttribute("usuario", usuarioservicios.findById(id));
		return "/admin/Usuario";
	}
	
	
	@GetMapping("/RecuperarPassword")
	public String RecuperarPassord(Model model) {
		model.addAttribute("recuperarpassword", new Usuario());
		model.addAttribute("encontrado", false);
		return "/RecuperarPassword";
	}
	
	@GetMapping("/ValidarEmail/{email}")
	public String ValidadEmail(@PathVariable String email,Model model) {
		boolean encontrado=false;
		Usuario user=usuarioservicios.findByEmail(email);
		if(user!=null) {
			System.out.println("Email -->"+email);
			encontrado=true;
		}else {
			model.addAttribute("errornoencontrado", "Revise el Email. Email no encontrado");
			user=new Usuario();
			user.setEmail(email);
		}
		model.addAttribute("recuperarpassword", user);
		model.addAttribute("encontrado", encontrado);
		return "/RecuperarPassword";
	}
	
	
	@GetMapping("/ModificarPassword/{email}/{password}")
	public String ModificarPassword(@PathVariable String email,@PathVariable String password) {
		usuarioservicios.ResetPassword(password, email);
		return "/login";
	}

}

