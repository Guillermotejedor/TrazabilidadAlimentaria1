function ValidadEmail(){
	
	var patron =/^(([^<>()[\]\.,;:\s@\"]+(\.[^<>()[\]\.,;:\s@\"]+)*)|(\".+\"))@(([^<>()[\]\.,;:\s@\"]+\.)+[^<>()[\]\.,;:\s@\"]{2,})$/i;
	var email=document.getElementById("email").value;
	
	if(patron.test(email)){
		 var url="/ValidarEmail/"+email;
    	 window.location.href=url;
	}else{
		alert("Email no valido");
	}
	
	
}

function modificarpassword(){
	
	var password1=document.getElementById("password1").value;
	var password2=document.getElementById("password2").value;
	var email=document.getElementById("email").value;
	
	if(password1==password2 && Object.keys(password1).length>=8 ){
		
		 var url="/ModificarPassword/"+email+"/"+password1;
    	 window.location.href=url;
	}else{
		alert("La password debe tener como minimo 8 carateres y deben de ser iguales");
	}
	
}