
import java.util.Scanner;
public class Ejecutor_SistemaDeCalificaciones {
    public static void main(String[] args) {
        Scanner tcl = new Scanner(System.in);
        String nombre, nombreMateria;
        int edad;
        double acd, apd, aa, rec;
        System.out.print("Ingresa su nombre: ");
        nombre = tcl.next();
        System.out.print("Ingrese su edad: ");
        edad = tcl.nextInt();
        Estudiante estudiante = new Estudiante(nombre, edad);
        System.out.print("Ingrese el nombre de la materia: ");
        nombreMateria = tcl.next();
        System.out.print("Ingrese la calificacion ACD(3.5 pts): ");
        acd = tcl.nextDouble();
        System.out.print("Ingrese la calificacion APE(3.5 pts): ");
        apd = tcl.nextDouble();
        System.out.print("Ingrese la calificacion AA(3.0 pts): ");
        aa = tcl.nextDouble();
        Materia materia = new Materia(nombreMateria, acd, apd, aa);
        estudiante.asignarMateria(materia);
        System.out.println(estudiante.verificarAprobacion());
        if (!materia.aprobado()){
            System.out.println("Ingrese la nota obtenida em el examen de recuperacion (3.5 pts): ");
            rec = tcl.nextDouble();
            System.out.println(estudiante.aplicarRecuperacion(rec));
        }
    }
}

class Materia {

    public String nombre;
    public double acd;
    public double ape;
    public double aa;
    public double notaFinal;

    public Materia() {
    }

    public Materia(String nombre, double acd, double ape, double aa) {
        this.nombre = nombre;
        this.acd = acd;
        this.ape = ape;
        this.aa = aa;
    }

    public String getNombre() {
        return nombre;
    }

    public double getNotaFinal() {
        return notaFinal;
    }

    public double calcularTotal() {
        this.notaFinal = this.acd + this.ape + this.aa;
        return this.notaFinal;
    }

    public boolean aprobado() {
        return calcularTotal() > 7.0;
    }

    public double acumulado() {
        return (this.acd + this.ape + this.aa) * 0.60;
    }

    public double recuperacion(double notaRecuperacion) {
         return acumulado() + notaRecuperacion;
    }
}

class Estudiante {

    public String nombre;
    public int edad;
    public Materia materia;

    public Estudiante(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
    }

    public void asignarMateria(Materia materia) {
        this.materia = materia;
    }

    public String verificarAprobacion() {
        if (materia == null) {
            return "No se ha asignado una materia al estudiante.";
        }
        if (materia.aprobado()) {
            return nombre + " ha aprobado la materia " + materia.getNombre() + " con nota: " + materia.getNotaFinal();
        } else {
            return nombre + " NO aprobó. Debe rendir un examen de recuperación. Nota acumulada (60%): "
                    + materia.acumulado();
        }
    }

    public String aplicarRecuperacion(double notaRecuperacion) {
        double finalConRecuperacion = materia.recuperacion(notaRecuperacion);
        if (finalConRecuperacion >= 7.0) {
            return nombre + " aprobó con recuperación. Nota final: " + finalConRecuperacion;
        } else {
            return nombre + " no aprobó ni con recuperación. Nota final: " + finalConRecuperacion;
        }
    }
}
