import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public abstract class Productos 
{
	 protected String codigoBarra;
	    protected Categoria codigoCategoria;
	    protected String marca;
	    protected String descripcion;
	    protected double precio;
	    protected int stock;
	    
	    protected static ArrayList<Productos> listaProductos = new ArrayList<>();
	    private static final String ArrayListProductos = "ArrayListProductos";

	    public Productos(String codigoBarra, Categoria codigoCategoria, String marca, String descripcion, double precio, int stock) 
	    {
	        this.codigoBarra = codigoBarra;
	        this.codigoCategoria = codigoCategoria;
	        this.marca = marca;
	        this.descripcion = descripcion;
	        this.precio = precio;
	        this.stock = stock;
	        listaProductos.add(this); 
	    }

		public String getCodigoBarra() {
			return codigoBarra;
		}

		public void setCodigoBarra(String codigoBarra) {
			this.codigoBarra = codigoBarra;
		}

		public Categoria getCodigoCategoria() {
			return codigoCategoria;
		}

		public void setCodigoCategoria(Categoria codigoCategoria) {
			this.codigoCategoria = codigoCategoria;
		}

		public String getMarca() {
			return marca;
		}

		public void setMarca(String marca) {
			this.marca = marca;
		}

		public String getDescripcion() {
			return descripcion;
		}

		public void setDescripcion(String descripcion) {
			this.descripcion = descripcion;
		}

		public double getPrecio() {
			return precio;
		}

		public void setPrecio(double precio) {
			this.precio = precio;
		}

		public int getStock() {
			return stock;
		}

		public void setStock(int stock) {
			this.stock = stock;
		}

		public static ArrayList<Productos> getListaProductos() {
			return listaProductos;
		}

		public static void setListaProductos(ArrayList<Productos> listaProductos) {
			Productos.listaProductos = listaProductos;
		}
	
		
		
		public abstract String obtenerDetalles();
			
		public static void buscarProductoPorCodigo(String codigoBarra) 
		{	
			Scanner scanner = new Scanner(System.in);
		    boolean encontrado = false;
		    for (Productos producto : listaProductos) 
		    {
		        if (producto.getCodigoBarra().equals(codigoBarra)) 
		        {
		        	System.out.println("----------------------");
		            System.out.println("°Producto encontrado:");
		            System.out.println(producto.obtenerDetalles());
		            encontrado = true;
		            break;
		        }
		    }
		    if (!encontrado) 
		    {
		    	System.out.println("----------------------");
		        System.out.println("°Producto no encontrado.");
		        
		    }
		    
		    System.out.println("°Ingrese cualquier caracater para volver al menu");
		    System.out.println("----------------------");
		    String x = scanner.nextLine();
		    Menus.MenuGen();
		}
		
		public static void imprimirArchivoArrayListProductos() 
		{
		    try (BufferedReader br = new BufferedReader(new FileReader("ArrayListProductos.txt"))) 
		    {
		        String linea;
		        while ((linea = br.readLine()) != null) 
		        {
		            if (linea.trim().isEmpty()) 
		            {
		                continue;
		            }
		            String[] datos = linea.split("\\|");
		            if (datos.length == 6) 
		            {
		                System.out.println("----------------------");
		                System.out.println("°Código de Barras: " + datos[0]);
		                System.out.println("°Marca: " + datos[2]);
		                System.out.println("°Descripción: " + datos[3]);
		                System.out.println("°Precio: " + datos[4]);
		                System.out.println("°Stock: " + datos[5]);
		                System.out.println("----------------------");
		            }
		        }
		    } 
		    catch (IOException e) 
		    {
		        e.printStackTrace();
		    }

		    Scanner scanner = new Scanner(System.in);
		    System.out.println("°Ingrese cualquier caracter para volver al menu");
		    System.out.println("----------------------");
		    String x = scanner.nextLine();
		    Menus.MenuGen();
		}
			
		public static void leerArchivoArrayListProductos() 
		{
			listaProductos.clear();
		    try (BufferedReader br = new BufferedReader(new FileReader("ArrayListProductos.txt"))) 
		    {
		        String linea;
		        while ((linea = br.readLine()) != null) 
		        {
		            if (linea.trim().isEmpty()) 
		            {
		                continue;
		            }
		            String[] datos = linea.split("\\|");
		            if (datos.length == 6) 
		            {
		                String codigoBarra = datos[0];
		                Categoria codigoCategoria = Categoria.values()[Integer.parseInt(datos[1]) - 1];
		                String marca = datos[2];
		                String descripcion = datos[3];
		                double precio = Double.parseDouble(datos[4]);
		                int stock = Integer.parseInt(datos[5]);

		                Productos producto = new Productos(codigoBarra, codigoCategoria, marca, descripcion, precio, stock) 
		                {
		                    @Override
		                    public String obtenerDetalles() {
		                        return "Código de Barras: " + getCodigoBarra() + "\n" +
		                               "Marca: " + getMarca() + "\n" +
		                               "Descripción: " + getDescripcion() + "\n" +
		                               "Precio: " + getPrecio() + "\n" +
		                               "Stock: " + getStock();
		                    }
		                };
		                listaProductos.add(producto);
		            }
		        }
		    } 
		    catch (IOException e) 
		    {
		        e.printStackTrace();
		    }
		    System.out.println("°Datos cargados correctamente desde el archivo ArrayListProductos.txt.");
		}			
}
