function validar(){
	
	
	var patron =/^(([^<>()[\]\.,;:\s@\"]+(\.[^<>()[\]\.,;:\s@\"]+)*)|(\".+\"))@(([^<>()[\]\.,;:\s@\"]+\.)+[^<>()[\]\.,;:\s@\"]{2,})$/i;
	var email=document.getElementById("email").value;
	var rol=document.getElementById("rol").value;
	var user=document.getElementById("user").value;
	if(patron.test(email)){
		 var url="/Usuario/edit/Submit/"+email+"/"+rol+"/"+user;
    	 window.location.href=url;
	}else{
		alert("Email no valido");
	}
}