import java.util.*;
import java.io.*;

public class Personal 
{
    private String nombre;
    private String apellido;
    private String legajo;
    private int dni;
    
    private static ArrayList<Personal> listaPersonal = new ArrayList<>();
    private static Map<Integer, String> dniLegajoMap = new HashMap<>();
    private static final String archivoPersonal = "ArrayListPersonal.txt";
    
    public Personal(String nombre, String apellido, String legajo, int dni) 
    {
        this.nombre = nombre;
        this.apellido = apellido;
        this.legajo = legajo;
        this.dni = dni;
    }
    
    public String getNombre() 
    {
        return nombre;
    }

    public void setNombre(String nombre) 
    {
        this.nombre = nombre;
    }

    public String getApellido() 
    {
        return apellido;
    }

    public void setApellido(String apellido) 
    {
        this.apellido = apellido;
    }

    public String getLegajo() 
    {
        return legajo;
    }

    public void setLegajo(String legajo) 
    {
        this.legajo = legajo;
    }

    public int getDni() 
    {
        return dni;
    }

    public void setDni(int dni) 
    {
        this.dni = dni;
    }
 

    
    public static void agregarEmpleado()
    {
    	  Scanner scanner = new Scanner(System.in);
    	  System.out.println("----------------------");
    	  
    	  System.out.println("°Ingrese nombre del nuevo Empleado: ");
          String nuevoNombre = Excepciones.verificarCadena(scanner);

          System.out.println("°Ingrese el apellido del nuevo Empleado:: ");
          String nuevoApellido = Excepciones.verificarCadena(scanner);

          System.out.println("°Ingrese el legajo del nuevo Empleado::");
          String nuevoLegajo = Excepciones.verificarCadena(scanner);

          System.out.println("°Ingrese el DNI del nuevo Empleado: ");
          int nuevoDni = Excepciones.verificarNumero(scanner);

          Personal personal = new Personal(nuevoNombre, nuevoApellido, nuevoLegajo, nuevoDni);

          listaPersonal.add(personal);
          dniLegajoMap.put(personal.getDni(), personal.getLegajo());
          escribirEnArchivo(personal);

          System.out.println("°Personal agregado correctamente.");
          System.out.println("°Ingrese cualquier caracter para volver al menú.");
          System.out.println("----------------------");
          scanner.nextLine();
          
          Menus.MenuGen();
    }

    public static void imprimirLegajoPorDNI() 
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("----------------------");
        System.out.println("°Ingrese DNI del personal para buscar legajo:");
        
        int dniBuscado = Excepciones.verificarNumero(scanner);
        
        boolean encontrado = false;
        for (Personal personal : listaPersonal) 
        {
            if (personal.getDni() == dniBuscado) 
            {
                System.out.println("°El legajo del personal con DNI " + dniBuscado + " es: " + personal.getLegajo());
                encontrado = true;
                break; 
            }
        }
        if (!encontrado) 
        {
            System.out.println("°No se encontró ningún personal con el DNI: " + dniBuscado);
        }

        System.out.println("°Ingrese cualquier caracter para volver al menú.");
        System.out.println("----------------------");
        scanner.nextLine();
        Menus.MenuGen();
    }

    public static void mostrarDatosPorLegajo() 
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("----------------------");
        System.out.println("°Ingrese el legajo del personal para buscar sus datos: ");
        String legajoBuscado = scanner.nextLine();
        
        boolean encontrado = false;
        for (Personal personal : listaPersonal) 
        {
            if (personal.getLegajo().equals(legajoBuscado)) 
            {
                System.out.println("°Datos del personal con legajo " + legajoBuscado + ":");
                System.out.println("°Nombre: " + personal.getNombre());
                System.out.println("°Apellido: " + personal.getApellido());
                System.out.println("°DNI: " + personal.getDni());
                System.out.println("°Legajo: " + personal.getLegajo());
                encontrado = true;
                break;
            }
        }
        if (!encontrado) 
        {
            System.out.println("°No se encontró ningún personal con el legajo: " + legajoBuscado);
        }
        System.out.println("°Ingrese cualquier caracter para volver al menú.");
        System.out.println("----------------------");
        scanner.nextLine(); 
        Menus.MenuGen();
    }
 
    private static void escribirEnArchivo(Personal personal) 
    {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivoPersonal, true))) 
        {
            bw.write(personal.getNombre() + "|" +
                    personal.getApellido() + "|" +
                    personal.getLegajo() + "|" +
                    personal.getDni() + "\n" + "\n");
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }
      	
    public static void leerArchivoPersonal() 
    {
        try (BufferedReader br = new BufferedReader(new FileReader(archivoPersonal))) 
        {
            String linea;
            while ((linea = br.readLine()) != null) 
            {
                String[] datos = linea.split("\\|");
                if (datos.length == 4) {
                    String nombre = datos[0];
                    String apellido = datos[1];
                    String legajo = datos[2];
                    int dni = Integer.parseInt(datos[3]);

                    Personal personal = new Personal(nombre, apellido, legajo, dni);
                    listaPersonal.add(personal);

                    dniLegajoMap.put(dni, legajo);
                }
            }
            System.out.println("°Datos cargados correctamente desde el archivo " + archivoPersonal);
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }
       
}
