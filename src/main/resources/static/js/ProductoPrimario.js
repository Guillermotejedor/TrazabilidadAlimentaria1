$(document).ready(function() {
			//Desactivar producto
			$('#delete-modal').on('show.bs.modal', function(event) {
				var button = $(event.relatedTarget);
				var data = button.data('id');
				var nombre_producto = button.data('nombre');
				var modal = $(this);
				var a = modal.find('.modal-body a')[0];
				a.href = '/ListaProductosPrimarios/' + data + '/eliminar';
				modal.find('.modal-title').text('Va a desactivar ' + nombre_producto);
			});
			//Añadir cantidad al producto
			$('#añadir-modal').on('show.bs.modal', function (event) {
    	
  			var button = $(event.relatedTarget); // Button that triggered the modal  		
  			var id_producto = button.data('id'); // Extract info from data-* attributes
 			var nombre_producto = button.data('nombre');
 			document.getElementById("id_producto").value=id_producto;
 		 	var modal = $(this);
 		 	modal.find('.modal-title').text('Va a añadir ' + nombre_producto);
  			//modal.find('.modal-body input').val(recipient);
			});
			//Diminuir producto
			$('#disminuir-modal').on('show.bs.modal', function(event) {
			var button = $(event.relatedTarget); // Button that triggered the modal  		
  			var id_producto = button.data('id'); // Extract info from data-* attributes
 			var nombre_producto = button.data('nombre');
 			var cantidad=button.data('cantidad');
 			document.getElementById('cantidadalmacen').value=cantidad;
 			document.getElementById("id_producto").value=id_producto;
 		 	var modal = $(this);
 		 	modal.find('.modal-title').text('Va a disminuir ' + nombre_producto);
			});

		});
		//Aceptar añadir cantidad al producto
		function aceptar(){
		
		 var patron =  /^[0-9]*(\.?)[0-9]+$/;
    	if (patron.test(document.getElementById("añadircantidad").value)){
    		var id_producto= document.getElementById("id_producto").value; 
    		var cantidad=document.getElementById("añadircantidad").value;
    		var url="/ProductoPrimario/"+id_producto+"/"+cantidad+"/añadir";
    		window.location.href=url;
    	}else{
    		alert("No a introducido un valor numerico");
    	}
		}// fin aceptar
		// Disminuir cantidad producto
		function disminuir(){
		
		 var patron =  /^[0-9]*(\.?)[0-9]+$/;
    	if (patron.test(document.getElementById("disminuircantidad").value)){
    		var cantidad=Number(document.getElementById("disminuircantidad").value);
    		var almacenada=Number(document.getElementById("cantidadalmacen").value);
    		if(almacenada>=cantidad){
    		    var id_producto= document.getElementById("id_producto").value; 
    			var cantidad=document.getElementById("disminuircantidad").value;
    			var url="/ProductoPrimario/"+id_producto+"/"+cantidad+"/disminuir";
    			window.location.href=url;
    		}else{
    			alert("Introduzca un valor menor");
    		}
    	}else{
    		alert("No a introducido un valor numerico");
    	}
		}// fin aceptar
	