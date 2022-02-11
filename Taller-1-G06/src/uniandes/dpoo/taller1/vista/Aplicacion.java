package uniandes.dpoo.taller1.vista;

import java.util.Scanner;
import java.io.File;
import java.io.IOException;

import uniandes.dpoo.taller1.modelo.Restaurante;

public class Aplicacion
{
    private Restaurante cont;
    
    public Aplicacion() {
        this.cont = new Restaurante();
    }
    
    public static void main(String[] args) throws IOException {    	
        Aplicacion main = new Aplicacion();
        main.mostrarMenu();
        int choosen = inputINT("Ingrese la opcion elegida");
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
    
    
    public void ejecutarOpcion(int choosen) {
        switch (choosen) {
            case 1: {
                File fIngrediente = new File(path("ingredientes"));
                File fMenu = new File(path("menu"));
                File fCombo = new File(path("combos"));
                File fBebida = new File(path("bebidas"));
                this.cont.cargarInformacionRestaurante(fIngrediente, fMenu, fCombo, fBebida);
                System.out.println(cont.toString());
                System.out.println("Cargado");
                break;
            }
            case 2: {
                String nameCliente = inputSTR("Ingrese el nombre del cliente");
                String dirCliente = inputSTR("Ingrese la direccion del cliente");
                this.cont.iniciarPedido(nameCliente, dirCliente);
                break;
            }
            case 3: {
                this.cont.cerrarYGuardarPedido();
                break;
            }
            case 4: {
                int id = inputINT("Ingrese el id");
                String info = this.cont.consultarId(id);
                System.out.println(info);
                break;
            }
        }
    }
    
    private static int inputINT(String s) {
        System.out.println(s);
        Scanner scan = new Scanner(System.in);
        int toRet = scan.nextInt();
        scan.close();
        return toRet;
    }
    
    private static String inputSTR(String s) {
        System.out.println(s);
        Scanner scan = new Scanner(System.in);
        String toRet = scan.next();
        scan.close();
        return toRet;
    }
    
    private static String path(String s) {
    	return String.format(".%sTaller-1-G06%sdata%s%s.txt", File.separator,File.separator, File.separator, s);
    }
}
