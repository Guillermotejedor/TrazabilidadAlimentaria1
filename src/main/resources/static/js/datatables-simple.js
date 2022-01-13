window.addEventListener('DOMContentLoaded', event => {
    // Simple-DataTables
    // https://github.com/fiduswriter/Simple-DataTables/wiki
	
    const datatablesSimple = document.getElementById('datatablesSimple');
    if (datatablesSimple) {	
        new simpleDatatables.DataTable(datatablesSimple,{perPageSelect:false,
        labels: {
    			placeholder: "Buscar...",
    			perPage: "{select} entradas por pagina",
   				noRows: "No hay entradas que mostrar",
    			info: "Mostrando {start} a {end} de {rows} entradas",
		}});
         
    }
});
