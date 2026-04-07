/**
 * ============================================================
 * PROYECTO: SISTEMA DE LIBRERÍA
 * ============================================================
 * Asignatura  : Programación Orientada a Objetos
 * Alumno      : Tartaglia, Juan Ignacio
 * ============================================================
 *
 * Conceptos de POO aplicados en este proyecto:
 *
 *  1. Clases y Objetos          → Libro, Cliente, Libreria
 *  2. Atributos                 → Cada clase tiene atributos private
 *  3. Encapsulamiento           → Modificadores public / private
 *  4. Métodos con parámetros    → vender(int), cargarSaldo(double), etc.
 *  5. Métodos con retorno       → buscarLibro(), realizarPago(), etc.
 *  6. Constructor               → Cada clase tiene al menos un constructor
 *  7. Sobrecarga de métodos     → Libro(), Cliente(), Libreria(),
 *                                  calcularTotal(), realizarVenta(),
 *                                  buscarLibroPorTitulo/Autor()
 *  8. Miembros estáticos        → totalLibrosRegistrados, getTotalLibrosRegistrados()
 *  9. Relaciones entre objetos  → Libreria contiene Libro[] y Cliente[]
 * ============================================================
 */
public class Main {

    public static void main(String[] args) {

        separador("INICIO DEL PROGRAMA");

        // ── 1. CREAR LA LIBRERÍA ─────────────────────────────────────────────
        // Se usa Constructor 1 de Libreria (con capacidades personalizadas)
        Libreria libreria = new Libreria("El Saber", 10, 5);
        System.out.println("Librería \"" + libreria.getNombre() + "\" iniciada.\n");

        // ── 2. CREAR LIBROS (constructores sobrecargados) ────────────────────
        separador("CREACIÓN DE LIBROS — Constructores Sobrecargados");

        // Constructor 1: información completa
        Libro libro1 = new Libro(
            "El Señor de los Anillos",
            "J.R.R. Tolkien",
            "978-0-618-00222-3",
            4500.00,
            10
        );

        // Constructor 1 de nuevo con otro libro
        Libro libro2 = new Libro(
            "Cien Años de Soledad",
            "Gabriel García Márquez",
            "978-0-06-019034-9",
            3200.00,
            5
        );

        // Constructor 2: sin ISBN ni stock (libro recién adquirido)
        Libro libro3 = new Libro(
            "Clean Code",
            "Robert C. Martin",
            8900.00
        );

        // Constructor 3: solo título y autor (pendiente de precio)
        Libro libro4 = new Libro(
            "Don Quijote de la Mancha",
            "Miguel de Cervantes"
        );

        System.out.println("Se crearon 4 libros usando 3 constructores distintos.");
        System.out.println("Total registrados en el sistema (estático): "
                           + Libro.getTotalLibrosRegistrados());

        // ── 3. AGREGAR LIBROS AL CATÁLOGO ────────────────────────────────────
        separador("CATÁLOGO — Agregar libros");

        libreria.agregarLibro(libro1);
        libreria.agregarLibro(libro2);
        libreria.agregarLibro(libro3);
        libreria.agregarLibro(libro4);

        libreria.mostrarCatalogo();

        // ── 4. CREAR CLIENTES (constructores sobrecargados) ──────────────────
        separador("CLIENTES — Constructores Sobrecargados");

        // Constructor 1: datos completos + saldo inicial
        Cliente cliente1 = new Cliente(
            "María González",
            "maria@email.com",
            "264-555-0101",
            15000.0
        );

        // Constructor 2: sin saldo inicial
        Cliente cliente2 = new Cliente(
            "Carlos Rodríguez",
            "carlos@email.com",
            "264-555-0202"
        );

        // Constructor 3: solo nombre
        Cliente cliente3 = new Cliente("Ana Martínez");

        System.out.println("Se crearon 3 clientes usando 3 constructores distintos.");
        System.out.println("Total registrados en el sistema (estático): "
                           + Cliente.getTotalClientesRegistrados());

        // Registrar clientes en la librería
        separador("Registrar clientes en la librería");
        libreria.registrarCliente(cliente1);
        libreria.registrarCliente(cliente2);
        libreria.registrarCliente(cliente3);

        // ── 5. CARGAR SALDO A CLIENTES ───────────────────────────────────────
        separador("SALDO — Métodos con parámetros");

        cliente2.cargarSaldo(10000.0);
        cliente3.cargarSaldo(5000.0);
        // Intento inválido
        cliente3.cargarSaldo(-200.0);

        // ── 6. MOSTRAR INFO DE OBJETOS ───────────────────────────────────────
        separador("mostrarInfo() — Métodos sin retorno");

        System.out.println("\n>> Información de libro1:");
        libro1.mostrarInfo();

        System.out.println("\n>> Información de cliente1:");
        cliente1.mostrarInfo();

        // ── 7. MÉTODOS CON RETORNO DE VALOR ──────────────────────────────────
        separador("Métodos con retorno de valor");

        // tieneStock() retorna boolean
        System.out.println("¿'Clean Code' tiene stock? → " + libro3.tieneStock());

        // calcularTotal() retorna double — SOBRECARGADO
        double totalSinDescuento = libro1.calcularTotal(3);
        double totalConDescuento = libro1.calcularTotal(3, 15.0);

        System.out.printf("Precio de 3 x \"%s\" sin descuento : $%.2f%n",
                libro1.getTitulo(), totalSinDescuento);
        System.out.printf("Precio de 3 x \"%s\" con 15%% dto   : $%.2f%n",
                libro1.getTitulo(), totalConDescuento);

        // buscarLibro() retorna un objeto Libro — SOBRECARGADO
        separador("buscarLibro() — Retorna objeto + Sobrecarga");

        Libro encontrado1 = libreria.buscarLibroPorTitulo("anillos");
        if (encontrado1 != null) {
            System.out.println("Búsqueda por título 'anillos' → Encontrado: " + encontrado1.getTitulo());
        }

        Libro encontrado2 = libreria.buscarLibroPorAutor("García Márquez");
        if (encontrado2 != null) {
            System.out.println("Búsqueda por autor 'García Márquez' → Encontrado: " + encontrado2.getTitulo());
        }

        Libro noEncontrado = libreria.buscarLibroPorTitulo("Harry Potter");
        System.out.println("Búsqueda por título 'Harry Potter' → "
                + (noEncontrado == null ? "No encontrado" : noEncontrado.getTitulo()));

        // ── 8. REPONER Y VENDER (métodos con parámetros) ─────────────────────
        separador("reponer() y vender() — Métodos con parámetros");

        libro3.reponer(5);
        libro4.reponer(3);

        System.out.println("\nIntento vender más ejemplares del stock:");
        libro2.vender(10);    // Falla: solo hay 5

        System.out.println("\nVenta normal:");
        libro2.vender(2);     // OK

        // ── 9. APLICAR DESCUENTO ──────────────────────────────────────────────
        separador("aplicarDescuento() — Retorna double");

        double nuevoPrecio = libro3.aplicarDescuento(20.0);
        System.out.printf("El precio de 'Clean Code' quedó en: $%.2f%n", nuevoPrecio);
        // Intento inválido
        libro3.aplicarDescuento(150.0);

        // ── 10. VENTAS A TRAVÉS DE LA LIBRERÍA ───────────────────────────────
        separador("realizarVenta() — Sobrecarga de método en Libreria");

        // Venta normal (sin descuento)
        System.out.println("Venta 1 — Sin descuento:");
        libreria.realizarVenta(cliente1, libro1, 2);

        // Venta con descuento (misma firma + parámetro extra: SOBRECARGA)
        System.out.println("\nVenta 2 — Con 10% de descuento:");
        libreria.realizarVenta(cliente2, libro2, 1, 10.0);

        // Venta fallida por saldo insuficiente
        System.out.println("\nVenta 3 — Saldo insuficiente:");
        libreria.realizarVenta(cliente3, libro1, 5);

        // Venta fallida por stock insuficiente
        System.out.println("\nVenta 4 — Stock insuficiente:");
        libreria.realizarVenta(cliente1, libro2, 20);

        // ── 11. RESUMEN FINAL ─────────────────────────────────────────────────
        separador("ESTADO FINAL DEL SISTEMA");

        libreria.mostrarClientes();
        libreria.mostrarResumen();

        separador("FIN DEL PROGRAMA");
    }

    // ── Método auxiliar para imprimir separadores legibles en consola ─────────
    private static void separador(String titulo) {
        System.out.println("\n══════════════════════════════════════════════");
        System.out.println("  " + titulo);
        System.out.println("══════════════════════════════════════════════");
    }
}
