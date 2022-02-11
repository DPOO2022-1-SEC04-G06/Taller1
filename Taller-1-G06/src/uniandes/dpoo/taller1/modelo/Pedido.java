package uniandes.dpoo.taller1.modelo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Pedido {
	private static int numeroPedidos;
	private int idPedido;
	private String nombreCliente;
	private String direccionCliente;
	private List<Producto> itemsPedido;

	private static final double IVA = 0.19;

	public Pedido(String nombreCliente, String direccionCliente) {
		this.nombreCliente = nombreCliente;
		this.direccionCliente = direccionCliente;
		this.itemsPedido = new ArrayList<Producto>();
		Pedido.numeroPedidos++;
		this.idPedido = Pedido.numeroPedidos;
	}

	public int getIdPedido() {
		return this.idPedido;
	}

	public void agregarProducto(Producto nuevoItem) {
		this.itemsPedido.add(nuevoItem);
	}

	private int getPrecioNetoPedido() {
		int sum = 0;
		for (Producto p : itemsPedido) {
			sum += p.getPrecio();
		}
		return sum;
	}

	private int getPrecioTotalPedido() {
		return getPrecioNetoPedido() + getPrecioIVAPedido();
	}

	private int getPrecioIVAPedido() {
		return (int) (getPrecioNetoPedido() * IVA);
	}

	private String generarTextoFactura() {
		String toRet = nombreCliente + ", " + direccionCliente + ": ";
		for (Producto p : itemsPedido) {
			toRet += p.generarTextoFactura() + ", ";
		}
		return toRet.substring(0, toRet.length() - 2) + " debe pagar " + getPrecioTotalPedido();
	}

	public void guardarFactura(File arc) throws IOException {
		BufferedWriter bw = new BufferedWriter(new FileWriter(arc, true));
		bw.append(generarTextoFactura());
		bw.close();
	}
}
