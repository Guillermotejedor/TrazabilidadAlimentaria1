$(document).ready(
		function() {
			
				$('button[name="addItem"]').click(function (event) {
					
    				if($.isNumeric($("#cantidad").val()) && $("#ingrediente-datalist").val()!="" ){
														
					    event.preventDefault();
					    var data = $('#form-receta').serialize();
					    // Añadimos el parámetro que espera el controlador concreto
					    data += '&addItem';
					    var nombreingrediente = $("#ingrediente-datalist").val();
					    var idingrediente = $('#ingrediente-list [value="' +nombreingrediente+ '"]').data('value');
					    var idreceta=$("#idreceta").val();
					    var cantidad=$("#cantidad").val();
					    data+='&idreceta='+idreceta+'&idproducto='+idingrediente+'&cantidad='+cantidad;
					  
					   $.post('/receta/nuevo/submit', data, replaceItems);
					}else{
						alert("Debe introducir ingrediente y cantidad");
					}
			});
					
		function replaceItems (html) {
   				 $('#listaingredientes').replaceWith($(html));
			}
			
			});
			
			