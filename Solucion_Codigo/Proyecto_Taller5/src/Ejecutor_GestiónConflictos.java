import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

public class Ejecutor_GestiónConflictos {
    public static void main(String[] args) {
        País tur = new País("Turquía", 85330000, País.Desarrollo.PrimerMundista);
        País uzb = new País("Uzbekistán", 35650000, País.Desarrollo.TercerMundista);

        Conflicto conflicto = new Conflicto();
        conflicto.añadirPaísInvolucrado(tur);
        conflicto.añadirPaísInvolucrado(uzb);

        Evento batalla = new Evento("Batalla en Asia", LocalDateTime.now(),"Asia","Un hipotético conflicto por razones desconocidas");

        conflicto.añadirEvento(batalla);
        conflicto.actualizar();

        System.out.println(conflicto);
    }
}

class País {
    public enum Desarrollo {
        PrimerMundista,
        EnDesarrollo,
        TercerMundista
    }

    private final String nombre;
    private final Desarrollo desarrollo;
    private final int población;

    public País(String nombre, int población, Desarrollo desarrollo) 
    {
        this.nombre = nombre;
        this.población = población;
        this.desarrollo = desarrollo;
    }

    public String getNombre() {
        return nombre;
    }

    public Desarrollo getDesarrollo() {
        return desarrollo;
    }

    public int getPoblación() {
        return población;
    }

    @Override
    public String toString() {
        return this.nombre;
    }
}

class Evento{
    private final String nombre;
    private final LocalDateTime fechaInicio;
    private final String ubicación;
    private final String descripción;
    private List<País> paisesInvolucrados;
    private boolean guerraNuclear;
    private int bajas;

    public Evento(String nombre, LocalDateTime fechaInicio, String ubicación, String descripción) {
        this.nombre = nombre;
        this.fechaInicio = fechaInicio;
        this.ubicación = ubicación;
        this.descripción = descripción;
    }

    public String getNombre() {
        return nombre;
    }

    public LocalDateTime getFechaInicio() {
        return fechaInicio;
    }

    public String getUbicación() {
        return ubicación;
    }

    public String getDescripción() {
        return descripción;
    }

    public List<País> getpaisesInvolucrados() {
        return paisesInvolucrados;
    }

    public boolean esGuerraNuclear() {
        return guerraNuclear;
    }

    public int getBajas() {
        return bajas;
    }

    @Override
    public String toString() {
        return "-- Información de evento --\n" +
               "Nombre: " + nombre + "\n" +
               "Ubicación: " + ubicación + "\n" +
               "Fecha de inicio: " + fechaInicio + "\n" +
               "Descripción: " + descripción;
    }
}

class Conflicto {
    public Conflicto(){}
    public enum Estado {
        GuerraMundial,
        Guerra,
        AltaTensión
    }

    private Estado estadoActual = Estado.AltaTensión;
    private final List<Evento> eventosActuales = new ArrayList<>();
    private final List<País> paísesInvolucrados = new ArrayList<>();

    public void añadirEvento(Evento evento) {
        eventosActuales.add(evento);
    }

    public Evento getEvento(String nombre) {
        for (Evento x : eventosActuales)
            if (x.getNombre().equals(nombre))
                return x;
        return null;
    }

    public void QuitarEvento(String nombre) {
        for (int i = 0; i < eventosActuales.size(); i++) {
            if (eventosActuales.get(i).getNombre().equals(nombre)) {
                eventosActuales.remove(i);
                break;
            }
        }
    }

    public void añadirPaísInvolucrado(País país) {
        paísesInvolucrados.add(país);
    }

    public País getPaísInvolucrado(String nombre) {
        for (País c : paísesInvolucrados)
            if (c.getNombre().equals(nombre))
                return c;
        return null;
    }

    public void quitarPaísInvolucrado(String nombre) {
        for (int i = 0; i < paísesInvolucrados.size(); i++) {
            if (paísesInvolucrados.get(i).getNombre().equals(nombre)) {
                paísesInvolucrados.remove(i);
                break;
            }
        }
    }

    public Estado getEstadoActual() {
        return estadoActual;
    }

    public void actualizar() {
        int cantidadBatallas = 0;

        for (Evento evento : eventosActuales) {
            if (evento instanceof Evento eb) {
                cantidadBatallas++;

                boolean conflictoPrimerMundo = true;
                for (País p : eb.getpaisesInvolucrados()) {
                    if ((double) eb.getBajas() / p.getPoblación()>= 0.5) {
                        System.out.println("Reunión urgente de la ONU requerida para asistir al país: " + eb.getNombre());
                    }
                    if (p.getDesarrollo() != País.Desarrollo.PrimerMundista) {
                        conflictoPrimerMundo = false;
                    }
                }

                if (conflictoPrimerMundo && eb.esGuerraNuclear()) {
                    estadoActual = Estado.GuerraMundial;
                    return;
                }
            }
        }

        if (cantidadBatallas > 0) {
            double proporción = (double) paísesInvolucrados.size() / cantidadBatallas;
            if (proporción > 0.5) {
                estadoActual = Estado.GuerraMundial;
            } else if (proporción >= 0.3) {
                System.out.println("Reunión urgente de la ONU requerida");
            }
        }
    }

    @Override
    public String toString() {
        return "-- Información de Conflicto --\n" +
            "Estado: " + estadoActual + "\n" +
            "Países involucrados: " + paísesInvolucrados + "\n" +
            "Eventos: " + eventosActuales;
    }
}

