package uniandes.dpoo.taller1.modelo;

import java.util.ArrayList;
import java.util.List;

public class ProductoAjustado implements Producto {
	private List<Ingrediente> agregados;
	private List<Ingrediente> eliminados;
	private ProductoMenu base;

	public ProductoAjustado(ProductoMenu pBase) {
		this.agregados = new ArrayList<Ingrediente>();
		this.eliminados = new ArrayList<Ingrediente>();
		this.base = pBase;
	}

	public int getPrecio() {
		int sumAdded = 0;
		for (int i = 0; i < this.agregados.size(); ++i) {
			sumAdded += this.agregados.get(i).getCostoAdicional();
		}
		return this.base.getPrecio() + sumAdded;
	}

	public String getNombre() {
		return String.valueOf(this.base.getNombre()) + "*";
	}

	public String generarTextoFactura() {
		String itemsPlus = "", itemsMin = "";
		for (Ingrediente item : agregados) {
			itemsPlus += item.getNombre() + ", ";
		}
		for (Ingrediente item : eliminados) {
			itemsMin += item.getNombre() + ", ";
		}
		return "El producto " + base.getNombre() + "con " + itemsPlus.substring(0, itemsPlus.length() - 2) + "sin "
				+ itemsMin.substring(0, itemsMin.length() - 2);
	}
	
	public void modifyAdd(Ingrediente item) {
		agregados.add(item);
	}
	
	public void modifySub(Ingrediente item) {
		eliminados.add(item);
	}
}
