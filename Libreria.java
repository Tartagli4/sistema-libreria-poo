/**
 * Clase Libreria
 *
 * Gestiona el catálogo de libros y la lista de clientes registrados.
 * Coordina las ventas, búsquedas e impresión de reportes.
 * Demuestra relaciones entre objetos (Libreria contiene Libro y Cliente).
 *
 * Autor: Tartaglia, Juan Ignacio
 * Asignatura: Programación Orientada a Objetos
 */
public class Libreria {

    // ── Atributos privados ────────────────────────────────────────────────────
    private String   nombre;
    private Libro[]  catalogo;          // Arreglo de libros del catálogo
    private Cliente[] clientes;         // Arreglo de clientes registrados
    private int      cantidadLibros;    // Cuántos libros hay actualmente
    private int      cantidadClientes;  // Cuántos clientes hay actualmente
    private double   totalRecaudado;    // Acumulador de ventas

    // ── Constructores sobrecargados ───────────────────────────────────────────

    /**
     * Constructor 1: con nombre y capacidades personalizadas.
     * @param nombre        Nombre de la librería
     * @param capLibros     Capacidad máxima del catálogo
     * @param capClientes   Capacidad máxima de clientes
     */
    public Libreria(String nombre, int capLibros, int capClientes) {
        this.nombre           = nombre;
        this.catalogo         = new Libro[capLibros];
        this.clientes         = new Cliente[capClientes];
        this.cantidadLibros   = 0;
        this.cantidadClientes = 0;
        this.totalRecaudado   = 0.0;
    }

    /**
     * Constructor 2: sobrecarga — capacidades por defecto (50 libros, 100 clientes).
     * @param nombre Nombre de la librería
     */
    public Libreria(String nombre) {
        this(nombre, 50, 100);
    }

    // ── Gestión del catálogo ──────────────────────────────────────────────────

    /**
     * Agrega un libro al catálogo.
     * @param libro Objeto Libro a agregar
     * @return true si se agregó correctamente
     */
    public boolean agregarLibro(Libro libro) {
        if (cantidadLibros >= catalogo.length) {
            System.out.println("  [Error] El catálogo está lleno. Capacidad: " + catalogo.length);
            return false;
        }
        catalogo[cantidadLibros] = libro;
        cantidadLibros++;
        System.out.println("  Libro agregado: \"" + libro.getTitulo() + "\" de " + libro.getAutor());
        return true;
    }

    /**
     * Busca un libro por título (búsqueda parcial, sin distinción de mayúsculas).
     * Método con parámetro String y retorno de objeto Libro.
     * @param titulo Título o fragmento a buscar
     * @return El primer Libro que coincide, o null si no se encontró
     */
    public Libro buscarLibroPorTitulo(String titulo) {
        for (int i = 0; i < cantidadLibros; i++) {
            if (catalogo[i].getTitulo().toLowerCase().contains(titulo.toLowerCase())) {
                return catalogo[i];
            }
        }
        return null;
    }

    /**
     * Busca un libro por autor.
     * Sobrecarga del concepto de búsqueda — mismo objetivo, distinto criterio.
     * @param autor Nombre del autor a buscar
     * @return El primer Libro de ese autor, o null si no se encontró
     */
    public Libro buscarLibroPorAutor(String autor) {
        for (int i = 0; i < cantidadLibros; i++) {
            if (catalogo[i].getAutor().toLowerCase().contains(autor.toLowerCase())) {
                return catalogo[i];
            }
        }
        return null;
    }

    // ── Gestión de clientes ───────────────────────────────────────────────────

    /**
     * Registra un nuevo cliente en la librería.
     * @param cliente Objeto Cliente a registrar
     * @return true si se registró correctamente
     */
    public boolean registrarCliente(Cliente cliente) {
        if (cantidadClientes >= clientes.length) {
            System.out.println("  [Error] No hay más espacio para clientes.");
            return false;
        }
        clientes[cantidadClientes] = cliente;
        cantidadClientes++;
        System.out.println("  Cliente registrado: " + cliente.getNombre());
        return true;
    }

    // ── Operación principal: venta ────────────────────────────────────────────

