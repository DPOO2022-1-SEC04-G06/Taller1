// 
// Decompiled by Procyon v0.5.36
// 

package uniandes.dpoo.taller1.vista;

import java.util.Scanner;
import java.io.File;
import uniandes.dpoo.taller1.modelo.Restaurante;

public class Aplicacion
{
    private Restaurante cont;
    
    public Aplicacion() {
        this.cont = new Restaurante();
    }
    
    public static void main(final String[] args) {
        final Aplicacion main = new Aplicacion();
        main.mostrarMenu();
        final int choosen = inputINT("Ingrese la opcion elegida");
        main.ejecutarOpcion(choosen);
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
    
    
    public void ejecutarOpcion(final int choosen) {
        switch (choosen) {
            case 0: {
                final File fIngrediente = new File("./data/ingredientes.txt");
                final File fMenu = new File("./data/menu.txt");
                final File fCombo = new File("./data/combos.txt");
                final File fBebida = new File("./data/bebidas.txt");
                this.cont.cargarInformacionRestaurante(fIngrediente, fMenu, fCombo, fBebida);
                break;
            }
            case 2: {
                final String nameCliente = inputSTR("Ingrese el nombre del cliente");
                final String dirCliente = inputSTR("Ingrese la direccion del cliente");
                this.cont.iniciarPedido(nameCliente, dirCliente);
                break;
            }
            case 3: {
                this.cont.cerrarYGuardarPedido();
                break;
            }
            case 4: {
                final int id = inputINT("Ingrese el id");
                final String info = this.cont.consultarId(id);
                System.out.println(info);
                break;
            }
        }
    }
    
    private static int inputINT(final String s) {
        System.out.println(s);
        final Scanner scan = new Scanner(System.in);
        final int toRet = scan.nextInt();
        scan.close();
        return toRet;
    }
    
    private static String inputSTR(final String s) {
        System.out.println(s);
        final Scanner scan = new Scanner(System.in);
        final String toRet = scan.next();
        scan.close();
        return toRet;
    }
}
