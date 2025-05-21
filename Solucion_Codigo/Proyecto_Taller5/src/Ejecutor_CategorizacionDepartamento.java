
import java.util.Scanner;
import java.util.ArrayList;
public class Ejecutor_CategorizacionDepartamento {
    public static void main(String[] args) {
        Scanner tcl = new Scanner(System.in);
        String nombreEmpresa, ruc, direccion, nombre;
        int n, numeroEmpleados;
        double produccionAnual;
        System.out.print("Ingrese el nombre de la empresa: ");
        nombreEmpresa = tcl.nextLine();
        System.out.print("Ingrese el ruc: ");
        ruc = tcl.nextLine();
        System.out.print("Ingrese su dirreccion: ");
        direccion = tcl.nextLine();
        Empresa empresa = new Empresa(nombreEmpresa, ruc, direccion);
        System.out.print("¿Cuantos departamentos desea agregar?: ");
        n = tcl.nextInt();
        tcl.nextLine();
        for (int i = 0; i < n; i++) {
            System.out.println("\nDepartamento #" + (i +1));
            System.out.print("Nombre: ");
            nombre = tcl.nextLine();
            System.out.print("Numero de empleados: ");
            numeroEmpleados = tcl.nextInt();
            System.out.print("Produccion anual: ");
            produccionAnual = tcl.nextDouble();
            tcl.nextLine();
            Departamento dpto = new Departamento(nombre, numeroEmpleados, produccionAnual);
            empresa.agregarDepartamento(dpto);
        }
        System.out.println("===Resultados===");
        System.out.println(empresa);
    }
}

class Departamento {

    public String nombre;
    public int numeroEmpleados;
    public double produccionAnual;
    public String categoria;
    public Departamento(String nombre, int numeroEmpleados, double produccionAnual) {
        this.nombre = nombre;
        this.numeroEmpleados = numeroEmpleados;
        this.produccionAnual = produccionAnual;
        this.categoria = asignarCategoria();
    }

    public String asignarCategoria() {
        if (this.numeroEmpleados > 20 && this.produccionAnual > 1_000_000) {
            return "A";
        } else if (this.numeroEmpleados >= 20 && this.produccionAnual >= 1_000_000) {
            return "B";
        } else if (this.numeroEmpleados >= 10 && this.produccionAnual >= 500_000) {
            return "C";
        } else {
            return "No categorizado";
        }
    }

    @Override
    public String toString() {
        return "Departamento: " + nombre
                + "\nEmpleados: " + numeroEmpleados
                + "\nProduccion anual: " + produccionAnual
                
                + "\nCategoria: " + categoria + "\n";
    }

}

class Empresa {

    public String nombre;
    public String ruc;
    public String direccion;
    public ArrayList<Departamento> departamentos;

    public Empresa() {
    }

    public Empresa(String nombre, String ruc, String direccion) {
        this.nombre = nombre;
        this.ruc = ruc;
        this.direccion = direccion;
        this.departamentos = new ArrayList<>();
    }

    public void agregarDepartamento(Departamento dpto) {
        this.departamentos.add(dpto);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Empresa: ").append(nombre).append("\n");
        sb.append("RUC: ").append(ruc).append("\n");
        sb.append("Dirección: ").append(direccion).append("\n");
        sb.append("\n=== DEPARTAMENTOS ===\n");

        for (Departamento d : departamentos) {
            sb.append(d.toString()).append("\n");
        }

        return sb.toString();
    }
}