    /**
     * Procesa la venta de un libro a un cliente.
     * Método con múltiples parámetros (objetos + primitivo) y retorno booleano.
     * @param cliente  Cliente que compra
     * @param libro    Libro a comprar
     * @param cantidad Cantidad de ejemplares
     * @return true si la transacción fue exitosa
     */
    public boolean realizarVenta(Cliente cliente, Libro libro, int cantidad) {
        System.out.println("\n  >> Procesando venta...");

        double total = libro.calcularTotal(cantidad);

        // Verifica stock
        if (!libro.tieneStock() || libro.getStock() < cantidad) {
            System.out.println("  [Venta cancelada] Sin stock suficiente.");
            return false;
        }

        // Verifica saldo del cliente
        if (!cliente.puedePagar(total)) {
            System.out.printf("  [Venta cancelada] Saldo insuficiente. Total: $%.2f | Saldo: $%.2f%n",
                    total, cliente.getSaldo());
            return false;
        }

        // Ejecuta la transacción
        libro.vender(cantidad);
        cliente.realizarPago(total);
        totalRecaudado += total;

        System.out.printf("  Venta exitosa: %d x \"%s\" por $%.2f%n",
                cantidad, libro.getTitulo(), total);
        return true;
    }

    /**
     * Procesa la venta con descuento especial.
     * Sobrecarga de realizarVenta — agrega parámetro de descuento.
     * @param cliente    Cliente que compra
     * @param libro      Libro a comprar
     * @param cantidad   Cantidad de ejemplares
     * @param descuento  Porcentaje de descuento a aplicar
     * @return true si la transacción fue exitosa
     */
    public boolean realizarVenta(Cliente cliente, Libro libro, int cantidad, double descuento) {
        System.out.println("\n  >> Procesando venta con descuento del " + descuento + "%...");

        double total = libro.calcularTotal(cantidad, descuento);

        if (!libro.tieneStock() || libro.getStock() < cantidad) {
            System.out.println("  [Venta cancelada] Sin stock suficiente.");
            return false;
        }

        if (!cliente.puedePagar(total)) {
            System.out.printf("  [Venta cancelada] Saldo insuficiente. Total c/dto: $%.2f | Saldo: $%.2f%n",
                    total, cliente.getSaldo());
            return false;
        }

        libro.vender(cantidad);
        cliente.realizarPago(total);
        totalRecaudado += total;

        System.out.printf("  Venta con descuento exitosa: %d x \"%s\" por $%.2f (%.0f%% off)%n",
                cantidad, libro.getTitulo(), total, descuento);
        return true;
    }

    // ── Reportes ──────────────────────────────────────────────────────────────

    /**
     * Imprime el catálogo completo de libros.
     */
    public void mostrarCatalogo() {
        System.out.println("\n╔══════════════════════════════════════╗");
        System.out.println("║   CATÁLOGO DE " + nombre);
        System.out.println("╚══════════════════════════════════════╝");
        if (cantidadLibros == 0) {
            System.out.println("  El catálogo está vacío.");
            return;
        }
        for (int i = 0; i < cantidadLibros; i++) {
            catalogo[i].mostrarInfo();
        }
    }

    /**
     * Imprime la lista de clientes registrados.
     */
    public void mostrarClientes() {
        System.out.println("\n╔══════════════════════════════════════╗");
        System.out.println("║   CLIENTES REGISTRADOS               ║");
        System.out.println("╚══════════════════════════════════════╝");
        if (cantidadClientes == 0) {
            System.out.println("  No hay clientes registrados.");
            return;
        }
        for (int i = 0; i < cantidadClientes; i++) {
            System.out.println("  " + clientes[i].obtenerResumen());
        }
    }

    /**
     * Imprime un resumen estadístico de la librería.
     */
    public void mostrarResumen() {
        System.out.println("\n╔══════════════════════════════════════╗");
        System.out.println("║   RESUMEN DE " + nombre);
        System.out.println("╚══════════════════════════════════════╝");
        System.out.println("  Libros en catálogo  : " + cantidadLibros);
        System.out.println("  Clientes registrados: " + cantidadClientes);
        System.out.printf ("  Total recaudado     : $%.2f%n", totalRecaudado);
        System.out.println("  [Estático] Total libros creados en el sistema: "
                           + Libro.getTotalLibrosRegistrados());
        System.out.println("  [Estático] Total clientes creados en el sistema: "
                           + Cliente.getTotalClientesRegistrados());
    }

    // ── Getter ────────────────────────────────────────────────────────────────

    public String getNombre()         { return nombre;         }
    public double getTotalRecaudado() { return totalRecaudado; }
}
