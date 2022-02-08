function buscar(){
 		var buscar=document.getElementById('buscar').value;
 		var patron = /^[a-zA-Z\s]+$/;
    	if (patron.test(buscar)){
 			window.location.href="/BuscarHistoricoLote/"+buscar;
 		}else{
 		alert("Introduzca un nombre de la receta");
 		
 		}
 	}