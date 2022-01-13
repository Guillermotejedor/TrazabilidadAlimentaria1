function Buscar(){
	var lote=document.getElementById("buscar").value;
	var patron =  /^[0-9]+$/;
	if (patron.test(lote) && lote.length==14){
		
		window.location.href="/BuscarTrazabilidad/"+lote;
	
	}else{
		alert("Debe introducir un lote")
	}
	
}