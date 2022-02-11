package uniandes.dpoo.taller1.modelo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
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
    
    public void cargarInformacionRestaurante(File fIngrediente, File fMenu, File fCombo, File fBebida) {
        this.cargarIngredientes(fIngrediente);
        this.cargarMenu(fMenu);
        this.cargarCombo(fCombo);
    }
    
    private void cargarCombo(File fCombo) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(fCombo));
            String line = br.readLine();
            while (line != null) {
                String[] info = line.split(";");
                String name = info[0];
                String dcto = info[1].replace("%", "");
                double disc = Double.parseDouble(dcto) * 0.01;
                Combo comb = new Combo(disc, name);
                for (int i = 2; i < info.length; ++i) {
                    ProductoMenu item = this.findProduct(info[i]);
                    comb.agregarItemACombo((Producto)item);
                }
                this.combos.add(comb);
                line = br.readLine();
            }
            br.close();
        }
        catch (IOException e) {
            System.err.println("Error con el archivo brother");
            e.printStackTrace();
        }
    }
    
    private ProductoMenu findProduct(String productName) {
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
    
    private void cargarMenu(File fMenu) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(fMenu));
            String line = br.readLine();
            while (line != null) {
                String[] info = line.split(";");
                ProductoMenu pm = new ProductoMenu(info[0], Integer.parseInt(info[1]));
                this.menuBase.add(pm);
                line = br.readLine();
            }
            br.close();
        }
        catch (IOException e) {
            System.err.println("Error con el archivo brother");
        }
    }
    
    private void cargarIngredientes(File fIngrediente) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(fIngrediente));
            String line = br.readLine();
            while (line != null) {
                String[] info = line.split(";");
                Ingrediente ing = new Ingrediente(info[0], Integer.parseInt(info[1]));
                this.ingredientes.add(ing);
                line = br.readLine();
            }
            br.close();
        }
        catch (IOException e) {
            System.err.println("Error con el archivo brother");
        }
    }
    
    public void iniciarPedido(String nameCliente, String dirCliente) {
        this.pedidoEnCurso = new Pedido(nameCliente, dirCliente);
    }
    
    public void cerrarYGuardarPedido() {
        this.pedidos.add(this.pedidoEnCurso);
        this.pedidoEnCurso = null;
    }
    
    public String consultarId(int id) {
        for (Pedido item : this.pedidos) {
            if (item.getIdPedido() == id) {
                return item.toString();
            }
        }
        return "NaN-No Existe";
    }
    
    
}
