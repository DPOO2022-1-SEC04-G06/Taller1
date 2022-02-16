package uniandes.dpoo.taller1.modelo;

import java.util.Objects;

public class Bebida implements Producto{
	private String nombre;
	private int precioBase;
	private int calorias;

	public Bebida(String nombre, int precioBase, int calorias) {
		this.nombre = nombre;
		this.precioBase = precioBase;
		this.calorias = calorias;
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
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Bebida other = (Bebida) obj;
		return calorias == other.calorias && Objects.equals(nombre, other.nombre) && precioBase == other.precioBase;
	}

}
