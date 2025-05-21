
import java.util.Scanner;

public class Ejecutor_SistemaDeVentas {

    public static void main(String[] args) {
        Producto[] tienda = new Producto[5];
        tienda[0] = new Producto("Laptop", 800.0, 10);
        tienda[1] = new Producto("Celular", 600.0, 15);
        tienda[2] = new Producto("Tablet", 300.0, 20);
        tienda[3] = new Producto("Audifonos", 50.0, 50);
        tienda[4] = new Producto("Monitor", 400.0, 8);
        CarritoDeCompra carrito = new CarritoDeCompra(tienda, 10);
        Scanner tcl = new Scanner(System.in);
        String opcion;
        do {
            System.out.println("Ingrese producto a comprar: ");
            String producto = tcl.next();
            System.out.println("Ingrese la cantidad a comprar: ");
            int cantidad = tcl.nextInt();
            carrito.agregarProductos(producto, cantidad);
            System.out.println("Desea agregar otro producto? (s/n)");
            opcion = tcl.next();
        } while (opcion.equalsIgnoreCase("s"));
        System.out.println("\nDetalle de la compra:");
        System.out.print(carrito.mostrarDetallesCompra());

        double total = carrito.calcularTotal();
        System.out.println("Total a pagar: $" + total);
        System.out.println("Ingrese el monto con el que va a pagar: ");
        double pago = tcl.nextDouble();

        System.out.println(carrito.realizarPago(pago));

        System.out.println("\nInventario actualizado:");
        for (Producto p : tienda) {
            System.out.println(p.getNombre() + " - Stock restante: " + p.getCantidad());
        }
        tcl.close();
    }
}

class Producto {

    public String nombre;
    public double precio;
    public int cantidad;

    public Producto() {
    }

    public Producto(String nombre, double precio, int cantidad) {
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
    }
    public void reducirCantidad(int cantidadVendida) {
        this.cantidad -= cantidadVendida;
    }
    
    public String getNombre() {
        return nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    @Override
    public String toString() {
        return nombre + " - $" + precio + " x " + cantidad;
    }

}

class CarritoDeCompra {

    private Producto[] producto;
    private Producto[] tienda;
    private double descuento;
    private int con;

    public CarritoDeCompra(Producto[] tienda, double descuento) {
        this.tienda = tienda;
        this.descuento = descuento;
        this.producto = new Producto[100];
        this.con = 0;
    }

    public String agregarProductos(String nombre, int cantidad) {
        for (Producto item : tienda) {
            if (item != null && item.getNombre().equalsIgnoreCase(nombre)) {
                if (cantidad <= item.getCantidad()) {
                    for (int i = 0; i < con; i++) {
                        if (producto[i].getNombre().equalsIgnoreCase(nombre)) {
                            int nuevaCantidad = producto[i].getCantidad() + cantidad;
                            producto[i] = new Producto(nombre, item.getPrecio(), nuevaCantidad);
                            return "Cantidad actualizada de: " + nombre + ", total: " + nuevaCantidad;
                        }
                    }
                    producto[con++] = new Producto(nombre, item.getPrecio(), cantidad);
                    return "Producto agregado: " + nombre + ", cantidad: " + cantidad;
                } else {
                    return "Cantidad insuficiente de: " + nombre;
                }
            }
        }
        return "Producto no encontrado";
    }

    public double calcularTotal() {
        double total = 0;
        for (int i = 0; i < con; i++) {
            total += producto[i].getPrecio() * producto[i].getCantidad();
        }
        if (total > 1000) {
            total -= total * (descuento / 100);
        }
        return total;
    }

    public String realizarPago(double pago) {
        double total = calcularTotal();
        if (pago >= total) {
            for (int i = 0; i < con; i++) {
                for (Producto item : tienda) {
                    if (item != null && item.getNombre().equalsIgnoreCase(producto[i].getNombre())) {
                        item.reducirCantidad(producto[i].getCantidad());
                    }
                }
            }
            return "Gracias por su compra. Cambio: $" + (pago - total);
        } else {
            return "Pago insuficiente. Falta: $" + (total - pago);
        }
    }

    public String mostrarDetallesCompra() {
        StringBuilder detalles = new StringBuilder();
        for (int i = 0; i < con; i++) {
            Producto p = producto[i];
            detalles.append("- ").append(p.getNombre())
                    .append(" x ").append(p.getCantidad())
                    .append(" = $").append(p.getPrecio() * p.getCantidad())
                    .append("\n");
        }
        return detalles.toString();
    }

    @Override
    public String toString() {
        return mostrarDetallesCompra();
    }
}

