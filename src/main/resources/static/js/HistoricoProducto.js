function buscar(){
 		var buscar=document.getElementById('buscar').value;
 		var patron = /^[a-zA-Z\s]+$/;
    	if (patron.test(buscar)){
 			window.location.href="/BuscarHistoricoProducto/"+buscar;
 		}else{
 		alert("Introduzca un valor valido");
 		
 		}
 	}