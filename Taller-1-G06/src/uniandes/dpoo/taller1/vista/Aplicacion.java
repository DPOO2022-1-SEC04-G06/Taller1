package uniandes.dpoo.taller1.vista;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import uniandes.dpoo.taller1.modelo.Restaurante;

public class Aplicacion {
	private Restaurante cont = new Restaurante();

	public static void main(String[] args) throws IOException {
		Aplicacion main = new Aplicacion();
		while (true) {
			main.mostrarMenu();
			int choosen = Integer.parseInt(input("Ingrese la opcion elegida"));
			main.ejecutarOpcion(choosen);
			if (choosen == 6)
				break;
		}
	}

	public void mostrarMenu() {

		System.out.println("Opciones de la aplicacion");
		System.out.println("1. Mostar el menu");
		System.out.println("2. Iniciar un nuevo pedido");
		System.out.println("3. Agregar un elemento a un pedido");
		System.out.println("4. Cerrar un pedido y guardar la factura");
		System.out.println("5. Consultar la informacion de un pedido dado su id");
		System.out.println("6. Salir de la aplicacion");
	}

	public void ejecutarOpcion(int choosen) throws IOException {
		switch (choosen) {
			case 1: {
				File fIngrediente = new File(path("ingredientes"));
				File fMenu = new File(path("menu"));
				File fCombo = new File(path("combos"));
				File fBebida = new File(path("bebidas"));
				this.cont.cargarInformacionRestaurante(fIngrediente, fMenu, fCombo, fBebida);
	
				System.out.println(cont.allPrint());
				System.out.println("Cargado");
				break;
			}
			case 2: {
				String nameCliente = input("Ingrese el nombre del cliente");
				String dirCliente = input("Ingrese la direccion del cliente");
				this.cont.iniciarPedido(nameCliente, dirCliente);
				break;
			}
			case 3: {
				String nameProducto = input("Ingrese algo del nombre del producto");
				String booleanTo = input("Lo va a modificar?");
				String[] modification = {"",""};
				boolean mod = false;
				
				if("true".equalsIgnoreCase(booleanTo) || "si".equalsIgnoreCase(booleanTo)) {
					modification[0] = input("Ingrese los ingredientes que quiere aniadir(separado por comas)");
					modification[1] = input ("Ingrese los ingredientes que quiere quitar(separado por comas)");
					mod = true;
				}
				this.cont.aniadirProducto(nameProducto, modification, mod);
				break;
			}
			case 4: {
				System.out.println(this.cont.iguales());
				this.cont.cerrarYGuardarPedido();
				break;
			}
			case 5: {
				int id = Integer.parseInt(input("Ingrese el id"));
				String info = this.cont.consultarId(id);
				System.out.println(info);
				break;
			}
		}
	}

	private static String input(String mensaje) {
		try {
			System.out.print(mensaje + ": ");
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			return reader.readLine();
		} catch (IOException e) {
			System.out.println("Error leyendo de la consola");
			e.printStackTrace();
		}
		return null;
	}

	public static String path(String s) {
		return String.format(".%sTaller-1-G06%sdata%s%s.txt", File.separator, File.separator, File.separator, s);
	}
}
