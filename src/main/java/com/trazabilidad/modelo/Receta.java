package com.trazabilidad.modelo;

import java.util.ArrayList;

import java.util.List;
import java.util.Objects;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;





@Entity
@Table(name="recetas")
public class Receta {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_receta")
	private long idreceta;
	@Column(name="nombre_receta")
	@NotNull
	@Size(min=2,message="Especifique el Nombre de la receta")
	private String nombrereceta;
	@Min(value=1,message="{Receta.cantidad}")
	private int caducidad;
	private boolean activado;
	@Column(name="id_productobase")
	@Min(value=1,message="Debe añadir ingredientes y seleccionar producto base")
	private long productobase;
	
	
	@ManyToMany
	@JoinTable(name = "rel_recetas_primarios",
			joinColumns = @JoinColumn(name = "id_receta"),
			inverseJoinColumns = @JoinColumn(name="Id_Primarias"))
	private List<ProductoPrimario> ingrediente = new ArrayList<ProductoPrimario>();
	 
	@JoinColumn(name="id_receta",referencedColumnName="id_receta",insertable=false,updatable=false)
	@OneToMany( cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
	private List<RelacionRecetaPrimario> cantidadingrediente= new ArrayList<RelacionRecetaPrimario>();
	//
	@JoinColumn(name="id_receta",referencedColumnName="id_receta",insertable=false,updatable=false)
	//@Where(clause ="cantidad_producida=cantidad_distribuida")
	@OneToMany( cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
	@OrderBy("lote DESC")
	private List<Lote> lotes= new ArrayList<Lote>();
	
	//Metodos helper
	
	public void AñadirCantidadIngrediente(RelacionRecetaPrimario cantidad) {
		this.cantidadingrediente.add(cantidad);
		//cantidad.setReceta(this);
	}
	
	public void EliminarCantidadIngrediente(RelacionRecetaPrimario cantidad) {
		this.cantidadingrediente.remove(cantidad);
		//cantidad.setReceta(null);
	}
	
	public Receta() {
		
		super();
	}



	public Receta(@NotNull @Size(min = 2, message = "Especifique el Nombre de la receta") String nombrereceta,
			@Min(value = 1, message = "{Receta.cantidad}") int caducidad, boolean activado, 
			long productobase, List<ProductoPrimario> ingrediente, List<RelacionRecetaPrimario> cantidadingrediente) {
		super();
		this.nombrereceta = nombrereceta;
		this.caducidad = caducidad;
		this.activado = activado;
		this.productobase = productobase;
		this.ingrediente = ingrediente;
		this.cantidadingrediente = cantidadingrediente;
	}

	public long getIdreceta() {
		return idreceta;
	}

	public void setIdreceta(long id) {
		this.idreceta = id;
	}

	public String getNombrereceta() {
		return nombrereceta;
	}

	public void setNombrereceta(String nombrereceta) {
		this.nombrereceta = nombrereceta;
	}

	public int getCaducidad() {
		return caducidad;
	}

	public void setCaducidad(int caducidad) {
		this.caducidad = caducidad;
	}

	public boolean isActivado() {
		return activado;
	}

	public void setActivado(boolean activado) {
		this.activado = activado;
	}

	public List<ProductoPrimario> getIngrediente() {
		return ingrediente;
	}



	public void setIngrediente(List<ProductoPrimario> productos) {
		this.ingrediente = productos;
	}


	public List<RelacionRecetaPrimario> getCantidadingrediente() {
		return cantidadingrediente;
	}

	public void setCantidadingrediente(List<RelacionRecetaPrimario> cantidadingrediente) {
		this.cantidadingrediente = cantidadingrediente;
	}



	public long getProductobase() {
		return productobase;
	}

	public void setProductobase(long productobase) {
		this.productobase = productobase;
	}
	
	

	public List<Lote> getLotes() {
		return lotes;
	}

	public void setLotes(List<Lote> lotes) {
		this.lotes = lotes;
	}

	@Override
	public int hashCode() {
		return Objects.hash(activado, caducidad, cantidadingrediente,  idreceta, ingrediente,
				nombrereceta, productobase);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Receta other = (Receta) obj;
		return activado == other.activado && caducidad == other.caducidad
				&& Objects.equals(cantidadingrediente, other.cantidadingrediente)
				&& idreceta == other.idreceta
				&& Objects.equals(ingrediente, other.ingrediente) && Objects.equals(nombrereceta, other.nombrereceta)
				&& Objects.equals(productobase, other.productobase);
	}

	
	

	
	
	
	
}
