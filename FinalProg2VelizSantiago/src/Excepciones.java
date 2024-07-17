import java.util.*;

public class Excepciones 
{
	public static int verificarNumero(Scanner scanner) 
	{
        while (true) 
        {
            try 
            {
                return scanner.nextInt();
            } 
            catch (InputMismatchException e) 
            {
                System.out.println("°ERRORR: Reintente: ");
                scanner.nextLine();
            }
        }
	}
	
    public static String verificarCodigoBarras(Scanner scanner) 
    {
        while (true) 
        {
            try 
            {
                System.out.println("°Ingrese el codigo de barras: ");
                String codigo = scanner.nextLine();

                if (codigo.length() == 3 && codigo.matches("\\d+"))
                {
                    return codigo;
                } else 
                {
                    throw new IllegalArgumentException("°ERROR: Reintente: ");
                }
            } 
            catch (IllegalArgumentException e) 
            {
                System.out.println("°ERROR: " + e.getMessage());
            }
        }
    }
    
    public static double verificarDouble(Scanner scanner) 
    {
        while (true)
        {
            try 
            {
                System.out.println("°Ingrese el precio: ");
                return Double.parseDouble(scanner.nextLine());
            } 
            catch (NumberFormatException e) 
            {
                System.out.println("°ERROR: Reintente: ");
            }
        }
    }

	
    public static String verificarCadena(Scanner scanner) {
        while (true) {
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            } else {
                System.out.println("°ERRORR. Reintente:");
            }
        }
    }

}
	
	

	
