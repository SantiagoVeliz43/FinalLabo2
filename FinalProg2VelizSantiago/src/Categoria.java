
public enum Categoria 
{
	REGALERIA("1"),
    LACTEOS("2"),
    ALIMENTOS("3"),
    LIMPIEZA("4"),
    COTILLON("5");

    private String codigo;

    Categoria(String codigo) 
    {
        this.codigo = codigo;
    }

    public String getCodigo() 
    {
        return codigo;
    }
}
