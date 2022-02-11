// 
// Decompiled by Procyon v0.5.36
// 

package uniandes.dpoo.taller1.modelo;

public class Ingrediente
{
    private String nombre;
    private int costoAdicional;
    
    public Ingrediente(final String nombre, final int costoAdicional) {
        this.nombre = nombre;
        this.costoAdicional = costoAdicional;
    }
    
    public String getNombre() {
        return this.nombre;
    }
    
    public int getCostoAdicional() {
        return this.costoAdicional;
    }
}
