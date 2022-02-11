// 
// Decompiled by Procyon v0.5.36
// 

package uniandes.dpoo.taller1.modelo;

import java.util.ArrayList;
import java.util.List;

public class ProductoAjustado implements Producto
{
    private List<Ingrediente> agregados;
    private List<Ingrediente> eliminados;
    private ProductoMenu base;
    
    public ProductoAjustado(final ProductoMenu pBase) {
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
        return null;
    }
}
