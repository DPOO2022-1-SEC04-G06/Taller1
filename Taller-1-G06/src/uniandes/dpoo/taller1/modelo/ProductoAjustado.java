package uniandes.dpoo.taller1.modelo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

public class ProductoAjustado implements Producto {
	private List<Ingrediente> agregados;
	private List<Ingrediente> eliminados;
	private ProductoMenu base;

	public ProductoAjustado(ProductoMenu pBase) {
		this.agregados = new ArrayList<Ingrediente>();
		this.eliminados = new ArrayList<Ingrediente>();
		this.base = pBase;
	}
	
	@Override
	public int getPrecio() {
		int sumAdded = 0;
		for (int i = 0; i < this.agregados.size(); ++i) {
			sumAdded += this.agregados.get(i).getCostoAdicional();
		}
		return this.base.getPrecio() + sumAdded;
	}

	@Override
	public String getNombre() {
		return String.valueOf(this.base.getNombre()) + "*";
	}

	@Override
	public String generarTextoFactura() {
		String itemsPlus = "", itemsMin = "";
		for (Ingrediente item : agregados) {
			itemsPlus += item.getNombre() + ", ";
		}
		for (Ingrediente item : eliminados) {
			itemsMin += item.getNombre() + ", ";
		}
		return "El producto " + base.getNombre() + " con " + itemsPlus.substring(0, itemsPlus.length() - 2) + " sin "
				+ itemsMin.substring(0, itemsMin.length() - 2) + "(Tiene " + getCalorias() + " calorias)";
	}
	
	public void modifyAdd(Ingrediente item) {
		agregados.add(item);
	}
	
	public void modifySub(Ingrediente item) {
		eliminados.add(item);
	}

	@Override
	public int getCalorias() {
		return this.base.getCalorias();
	}

	@Override
	public int hashCode() {
		return Objects.hash(agregados, base, eliminados);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductoAjustado other = (ProductoAjustado) obj;
		
		HashSet<Ingrediente> add1 = new HashSet<Ingrediente>(agregados);
		HashSet<Ingrediente> add2 = new HashSet<Ingrediente>(other.agregados);
		HashSet<Ingrediente> sub1 = new HashSet<Ingrediente>(eliminados);
		HashSet<Ingrediente> sub2 = new HashSet<Ingrediente>(other.eliminados);
		
		return add1.equals(add2) && Objects.equals(base, other.base)
				&& sub1.equals(sub2);
	}
	
	
}
