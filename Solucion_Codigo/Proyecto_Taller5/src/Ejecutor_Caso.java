import java.time.LocalDate;
import java.util.Random;
import java.util.Scanner;
public class Ejecutor_Caso
{
    public static void main(String[] args) 
    {
        Scanner in = new Scanner(System.in);
        Random rand = new Random();
        Caso casoX = new Caso();
        System.out.print("Ingrese la cantidad de involucrados: ");
        casoX.lim = in.nextInt();
        System.out.print("Ingrese el nombre del caso: ");
        casoX.nombre = in.next();
        casoX.fecha = LocalDate.of(2025, 5, 10);
        casoX.Gravedad();
        for (int i = 0; i < casoX.lim; i++) 
        {
            System.out.print("Ingrese el nombre del involucrado " + i + ": ");
            casoX.lista[i].nombre = in.next();
            System.out.println("Ingrese la ocupación del involucrado" + i + ": ");
            System.out.print("Ingrese la edad del involucrado" + i + ": ");
            casoX.lista[i].edad = in.nextShort();
            System.out.println("Ingrese el nivel de implicación del involucrado" + i + ": ");
            casoX.lista[i].nivelImplicación = in.nextInt();
            System.out.println("¿El involucrado " + i + " decidió cooperar?");
            casoX.lista[i].cooperación = in.nextBoolean();
            System.out.println("Ingrese el valor de los daños ocasionados al estado por el involucrado" + i + ": ");
            casoX.lista[i].daños = in.nextDouble();
            casoX.lista[i].sentencia.plusDays(rand.nextInt(1825));   
            casoX.Reducción(i, rand.nextDouble(10000.00));
        }
        casoX.toString();
        for (int i = 0; i < casoX.lim; i++)
            casoX.lista[i].toString();
    }
}
class Caso
{
    public int lim;
    Persona[] lista = new Persona[lim];
    public String nombre;
    public LocalDate fecha;
    public String estado;
    
    public Caso(){}
    
    public Caso(int lim, String nombre, LocalDate fecha, String estado)
    {
        this.lim = lim;
        this.nombre = nombre;
        this.fecha = fecha;
        this.estado = estado;
    }
    public void Gravedad()
    {
        if (fecha == fecha.plusWeeks(1)) 
            this.estado = "Alerta";
        else
            if (fecha == fecha.plusWeeks(2)) 
                this.estado = "Urgente";
            else
                this.estado = "Iniciado";
    }
    public void Reducción(int posición, double fianza)
    {
        if (lista[posición].cooperación = true) 
        {
            lista[posición].nivelImplicación = lista[posición].nivelImplicación - 1;
            LocalDate acoger = lista[posición].sentencia.plusYears(1);
            if (lista[posición].sentencia.getDayOfYear() >  + acoger.getDayOfYear() && fianza < lista[posición].daños/2) 
            {
                lista[posición].daños -= fianza;
            }
        }  
    }
    @Override
    public String toString()
    {
        return String.format("Nombre del caso: %s" + "\nFecha del caso: %s" + "\nCantidad de involucrados: %d" + "\nEstado del caso: %s", this.nombre, this.fecha, this.lim, this.estado);
    }
}
class Persona
{
    public String nombre;
    public short edad;
    public String ocupación;
    public int nivelImplicación;
    public double daños;
    public LocalDate sentencia;
    public boolean cooperación;

    public Persona(){}
    
    public Persona(String nombre, short edad, String ocupación, int nivelImplicación, double daños, LocalDate sentencia, boolean cooperación) 
    {
        this.nombre = nombre;
        this.edad = edad;
        this.ocupación = ocupación;
        this.nivelImplicación = nivelImplicación;
        this.daños = daños;
        this.sentencia = sentencia;
        this.cooperación = cooperación;
    }

    @Override
    public String toString() {
        return String.format("Nombre: %s" + 
                "\nEdad: %d" + "\nOcupaci\u00f3n: %s" + "\nNivel de implicaci\u00f3n: %s" + "\nda\u00f1os:" + "$" + " %d " + "Sentencia: %s" 
                + "Cooperaci\u00f3n: %s", this.nombre, this.edad, this.ocupación, this.nivelImplicación, this.daños, this.sentencia, this.cooperación);
    }
    
}