$(document).ready(function() {
			//Distribuir cantidad lote
			$('#distribuir-modal').on('show.bs.modal', function (event) {
    		var button = $(event.relatedTarget); // Button that triggered the modal  		
  			var lote = button.data('id'); // Extract info from data-* attributes
 			var nombre_lote = button.data('nombre');
 			var cantidad_lote = button.data('cantidad');
 			document.getElementById("lote").value=lote;
 			document.getElementById("tipomovimiento").value="-1";
 			document.getElementById("distribuircantidad").value=0;
 			document.getElementById("cantidad").value=cantidad_lote;
 		 	var modal = $(this);
 		 	modal.find('.modal-title').text('Lote a distribuir: ' + nombre_lote);
  			//modal.find('.modal-body input').val(recipient);
			});
		});
		//Aceptar ejecutar receta
		function aceptar(){
		
		 var patron =  /^[0-9]*(\.?)[0-9]+$/;
    	if (patron.test(document.getElementById("distribuircantidad").value) && document.getElementById("tipomovimiento").value !="-1" && document.getElementById("destinatario").value !=""){
    		var cantidad=parseInt(document.getElementById("cantidad").value,10);
    		var distribuir=parseInt(document.getElementById("distribuircantidad").value,10);
    		if(cantidad>=distribuir){
	    		var lote= document.getElementById("lote").value; 
	    		var cantidad=document.getElementById("distribuircantidad").value;
	    		var tipomovimiento=document.getElementById("tipomovimiento").value;
	    		var destinatario=document.getElementById("destinatario").value;
	    		var url="/DistribuirLote/"+lote+"/"+cantidad+"/"+tipomovimiento+"/"+destinatario;
	    		window.location.href=url;
	    	}else{
				alert("No tiene tanta cantidad para distribuir");
			}
    	}else{
    		alert("Introduzca un valor numerico, seleccione un tipo de movimiento y destinatario");
    	}
		}// fin aceptar