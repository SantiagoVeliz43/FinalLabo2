import java.util.Scanner;

public class Menus 
{
	
	public static void MenuGen()
	{
		Productos.leerArchivoArrayListProductos();
		Regaleria.leerArchivoRegaleria();
		Lacteos.leerArchivoLacteos();
		Personal.leerArchivoPersonal();

		Scanner scanner = new Scanner(System.in);
		int opcion = 0;
		do 
		{
			System.out.println("°-°-°-°-°-°-°-°| Bienvenido al programa |°-°-°-°-°-°-°-°");
			System.out.println("°Acciones°");
			System.out.println("°1) Ver productos");
			System.out.println("°2) Agregar producto");
			System.out.println("°3) Modificar producto");
			System.out.println("°4) Area de personal");
			System.out.println("°5) Cerrar programa");
			System.out.println("°Ingrese numero de accion: ");
			int ing = Excepciones.verificarNumero(scanner);
		
			switch (ing) 
			{
				case 1:
				do 
				{
					scanner.nextLine();
					System.out.println("°Ver Productos°");
					System.out.println("°1) Busqueda de producto rapida");
        			System.out.println("°2) Ver novedad de Regaleria");
        			System.out.println("°3) Ver Lacteo proximo a vencer");
        			System.out.println("°4) Ver Lista completa de productos");
        			System.out.println("°Ingrece una accion: ");
       				ing = Excepciones.verificarNumero(scanner);
      			
        			switch (ing) 
        			{
                		case 1:
                			scanner.nextLine();
                			String codigoBuscar = Excepciones.verificarCodigoBarras(scanner);
                			Productos.buscarProductoPorCodigo(codigoBuscar);
                		break;
                
                		case 2:
                			Regaleria.verNovedad();
                		break;
                
                		case 3:
                			Lacteos.verLacteoProximo();
                		break;
                
                		case 4:
                			Productos.imprimirArchivoArrayListProductos();
                		break;
                
                		default:
                			System.out.println("°ERRORR: Debe ingresar una opcion valida.");
        			}
        		}	 
				while (ing < 1 || ing > 4);	
				break;
            

				case 2:
				int categoria = 0;
				System.out.println("°Agregar Productos");
				do 
				{
					scanner.nextLine();
					
					System.out.println("°Ingrese la categoría del producto a ingresar (1: REGALERIA, 2: LACTEOS)");
					categoria = Excepciones.verificarNumero(scanner);
					if (categoria == 1) 
					{
						Regaleria.agregarNuevoRegalo();
					} 
					else if (categoria == 2) 
					{
						Lacteos.agregarNuevoLacteo();
					} 
					else 
					{
						System.out.println("°Opción no válida. Por favor ingrese 1 para REGALERIA o 2 para LACTEOS.");
					}
				} 
				while (categoria != 1 && categoria != 2);
				break;
        	

				case 3:
					scanner.nextLine();
					System.out.println("°Modificar Producto");
					System.out.println("°Ingrese la categoría del producto a Modificar (1: REGALERIA, 2: LACTEOS)");
					ing = Excepciones.verificarNumero(scanner);
					do 
					{
						if (ing == 1)
						{
							Regaleria.modificarRegalo();
						} 
						else if (ing == 2) 
						{
							Lacteos.modificarLacteo();
						} 
						else 
						{
							System.out.println("°ERRORR: Debe ingresar una opcion valida.");
						}
					} 
					while (ing != 1 && ing != 2);
        	    break;
        

				case 4:
					do 
					{
						System.out.println("°Area de Personal");
						System.out.println("°1) Agregar Personal");
						System.out.println("°2) Obtener Legajo de Personal");
						System.out.println("°3) Obtener datos de Personal");
						System.out.println("°Ingrece una accion: ");
						ing = Excepciones.verificarNumero(scanner);
        	
						switch (ing) 
						{
        					case 1:
        						Personal.agregarEmpleado();
        					break;
            
        					case 2:
        						Personal.imprimirLegajoPorDNI();
        					break;
            
        					case 3:
        						Personal.mostrarDatosPorLegajo();
        					break;
                
        					default:
        						System.out.println("°ERRORR: Debe ingresar una opcion valida.");
						}
					} 
					while (ing < 1 || ing > 3);
        	    break;
        	    
				case 5:
					System.out.println("°El equipo se autodestruira en 10 segundos. Hasta pronto!");
					System.exit(0);
				break;

				default:
					System.out.println("°ERRORR: Debe ingresar una opcion valida.");
            	break;
			}
		} 
		while (opcion < 1 || opcion > 4);
		scanner.close();
	}	
}
