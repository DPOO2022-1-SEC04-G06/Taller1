package uniandes.dpoo.taller1.modelo;

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
}
