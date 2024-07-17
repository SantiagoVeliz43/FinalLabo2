import java.text.*;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.io.*;

public class Lacteos extends Productos implements Fecha
{
    private Date fechaFabricacion;
    private Date fechaVencimiento;
    private static final String ColaLacteos = "ColaLacteos.txt";
    private static Queue<Lacteos> colaLacteos = new LinkedList<>();
    
    public Lacteos(String codigoBarra, Categoria codigoCategoria, String marca, String descripcion, double precio, int stock, Date fechaFabricacion, Date fechaVencimiento) 
    {
        super(codigoBarra, codigoCategoria, marca, descripcion, precio, stock);
        this.fechaFabricacion = fechaFabricacion;
        this.fechaVencimiento = fechaVencimiento;
        listaProductos.add(this);
        colaLacteos.add(this);
    }
     
    public Date getFechaFabricacion() {
		return fechaFabricacion;
	}

	public void setFechaFabricacion(Date fechaFabricacion) {
		this.fechaFabricacion = fechaFabricacion;
	}

	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}

	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	
	
	@Override
	public String obtenerDetalles() 
	    {
	        return "Código de Barras: " + getCodigoBarra() + "\n" + "Marca: " + getMarca() + "\n" + "Descripción: " + getDescripcion() + "\n" + 
	        		"Precio: " + getPrecio() + "\n" + "Stock: " + getStock() + "\n";
	    }
	
	
	public static void verLacteoProximo() 
	{
		Scanner scanner = new Scanner(System.in);
	    Lacteos lacteoProximo = null;
	    long minDiferencia = Long.MAX_VALUE;
	    Date fechaActual = new Date();

	    for (Lacteos lacteo : colaLacteos) 
	    {
	        long diferenciaEnMilisegundos = lacteo.getFechaVencimiento().getTime() - fechaActual.getTime();
	        if (diferenciaEnMilisegundos >= 0 && diferenciaEnMilisegundos < minDiferencia) 
	        {
	            minDiferencia = diferenciaEnMilisegundos;
	            lacteoProximo = lacteo;
	        }
	    }
	    if (lacteoProximo != null) 
	    {
	    	System.out.println("----------------------");
	        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	        System.out.println("°Lácteo próximo a vencer:\n" +
	                "°Código de Barras: " + lacteoProximo.getCodigoBarra() + "\n" +
	                "°Marca: " + lacteoProximo.getMarca() + "\n" +
	                "°Descripción: " + lacteoProximo.getDescripcion() + "\n" +
	                "°Precio: " + lacteoProximo.getPrecio() + "\n" +
	                "°Fecha de Vencimiento: " + sdf.format(lacteoProximo.getFechaVencimiento()) + "\n" +
	                "°Días restantes: " + TimeUnit.MILLISECONDS.toDays(minDiferencia) + "\n");
	        
		    System.out.println("°Ingrese cualquier caracater para volver al menu");
		    System.out.println("----------------------");
		    String x = scanner.nextLine();
		    Menus.MenuGen();
	    } 
	    else 
	    {
	        System.out.println("°No hay productos lácteos próximos a vencer.");
	    }
	}
	
	public static void agregarNuevoLacteo() 
    {
		System.out.println("----------------------");
    	Scanner scanner = new Scanner(System.in);
    	SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    	String codigoBarra = Excepciones.verificarCodigoBarras(scanner);

    	Categoria codigoCategoria = Categoria.LACTEOS;

    	System.out.println("°Ingrese la marca:");
    	String marca = Excepciones.verificarCadena(scanner);

    	System.out.println("°Ingrese la descripción:");
    	String descripcion = Excepciones.verificarCadena(scanner);

    	double precio = Excepciones.verificarDouble(scanner);

    	System.out.println("°Ingrese el stock:");
    	int stock = Excepciones.verificarNumero(scanner);
    	        
    	scanner.nextLine();

    	Date fechaFabricacion;
    	while (true) 
    	{
    		try 
    		{
    			System.out.println("°Ingrese la fecha de fabricación (dd/MM/yyyy):");
    	        fechaFabricacion = dateFormat.parse(scanner.nextLine());
    	        break;
    	        } 
    		catch (ParseException e) 
    	    {
    			System.out.println("°Error en formato de fecha. Intente nuevamente.");
    	    }
    	}

    	Date fechaVencimiento;
    	while (true) 
    	{
    	    try 
    	    {
    	    	System.out.println("°Ingrese la fecha de vencimiento (dd/MM/yyyy):");
    	        fechaVencimiento = dateFormat.parse(scanner.nextLine());
    	        break; 
    	    } 
    	    catch (ParseException e) 
    	    {
    	    	System.out.println("°Error en formato de fecha. Intente nuevamente.");
    	    }
    	}

    	Lacteos nuevoLacteo = new Lacteos(codigoBarra, codigoCategoria, marca, descripcion, precio, stock, fechaFabricacion, fechaVencimiento);
    	        
        colaLacteos.add(nuevoLacteo);
        escribirEnArchivoColaLacteos(nuevoLacteo);
        escribirEnArchivoArrayListProductos(nuevoLacteo);
                
    	System.out.println("°Lácteo agregado exitosamente.");
    	System.out.println("°Ingrese cualquier caracater para volver al menu");
    	System.out.println("----------------------");
    	String x = scanner.nextLine();
    	Menus.MenuGen();
    	 } 	
    

	public static void modificarLacteo() 
	{
		System.out.println("----------------------");
	    Scanner scanner = new Scanner(System.in);
	    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

	    System.out.println("°Ingrese el código de barras del lácteo a modificar:");
	    String codigoBarra = Excepciones.verificarCodigoBarras(scanner);

	    Lacteos lacteoModificado = null;
	    for (Lacteos lacteo : colaLacteos) 
	    {
	        if (lacteo.getCodigoBarra().equals(codigoBarra)) 
	        {
	            lacteoModificado = lacteo;
	            break;
	        }
	    }

	    if (lacteoModificado == null) {
	        System.out.println("°Producto no encontrado en la cola de lácteos.");
	        scanner.close();
	        return;
	    }

	    System.out.println("°Detalles actuales del producto:");
	    System.out.println(lacteoModificado.obtenerDetalles());

	    System.out.println("°Ingrese la nueva marca:");
	    String nuevaMarca = Excepciones.verificarCadena(scanner);
	    lacteoModificado.setMarca(nuevaMarca);

	    System.out.println("°Ingrese la nueva descripción:");
	    String nuevaDescripcion = Excepciones.verificarCadena(scanner);
	    lacteoModificado.setDescripcion(nuevaDescripcion);

	    System.out.println("°Ingrese el nuevo precio:");
	    double nuevoPrecio = Excepciones.verificarDouble(scanner);
	    lacteoModificado.setPrecio(nuevoPrecio);

	    System.out.println("°Ingrese el nuevo stock:");
	    int nuevoStock = Excepciones.verificarNumero(scanner);
	    lacteoModificado.setStock(nuevoStock);
	        
	    scanner.nextLine();

	    Date nuevaFechaFabricacion;
	    while (true) 
	    {
	    	try 
	    	{
	    		System.out.println("°Ingrese la nueva fecha de fabricación (dd/MM/yyyy):");
	            nuevaFechaFabricacion = dateFormat.parse(scanner.nextLine());
	            break;
	        } 
	    	catch (ParseException e) 
	    	{
	    		System.out.println("°Error en formato de fecha. Intente nuevamente.");
	        }
	    }
	    lacteoModificado.setFechaFabricacion(nuevaFechaFabricacion);

	    Date nuevaFechaVencimiento;
	    while (true) 
	    {
	    	try 
	    	{
	    		System.out.println("°Ingrese la nueva fecha de vencimiento (dd/MM/yyyy):");
	            nuevaFechaVencimiento = dateFormat.parse(scanner.nextLine());
	            break; 
	            } 
	    	catch (ParseException e)
	    	{
	    		System.out.println("°Error en formato de fecha. Intente nuevamente.");
	        }
	    }

	        System.out.println("°Producto modificado exitosamente.");
	        
	        actualizarArchivoLacteos();
	        actualizarCola();
	    
	    System.out.println("°Ingrese cualquier caracter para volver al menú.");
	    System.out.println("----------------------");
	    scanner.nextLine();
	    Menus.MenuGen();
	}


    
    private static void actualizarCola() 
    {
        try 
        {
            FileWriter writer = new FileWriter("ColaLacteos.txt");
            PrintWriter printer = new PrintWriter(writer);

            for (Lacteos lacteo : colaLacteos) 
            {
                printer.println(lacteo.getCodigoBarra() + "|" +
                                lacteo.getCodigoCategoria().getCodigo() + "|" +
                                lacteo.getMarca() + "|" +
                                lacteo.getDescripcion() + "|" +
                                lacteo.getPrecio() + "|" +
                                lacteo.getStock() + "|" +
                                lacteo.getFechaVencimiento());
                printer.println();
            }
            printer.close();
            System.out.println("°Archivo PilaLacteos.txt actualizado correctamente.");
        }
        catch (IOException e) 
        {
            System.out.println("°Error al actualizar el archivo PilaLacteos.txt: " + e.getMessage());
        }
    }
    
    public static void actualizarArchivoLacteos() 
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ColaLacteos))) 
        {
            for (Lacteos lacteo : colaLacteos) 
            {
                String linea = lacteo.getCodigoBarra() + "|" +
                               lacteo.getCodigoCategoria().getCodigo() + "|" +
                               lacteo.getMarca() + "|" +
                               lacteo.getDescripcion() + "|" +
                               lacteo.getPrecio() + "|" +
                               lacteo.getStock() + "|" +
                               dateFormat.format(lacteo.getFechaFabricacion()) + "|" +
                               dateFormat.format(lacteo.getFechaVencimiento());
                bw.write(linea);
                bw.newLine();
            }
            System.out.println("°Archivo ColaLacteos.txt actualizado correctamente.");
        } 
        catch (IOException e) 
        {
            System.out.println("°Error al actualizar el archivo ColaLacteos.txt: " + e.getMessage());
        }
    }
    
    private static void escribirEnArchivoColaLacteos(Lacteos lacteo) 
    {
        try (FileWriter fw = new FileWriter(ColaLacteos, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) 
        {

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
            String fechaFabricacionStr = dateFormat.format(lacteo.fechaFabricacion);
            String fechaVencimientoStr = dateFormat.format(lacteo.fechaVencimiento);

            out.println(lacteo.codigoBarra + "|" + lacteo.codigoCategoria.getCodigo() + "|" +
                    lacteo.marca + "|" + lacteo.descripcion + "|" +
                    lacteo.precio + "|" + lacteo.stock + "|" +
                    fechaFabricacionStr + "|" + fechaVencimientoStr + "\n" );

        }
        catch (IOException e) 
        {
            System.out.println("°Error al escribir en el archivo de Cola de Lácteos: " + e.getMessage());
        }
    }
    
    public static void leerArchivoLacteos() 
    { 
    	  try (BufferedReader br = new BufferedReader(new FileReader("ColaLacteos.txt"))) 
    	  {
    	        String linea;
    	        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
    	        while ((linea = br.readLine()) != null) 
    	        {
    	            if (linea.trim().isEmpty()) 
    	            {
    	                continue;
    	            }
    	            String[] datos = linea.split("\\|");
    	            if (datos.length == 8) 
    	            {
    	                String codigoBarra = datos[0];
    	                Categoria categoria = Categoria.values()[Integer.parseInt(datos[1]) - 1];
    	                String marca = datos[2];
    	                String descripcion = datos[3];
    	                double precio = Double.parseDouble(datos[4]);
    	                int stock = Integer.parseInt(datos[5]);
    	                Date fechaFabricacion = dateFormat.parse(datos[6]);
    	                Date fechaVencimiento = dateFormat.parse(datos[7]);

    	                Lacteos lacteo = new Lacteos(codigoBarra, categoria, marca, descripcion, precio, stock, fechaFabricacion, fechaVencimiento);
    	                colaLacteos.add(lacteo);
    	            }
    	        }
    	        System.out.println("°Datos cargados correctamente desde el archivo ColaLacteos.txt.");
    	    } catch (IOException | ParseException e) {
    	        System.out.println("Error al leer el archivo ColaLacteos.txt: " + e.getMessage());
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

    
    
    public String obtenerFechaVencimiento() 
    {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        return sdf.format(fechaVencimiento);
    }

    public String obtenerFechaFabricacion() 
    {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        return sdf.format(fechaFabricacion);
    } 

    @Override
    public String mostrarFechaFormateada()
    {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        return "Fecha de Fabricación: " + sdf.format(fechaFabricacion) + ", Fecha de Vencimiento: " + sdf.format(fechaVencimiento);
    }

    @Override
    public String diferenciaFechas() 
    {
        Date fechaActual = new Date();
        long diferenciaEnMilisegundos = fechaVencimiento.getTime() - fechaActual.getTime();
        long diferenciaEnDias = TimeUnit.MILLISECONDS.toDays(diferenciaEnMilisegundos);

        if (diferenciaEnDias > 10) {
            return "Vigente";
        } else if (diferenciaEnDias > 0) {
            return "Pronto a vencer";
        } else if (diferenciaEnDias == 0) {
            return "Vence hoy";
        } else {
            return "Vencido";
        }
    }
  
}
