package uniandes.dpoo.taller1.modelo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import uniandes.dpoo.taller1.vista.Aplicacion;

public class Restaurante {
	private List<Combo> combos;
	private List<ProductoMenu> menuBase;
	private List<Ingrediente> ingredientes;
	private List<Pedido> pedidos;
	private List<Bebida> bebidas;
	private Pedido pedidoEnCurso;

	public Restaurante() {
		this.combos = new ArrayList<Combo>();
		this.menuBase = new ArrayList<ProductoMenu>();
		this.ingredientes = new ArrayList<Ingrediente>();
		this.pedidos = new ArrayList<Pedido>();
		this.bebidas = new ArrayList<Bebida>();
	}

	public void cargarInformacionRestaurante(File fIngrediente, File fMenu, File fCombo, File fBebida)
			throws IOException {
		this.cargarIngredientes(fIngrediente);
		this.cargarMenu(fMenu);
		this.cargarBebida(fBebida);
		this.cargarCombo(fCombo);
		
	}

	private void cargarBebida(File fBebida) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(fBebida));
		String line = br.readLine();
		while (line != null) {
			String[] info = line.split(";");
			Bebida beb = new Bebida(info[0], Integer.parseInt(info[1]), Integer.parseInt(info[2]));
			this.bebidas.add(beb);
			line = br.readLine();
		}
		br.close();
	}

	private void cargarMenu(File fMenu) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(fMenu));
		String line = br.readLine();
		while (line != null) {
			String[] info = line.split(";");
			ProductoMenu pm = new ProductoMenu(info[0], Integer.parseInt(info[1]), Integer.parseInt(info[2]));
			this.menuBase.add(pm);
			line = br.readLine();
		}
		br.close();
	}

	private void cargarCombo(File fCombo) throws IOException {
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
				comb.agregarItemACombo((Producto) item);
			}
			this.combos.add(comb);
			line = br.readLine();
		}
		br.close();
	}

	private void cargarIngredientes(File fIngrediente) throws IOException {
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

	public void iniciarPedido(String nameCliente, String dirCliente) throws IOException {
		if (pedidoEnCurso != null) {
			cerrarYGuardarPedido();
		}
		this.pedidoEnCurso = new Pedido(nameCliente, dirCliente);
	}

	public void cerrarYGuardarPedido() throws IOException {
		this.pedidos.add(this.pedidoEnCurso);
		this.pedidoEnCurso.guardarFactura(new File(Aplicacion.path("facturas")));
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

	public List<Combo> getCombos() {
		return combos;
	}

	public List<ProductoMenu> getMenuBase() {
		return menuBase;
	}

	public List<Ingrediente> getIngredientes() {
		return ingredientes;
	}

	public Pedido getPedidoEnCurso() {
		return pedidoEnCurso;
	}

	public String allPrint() {
		String basic = "=============Combos==============\n";
		for (Combo c : combos) {
			basic += c.getNombre() + ">>" + c.getPrecio() + "\n";
		}
		basic += "=================================\n\n";
		basic += "=============Productos==============\n";
		for (ProductoMenu p : menuBase) {
			basic += p.getNombre() + ">>" + p.getPrecio() + "\n";
		}
		basic += "====================================";
		basic += "==============Bebidas===============\n";
		for (Bebida b : bebidas) {
			basic += b.getNombre() +  ">>" + b.getPrecio() + "\n";
		}
		return basic;
	}

	public void aniadirProducto(String nameProducto, String[] modification, boolean mod) {
		ProductoMenu buscarProducto = buscarP(nameProducto);
		Combo buscarCombo = null;
		if (buscarProducto == null) {
			buscarCombo = buscarC(nameProducto);
			this.pedidoEnCurso.agregarProducto(buscarCombo);
		} else {
			if (mod) {
				ProductoAjustado pA = new ProductoAjustado(buscarProducto);
				String[] adder = modification[0].split(",");
				String[] subber = modification[1].split(",");
				for (String ingrediente : adder) {
					Ingrediente buscarIngre = buscarI(ingrediente);
					pA.modifyAdd(buscarIngre);
				}
				for (String ingrediente : subber) {
					Ingrediente buscarIngre = buscarI(ingrediente);
					pA.modifySub(buscarIngre);
				}
				this.pedidoEnCurso.agregarProducto(pA);
			} else {
				this.pedidoEnCurso.agregarProducto(buscarProducto);
			}
		}
	}

	private ProductoMenu buscarP(String nameProducto) {
		for (ProductoMenu p : menuBase) {
			if (p.getNombre().contains(nameProducto)) {
				return p;
			}
		}
		return null;
	}

	private Combo buscarC(String nameCombo) {
		for (Combo c : combos) {
			if (c.getNombre().contains(nameCombo)) {
				return c;
			}
		}
		return null;
	}

	private Ingrediente buscarI(String nameIngre) {
		for (Ingrediente i : ingredientes) {
			if (i.getNombre().contains(nameIngre)) {
				return i;
			}
		}
		return null;
	}

	public String iguales() {
		for(Pedido item : pedidos) {
			if (this.pedidoEnCurso.equals(item)) {
				return "Se encontro dos pedidos iguales: El pedido " + pedidoEnCurso.getIdPedido()
						+ " y el pedido " + item.getIdPedido();
			}
		}
		return "No hay pedidos iguales";
		
	}

}
