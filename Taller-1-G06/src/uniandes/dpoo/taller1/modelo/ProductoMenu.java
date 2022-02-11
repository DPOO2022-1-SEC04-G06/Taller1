// 
// Decompiled by Procyon v0.5.36
// 

package uniandes.dpoo.taller1.modelo;

public class ProductoMenu implements Producto
{
    private String nombre;
    private int precioBase;
    
    public ProductoMenu(final String nombre, final int precioBase) {
        this.nombre = nombre;
        this.precioBase = precioBase;
    }
    
    public int getPrecio() {
        return this.precioBase;
    }
    
    public String getNombre() {
        return this.nombre;
    }
    
    public String generarTextoFactura() {
        return null;
    }
}
