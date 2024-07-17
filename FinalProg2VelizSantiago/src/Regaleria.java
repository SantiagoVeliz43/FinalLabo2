import java.util.*;
import java.io.*;

public class Regaleria  extends Productos
{
	 private String materialFabricacion;
	 private static final String PilaRegaleria = "PilaRegaleria.txt";
	 private static Stack<Regaleria> pilaRegaleria = new Stack<>();

	    public Regaleria(String codigoBarra, Categoria codigoCategoria, String marca, String descripcion, double precio, int stock, String materialFabricacion) 
	    {
	        super(codigoBarra, codigoCategoria, marca, descripcion, precio, stock);
	        this.materialFabricacion = materialFabricacion;
	        listaProductos.add(this);
	        pilaRegaleria.push(this);
	    }
	    
	    public String getMaterialFabricacion()
	    {
	        return materialFabricacion;
	    }
    
	    public void setMaterialFabricacion(String materialFabricacion) 
	    {
			this.materialFabricacion = materialFabricacion;
		}
		
	    
	    
	    @Override
	    public String obtenerDetalles() 
	    {
	        return "Código de Barras: " + getCodigoBarra() + "\n" + "Marca: " + getMarca() + "\n" + "Descripción: " + getDescripcion() + "\n" + 
	        		"Precio: " + getPrecio() + "\n" + "Stock: " + getStock() + "\n";
	    }
	    

	    public static void verNovedad() 
	    {
	    	  if (!pilaRegaleria.isEmpty()) 
	    	  {
	    	        Regaleria ultimoRegalo = pilaRegaleria.peek();
	    	        System.out.println("----------------------");
	    	        System.out.println("\n" + "°Novedad en regaleria:");
	    	        System.out.println(ultimoRegalo.obtenerDetalles());
	    	  } 
	    	  else 
	    	  {
	    	        System.out.println("°No hay productos de regalería agregados.");
	    	  }
			  Scanner scanner = new Scanner(System.in);
			  System.out.println("°Ingrese cualquier caracater para volver al menu");
			  System.out.println("----------------------");
			  String x = scanner.nextLine();  
			  Menus.MenuGen();
	    }
	          
	    public static void agregarNuevoRegalo() 
	    {
	    	System.out.println("----------------------");
	    	 try 
	    	 {
	    	        Scanner scanner = new Scanner(System.in);
	    	        String codigoBarra = Excepciones.verificarCodigoBarras(scanner);
	    	        
	    	        Categoria codigoCategoria = Categoria.REGALERIA;

	    	        System.out.println("°Ingrese la marca:");
	    	        String marca = Excepciones.verificarCadena(scanner);

	    	        System.out.println("°Ingrese la descripción:");
	    	        String descripcion = Excepciones.verificarCadena(scanner);

	    	        double precio = Excepciones.verificarDouble(scanner);

	    	        System.out.println("°Ingrese el stock:");
	    	        int stock = Excepciones.verificarNumero(scanner);

	    	        scanner.nextLine();
	    	        
	    	        System.out.println("°Ingrese el material de fabricación:");
	    	        String materialFabricacion = Excepciones.verificarCadena(scanner);

	    	        
	    	        Regaleria nuevoRegalo = new Regaleria(codigoBarra, codigoCategoria, marca, descripcion, precio, stock, materialFabricacion);
	    	        
	    	        escribirEnArchivo(nuevoRegalo);
	    	        escribirEnArchivoArrayListProductos(nuevoRegalo);
	    	        
	    	        System.out.println("°Regalo agregado exitosamente.");
	    	 } 
	    	 catch (NumberFormatException e) 
	    	 {
	    	        System.out.println("°ERROR. Intente nuevamente:");
	    	 }
	    	 
		     Scanner scanner = new Scanner(System.in);
			 System.out.println("°Ingrese cualquier caracater para volver al menu");
			 System.out.println("----------------------");
			 String x = scanner.nextLine();
			 Menus.MenuGen();
	    }
	    
	    public static void modificarRegalo()
	    {
	        Scanner scanner = new Scanner(System.in);
	        Regaleria productoModificado = null;
	        System.out.println("----------------------");
	        do 
	        {
	            System.out.println("°Ingrese el código de barras del producto a modificar:");
	            String codigoBarra = Excepciones.verificarCodigoBarras(scanner);

	            for (Regaleria regalo : pilaRegaleria) 
	            {
	                if (regalo.getCodigoBarra().equals(codigoBarra)) 
	                {
	                    productoModificado = regalo;
	                    break;
	                }
	            }

	            if (productoModificado == null) 
	            {
	                System.out.println("°Producto no encontrado. Por favor, intente de nuevo.");
	            }
	            
	        } 
	        while (productoModificado == null);

	        System.out.println("°Detalles actuales del producto:");
	        System.out.println(productoModificado.obtenerDetalles());

	        System.out.println("°Ingrese la nueva marca:");
	        String nuevaMarca = Excepciones.verificarCadena(scanner);
	        productoModificado.setMarca(nuevaMarca);

	        System.out.println("°Ingrese la nueva descripción:");
	        String nuevaDescripcion = Excepciones.verificarCadena(scanner);
	        productoModificado.setDescripcion(nuevaDescripcion);

	        System.out.println("°Ingrese el nuevo precio:");
	        double nuevoPrecio = Excepciones.verificarDouble(scanner);
	        productoModificado.setPrecio(nuevoPrecio);

	        System.out.println("°Ingrese el nuevo stock:");
	        int nuevoStock = Excepciones.verificarNumero(scanner);
	        productoModificado.setStock(nuevoStock);
	        
	        scanner.nextLine();
	        
	        System.out.println("°Ingrese el nuevo material de fabricación:");
	        String nuevoMaterialFabricacion = Excepciones.verificarCadena(scanner);
	        productoModificado.setMaterialFabricacion(nuevoMaterialFabricacion);

	        actualizarPila();

	        actualizarArchivo();

	        System.out.println("°Producto modificado exitosamente.");
	        System.out.println("°Ingrese cualquier carácter para volver al menú");
	        System.out.println("----------------------");
	        String x = scanner.nextLine();
	        Menus.MenuGen();
	    }

	    
	    
