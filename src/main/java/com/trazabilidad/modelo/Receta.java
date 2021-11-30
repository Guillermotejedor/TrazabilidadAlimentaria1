package com.trazabilidad.modelo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;

import com.trazabilidad.modelo.*;
import com.trazabilidad.servicios.ProductosPrimariosServicios;



@Entity
@Table(name="recetas")
public class Receta {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_receta")
	private long idreceta;
	@Column(name="nombre_receta")
	private String nombrereceta;
	private int caducidad;
	private boolean activado;
	

	
	
	@ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
	@JoinTable(name = "rel_recetas_primarios",
			joinColumns = @JoinColumn(name = "id_receta"),
			inverseJoinColumns = @JoinColumn(name="Id_Primarias"))
	private List<ProductoPrimario> ingrediente = new ArrayList<ProductoPrimario>();
	  
	@OneToMany(mappedBy = "receta",cascade=CascadeType.DETACH, orphanRemoval=true, fetch=FetchType.EAGER )   
	private List<RelacionRecetaPrimario> cantidadingrediente= new ArrayList<>();
	
	
	//Metodos helper
	
	public void AñadirCantidadIngrediente(RelacionRecetaPrimario cantidad) {
		System.out.println("Tamaño-->"+cantidadingrediente.size());
		this.cantidadingrediente.add(cantidad);
		System.out.println("Tamaño--->"+cantidadingrediente.size());
		cantidad.setReceta(this);
	}
	
	public void EliminarCantidadIngrediente(RelacionRecetaPrimario cantidad) {
		this.cantidadingrediente.remove(cantidad);
		cantidad.setReceta(null);
	}
	
	public Receta() {
		
		super();
	}


	public Receta(String nombrereceta, int caducidad, boolean activado, List<ProductoPrimario> productos,
			List<RelacionRecetaPrimario> relrecetaprimario) {
		super();
		this.nombrereceta = nombrereceta;
		this.caducidad = caducidad;
		this.activado = activado;
		this.ingrediente = productos;
		this.cantidadingrediente = relrecetaprimario;
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

	@Override
	public int hashCode() {
		return Objects.hash(activado, caducidad, idreceta, nombrereceta);
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
		return activado == other.activado && caducidad == other.caducidad && idreceta == other.idreceta
				&& Objects.equals(nombrereceta, other.nombrereceta);
	}
	
	
	
	
}
