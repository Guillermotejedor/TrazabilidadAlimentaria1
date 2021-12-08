$(document).ready(
		function() {
			
				$('button[name="addItem"]').click(function (event) {
						var nombreingrediente = $("#ingrediente-datalist").val();
					    var idingrediente = $('#ingrediente-list [value="' +nombreingrediente+ '"]').data('value');
					    var idreceta=$("#idreceta").val();
					    var cantidad=$("#cantidad").val();
						var patron =  /^[0-9]*(\.?)[0-9]+$/;
						//alert("nobreingrediente-->"+nombreingrediente);
						if(patron.test(cantidad) && $.isNumeric(idingrediente)!="" ){
														
					    event.preventDefault();
					    var data = $('#form-receta').serialize();
					    // Añadimos el parámetro que espera el controlador concreto
					    data += '&addItem';
					    data+='&idreceta='+idreceta+'&idproducto='+idingrediente+'&cantidad='+cantidad+'&nombre='+nombreingrediente;					  
					    $.post('/receta/nuevo/submit', data, replaceItems);
					}else{
						alert("Debe introducir ingrediente y cantidad");
					}
			});
					

			
});

function isFloat(x) { return !!(x % 1); }

function replaceItems (html) {
   				 $('#listaingredientes').replaceWith($(html));
			}

function remove_element(id,idprimarias) {
	var idreceta=$("#idreceta").val();
	var data = $('#form-receta').serialize();
    // Añadimos el parámetro que espera el controlador concreto
    data += '&removeItem=' + id;
    $.post('/receta/nuevo/submit', data, replaceItems);
}

			
			