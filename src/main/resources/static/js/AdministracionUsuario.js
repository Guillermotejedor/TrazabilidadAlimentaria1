$(document).ready(function() {
			//Desactivar producto
			$('#delete-modal').on('show.bs.modal', function(event) {
				var button = $(event.relatedTarget);
				var data = button.data('id');
				var nombre = button.data('nombre');
				var modal = $(this);
				var a = modal.find('.modal-body a')[0];
				a.href = '/EliminarUsuario/' + data + '/eliminar';
				modal.find('.modal-title').text('Va a eliminar ' + nombre);
			});
			
			});