	    private static void actualizarPila() 
	    {
	    	Stack<Regaleria> tempPila = new Stack<>();

	        for (Regaleria regalo : pilaRegaleria) 
	        {
	        	tempPila.push(regalo);
	        }

	        pilaRegaleria = tempPila;
	        System.out.println("Pila actualizada correctamente.");
	    }
	    
	    private static void actualizarArchivo() 
	    {
	        try {
	            FileWriter writer = new FileWriter(PilaRegaleria);
	            PrintWriter printer = new PrintWriter(writer);

	            for (Regaleria regalo : pilaRegaleria) 
	            {
	                printer.println(regalo.getCodigoBarra() + "|" +
	                                regalo.getCodigoCategoria().getCodigo() + "|" +
	                                regalo.getMarca() + "|" +
	                                regalo.getDescripcion() + "|" +
	                                regalo.getPrecio() + "|" +
	                                regalo.getStock() + "|" +
	                                regalo.getMaterialFabricacion());
	                printer.println();
	            }
	            printer.close();
	            System.out.println("Archivo actualizado correctamente.");
	        } 
	        catch (IOException e)
	        {
	            System.out.println("Error al actualizar el archivo: " + e.getMessage());
	        }
	    }

	    private static void escribirEnArchivo(Regaleria regalo) 
	    {
	    	try (FileWriter fw = new FileWriter(PilaRegaleria, true);
	                BufferedWriter bw = new BufferedWriter(fw);
	                PrintWriter out = new PrintWriter(bw)) 
	    	{
	               out.println(
	                   regalo.getCodigoBarra() + "|" +
	                   regalo.getCodigoCategoria().getCodigo() + "|" +
	                   regalo.getMarca() + "|" +
	                   regalo.getDescripcion() + "|" +
	                   regalo.getPrecio() + "|" +
	                   regalo.getStock() + "|" +
	                   regalo.getMaterialFabricacion() +"\n"
	            );
	        } 
	    	catch (IOException e) 
	    	{
	            e.printStackTrace();
	        }
	    }
	    
	    public static void leerArchivoRegaleria() 
	    {
	    	  try (BufferedReader br = new BufferedReader(new FileReader("PilaRegaleria.txt"))) 
	    	  {
	    	        String linea;
	    	        while ((linea = br.readLine()) != null) 
	    	        {
	    	            if (linea.trim().isEmpty()) 
	    	            {
	    	                continue;
	    	            }
	    	            String[] datos = linea.split("\\|");
	    	            if (datos.length == 7) {
	    	                String codigoBarra = datos[0];
	    	                Categoria codigoCategoria = Categoria.values()[Integer.parseInt(datos[1]) - 1];
	    	                String marca = datos[2];
	    	                String descripcion = datos[3];
	    	                double precio = Double.parseDouble(datos[4]);
	    	                int stock = Integer.parseInt(datos[5]);
	    	                String materialFabricacion = datos[6];

	    	                Regaleria regalo = new Regaleria(codigoBarra, codigoCategoria, marca, descripcion, precio, stock, materialFabricacion);
	    	                pilaRegaleria.add(regalo);
	    	            }
	    	        }
	            System.out.println("°Datos cargados correctamente desde el archivo PilaRegaleria.txt.");
	        } 
	        catch (IOException e) 
	        {
	            e.printStackTrace();
	        }
	    }
	    
	    public static void escribirEnArchivoArrayListProductos(Productos producto) 
	    {
	        try (BufferedWriter bw = new BufferedWriter(new FileWriter("ArrayListProductos.txt", true)))
	        {
	            bw.write(producto.getCodigoBarra() + "|" +
	                     (producto.getCodigoCategoria().ordinal() + 1) + "|" +
	                     producto.getMarca() + "|" +
	                     producto.getDescripcion() + "|" +
	                     producto.getPrecio() + "|" +
	                     producto.getStock());
	            bw.newLine();
	            bw.newLine(); 
	        } 
	        catch (IOException e) 
	        {
	            e.printStackTrace();
	        }
	    }
	    
	    
	            
	   
	       
	    
	    
	    
}
