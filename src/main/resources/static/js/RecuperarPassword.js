$(document).ready(
		function() {
			
				$('button[name="modificar"]').click(function (event) {
					alert("Dentro");
					var password1=document.getElementById("password1").value;
					var password2=document.getElementById("password2").value;
					var email=document.getElementById("email").value;
												
					// event.preventDefault();
					var data1 = $('#recuperarpassword').serialize();
					 // Añadimos el parámetro que espera el controlador concreto
					//data1 += '&addItem';
					var data1 =+'&email='+email+'&pass='+password1;	
					//data1='&pass='+password1
					alert(data1);
					$.post('/ModificarPassword', data1);
					/*
					var name1="aa";
					    $.ajax({
					        type: "POST",
					        url: "/ModificarPassword",
					        data : {'email':email},
					        statusCode: {
					            404: function () { $("#messege").text("Page not found"); },
					            500: function () { $("#messege").text("internal server error"); }
					        },
					        success: function (data) {
					            $("#messege").html(data); 
					        } });
					        */
					        alert("Fin1");
					});
					
				});
					

			
//});

function replaceItems (html) {
   				 $('#ver').replaceWith($(html));
			}

function ValidadEmail(){
	
	var patron =/^(([^<>()[\]\.,;:\s@\"]+(\.[^<>()[\]\.,;:\s@\"]+)*)|(\".+\"))@(([^<>()[\]\.,;:\s@\"]+\.)+[^<>()[\]\.,;:\s@\"]{2,})$/i;
	var email=document.getElementById("email").value;
	
	if(patron.test(email)){
		 var url="/ValidarEmail/"+email;
    	 window.location.href=url;
	}else{
		alert("Email no válido");
	}
	
	
}

function modificarpassword(){
	
	var password1=document.getElementById("password1").value;
	var password2=document.getElementById("password2").value;
	var email=document.getElementById("email").value;
	
	if(password1==password2 && Object.keys(password1).length>=8 ){
		
		 var url="/ModificarPassword/"+email+"/"+password1;
    	// window.location.href=url;

	}else{
		alert("La password debe tener como minimo 8 carateres y deben de ser iguales");
	}
	
}

