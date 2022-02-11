package uniandes.dpoo.taller1.modelo;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Pedido
{
    private static int numeroPedidos;
    private int idPedido;
    private String nombreCliente;
    private String direccionCliente;
    private List<Producto> itemsPedido;
    
    public Pedido(String nombreCliente, String direccionCliente) {
        this.nombreCliente = nombreCliente;
        this.direccionCliente = direccionCliente;
        this.itemsPedido = new ArrayList<Producto>();
        ++Pedido.numeroPedidos;
        this.idPedido = Pedido.numeroPedidos;
    }
    
    public int getIdPedido() {
        return this.idPedido;
    }
    
    public void agregarProducto(Producto nuevoItem) {
        this.itemsPedido.add(nuevoItem);
    }
    
    private int getPrecioNetoPedido() {
        return 0;
    }
    
    private int getPrecioTotalPedido() {
        return 0;
    }
    
    private int getPrecioIVAPedido() {
        return 0;
    }
    
    private String generarTextoFactura() {
        return "";
    }
    
    public void guardarFactura(File arc) {
    }
}
