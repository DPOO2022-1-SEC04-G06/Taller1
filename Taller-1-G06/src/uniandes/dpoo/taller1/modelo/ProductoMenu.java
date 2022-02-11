package uniandes.dpoo.taller1.modelo;

import java.util.Objects;

public class ProductoMenu implements Producto {
	private String nombre;
	private int precioBase;
	private int calorias;

	public ProductoMenu(String nombre, int precioBase) {
		this.nombre = nombre;
		this.precioBase = precioBase;
	}
	
	@Override
	public int getPrecio() {
		return this.precioBase;
	}

	@Override
	public String getNombre() {
		return this.nombre;
	}

	@Override
	public String generarTextoFactura() {
		return "El producto " + getNombre() + " tiene un precio de " + getPrecio() + "(Tiene " + getCalorias() + " calorias)";
	}

	@Override
	public int getCalorias() {
		return calorias;
	}

	@Override
	public int hashCode() {
		return Objects.hash(calorias, nombre, precioBase);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductoMenu other = (ProductoMenu) obj;
		return calorias == other.calorias && Objects.equals(nombre, other.nombre) && precioBase == other.precioBase;
	}
	
	
}
