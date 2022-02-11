// 
// Decompiled by Procyon v0.5.36
// 

package uniandes.dpoo.taller1.modelo;

import java.util.Iterator;
import java.io.IOException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Restaurante
{
    private List<Combo> combos;
    private List<ProductoMenu> menuBase;
    private List<Ingrediente> ingredientes;
    private List<Pedido> pedidos;
    private Pedido pedidoEnCurso;
    
    public Restaurante() {
        this.combos = new ArrayList<Combo>();
        this.menuBase = new ArrayList<ProductoMenu>();
        this.ingredientes = new ArrayList<Ingrediente>();
        this.pedidos = new ArrayList<Pedido>();
    }
    
    public void cargarInformacionRestaurante(final File fIngrediente, final File fMenu, final File fCombo, final File fBebida) {
        this.cargarIngredientes(fIngrediente);
        this.cargarMenu(fMenu);
        this.cargarCombo(fCombo);
    }
    
    private void cargarCombo(final File fCombo) {
        try {
            final BufferedReader br = new BufferedReader(new FileReader(fCombo));
            final String line = br.readLine();
            while (line != null) {
                final String[] info = line.split(";");
                final String name = info[0];
                final String dcto = info[1].replace("%", "");
                final double disc = Double.parseDouble(dcto) * 0.01;
                final Combo comb = new Combo(disc, name);
                for (int i = 2; i < info.length; ++i) {
                    final ProductoMenu item = this.findProduct(info[i]);
                    comb.agregarItemACombo((Producto)item);
                }
                this.combos.add(comb);
            }
            br.close();
        }
        catch (IOException e) {
            System.err.println("Error con el archivo brother");
        }
    }
    
    private ProductoMenu findProduct(final String productName) {
        ProductoMenu toRet = null;
        for (int i = 0; i < this.menuBase.size() && toRet == null; ++i) {
            if (productName.equals(this.menuBase.get(i).getNombre())) {
                toRet = this.menuBase.get(i);
            }
        }
        if (toRet == null) {
            System.err.println("Producto inexistente");
        }
        return toRet;
    }
    
    private void cargarMenu(final File fMenu) {
        try {
            final BufferedReader br = new BufferedReader(new FileReader(fMenu));
            final String line = br.readLine();
            while (line != null) {
                final String[] info = line.split(";");
                final ProductoMenu pm = new ProductoMenu(info[0], Integer.parseInt(info[1]));
                this.menuBase.add(pm);
            }
            br.close();
        }
        catch (IOException e) {
            System.err.println("Error con el archivo brother");
        }
    }
    
    private void cargarIngredientes(final File fIngrediente) {
        try {
            final BufferedReader br = new BufferedReader(new FileReader(fIngrediente));
            final String line = br.readLine();
            while (line != null) {
                final String[] info = line.split(";");
                final Ingrediente ing = new Ingrediente(info[0], Integer.parseInt(info[1]));
                this.ingredientes.add(ing);
            }
            br.close();
        }
        catch (IOException e) {
            System.err.println("Error con el archivo brother");
        }
    }
    
    public void iniciarPedido(final String nameCliente, final String dirCliente) {
        this.pedidoEnCurso = new Pedido(nameCliente, dirCliente);
    }
    
    public void cerrarYGuardarPedido() {
        this.pedidos.add(this.pedidoEnCurso);
        this.pedidoEnCurso = null;
    }
    
    public String consultarId(final int id) {
        for (final Pedido item : this.pedidos) {
            if (item.getIdPedido() == id) {
                return item.toString();
            }
        }
        return "NaN-No Existe";
    }
}
