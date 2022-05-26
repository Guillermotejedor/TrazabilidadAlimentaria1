package com.trazabilidad.controladores;



import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import com.trazabilidad.modelo.Token;
import com.trazabilidad.modelo.Usuario;
import com.trazabilidad.modelo.ModificarPassword;
import com.trazabilidad.servicios.EnvioEmail;
import com.trazabilidad.servicios.TokenServicios;
import com.trazabilidad.servicios.UsuarioServicios;



@Controller
public class ControladorUsuario {
	
	@Autowired
	UsuarioServicios usuarioservicios;
	@Autowired
	EnvioEmail mail;
	@Autowired
	TokenServicios tokenservicios;
	
	
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
				usuarioservicios.registrar(usuario);
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
		return "/EnviarEmail";
				
	}
	
	@GetMapping("/ValidarEmail/{email}")
	public String ValidadEmail(@PathVariable String email,Model model) {
		boolean encontrado=false;
		Usuario user=usuarioservicios.findByEmail(email);
		if(user!=null) {
			System.out.println("Email -->"+email);
			String token=UUID.randomUUID().toString();
			String Url="\r\n" +"http://localhost:8080/ActializarPassword?token="+token;
			mail.send(null, email, "Cambio de Password", Url);
			Token tokenobj=new Token(token,user.getId(),true);
			tokenservicios.Guardar(tokenobj);
			encontrado=true;
			model.addAttribute("errornoencontrado", "Le hemos enviado un Email. Abra el Email para continuar con el cambio de password");
		}else {
			model.addAttribute("errornoencontrado", "Revise el Email. Email no encontrado");
			user=new Usuario();
			user.setEmail(email);
		}
		model.addAttribute("recuperarpassword", user);
		model.addAttribute("encontrado", encontrado);
		return "/EnviarEmail";
	}
	
	@GetMapping("/ActializarPassword")
	public  String ActualizarPassword(@RequestParam("token") String token,Model model) {
		System.out.println("token -->"+token);
		String Url="";
		ModificarPassword modificar=new ModificarPassword();
		Token tokenobj=tokenservicios.TokenPorToken(token);
		
		if(tokenobj!=null) {
			Usuario user=usuarioservicios.findById(tokenobj.getId());			
			modificar.setUser(user.getUser());
			modificar.setEmail(user.getEmail());
			if(tokenobj.isEnable()) {
				tokenobj.setEnable(false);
				tokenservicios.Guardar(tokenobj);
				model.addAttribute("modificarpassword",modificar);
				Url="/RecuperarPassword";
			}else {
				model.addAttribute("encontrado", false);
				model.addAttribute("recuperarpassword",modificar);
				model.addAttribute("errornoencontrado", "Token caducado. Si necesita modificar la Password, genere uno nuevo");
				Url= "/EnviarEmail";
			}
		}else {
			Url="/login";
		}
		
		return Url;
	}
	
	
	@PostMapping("/ModificarPassword")
	public String ModificarPassord(@Valid @ModelAttribute("modificarpassword") ModificarPassword modificarpassword,BindingResult validar,Model model) {
				
		if(validar.hasErrors()) {			
			return "/RecuperarPassword";
		}else {
			if(modificarpassword.getPassword1().equals(modificarpassword.getPassword2())) {
				usuarioservicios.ResetPassword(modificarpassword.getPassword1(),modificarpassword.getUser());
			}else {
				model.addAttribute("modificarpassword", modificarpassword);
				model.addAttribute("errornoencontrado", "Las Password no son iguales");
				return "/RecuperarPassword";
			}
		
		return "/login";
		}
	}
}

