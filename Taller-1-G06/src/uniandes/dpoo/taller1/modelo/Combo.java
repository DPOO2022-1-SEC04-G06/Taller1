package uniandes.dpoo.taller1.modelo;

import java.util.ArrayList;
import java.util.List;

public class Combo implements Producto{
	private double descuento;
	private String nombreCombo;
	private List<ProductoMenu> itemsCombo;

	public Combo(double descuento, String nombreCombo) {
		this.descuento = descuento;
		this.nombreCombo = nombreCombo;
		this.itemsCombo = new ArrayList<ProductoMenu>();
	}

	public void agregarItemACombo(Producto itemCombo) {
		this.itemsCombo.add((ProductoMenu) itemCombo);
	}

	public int getPrecio() {
		int sum = 0;
		for (ProductoMenu pr : this.itemsCombo) {
			sum += pr.getPrecio();
		}
		return (int) (sum * this.descuento);
	}

	public String generarTextoFactura() {
		String items = "";
		for (ProductoMenu item : itemsCombo) {
			items += item.generarTextoFactura() + ", ";
		}
		return "El combo " + getNombre() + " tiene " + items.substring(0, items.length() - 2) + " con un descuento de "
				+ descuento + " debes pagar " + getPrecio();
	}

	public String getNombre() {
		return this.nombreCombo;
	}
}
