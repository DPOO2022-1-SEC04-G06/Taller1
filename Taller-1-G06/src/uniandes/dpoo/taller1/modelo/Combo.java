// 
// Decompiled by Procyon v0.5.36
// 

package uniandes.dpoo.taller1.modelo;

import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;

public class Combo
{
    private double descuento;
    private String nombreCombo;
    private List<ProductoMenu> itemsCombo;
    
    public Combo(final double descuento, final String nombreCombo) {
        this.descuento = descuento;
        this.nombreCombo = nombreCombo;
        this.itemsCombo = new ArrayList<ProductoMenu>();
    }
    
    public void agregarItemACombo(final Producto itemCombo) {
        this.itemsCombo.add((ProductoMenu)itemCombo);
    }
    
    public int getPrecio() {
        int sum = 0;
        for (final ProductoMenu pr : this.itemsCombo) {
            sum += pr.getPrecio();
        }
        return (int)(sum * this.descuento);
    }
    
    public String generarTextoFactura() {
        return "";
    }
    
    public String getNombre() {
        return this.nombreCombo;
    }
}
