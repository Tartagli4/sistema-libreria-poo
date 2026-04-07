/**
 * Clase Libro
 *
 * Representa un libro disponible en la librería.
 * Aplica encapsulamiento (atributos private, métodos public),
 * constructores sobrecargados y miembro estático.
 *
 * Autor: Tartaglia, Juan Ignacio
 * Asignatura: Programación Orientada a Objetos
 */
public class Libro {

    // ── Atributos privados (encapsulamiento) ─────────────────────────────────
    private String titulo;
    private String autor;
    private String isbn;
    private double precio;
    private int    stock;

    // Atributo estático: contador compartido por TODOS los objetos Libro
    private static int totalLibrosRegistrados = 0;

    // ── Constructores sobrecargados ───────────────────────────────────────────

    /**
     * Constructor 1: información completa del libro.
     * @param titulo  Título del libro
     * @param autor   Autor del libro
     * @param isbn    Código ISBN
     * @param precio  Precio de venta
     * @param stock   Cantidad disponible en depósito
     */
    public Libro(String titulo, String autor, String isbn, double precio, int stock) {
        this.titulo = titulo;
        this.autor  = autor;
        this.isbn   = isbn;
        this.precio = precio;
        this.stock  = stock;
        totalLibrosRegistrados++;   // Se incrementa el contador estático
    }

    /**
     * Constructor 2: sobrecarga — sin ISBN ni stock inicial.
     * Se asigna ISBN "Sin registrar" y stock 0 por defecto.
     * @param titulo Título del libro
     * @param autor  Autor del libro
     * @param precio Precio de venta
     */
    public Libro(String titulo, String autor, double precio) {
        this(titulo, autor, "Sin registrar", precio, 0);
    }

    /**
     * Constructor 3: sobrecarga — solo título y autor, sin precio ni stock.
     * Útil para registrar un libro pendiente de precio.
     * @param titulo Título del libro
     * @param autor  Autor del libro
     */
    public Libro(String titulo, String autor) {
        this(titulo, autor, "Sin registrar", 0.0, 0);
    }

    // ── Métodos públicos ──────────────────────────────────────────────────────

    /**
     * Muestra la información completa del libro en consola.
     * Método sin retorno de valor (void).
     */
    public void mostrarInfo() {
        System.out.println("------------------------------");
        System.out.println("  Título  : " + titulo);
        System.out.println("  Autor   : " + autor);
        System.out.println("  ISBN    : " + isbn);
        System.out.printf ("  Precio  : $%.2f%n", precio);
        System.out.println("  Stock   : " + stock + " unidades");
        System.out.println("------------------------------");
    }

    /**
     * Vende una cantidad de ejemplares del libro.
     * Método con parámetro y retorno de valor booleano.
     * @param cantidad Número de ejemplares a vender
     * @return true si la venta fue exitosa, false si no hay stock suficiente
     */
    public boolean vender(int cantidad) {
        if (cantidad <= 0) {
            System.out.println("  [Error] La cantidad debe ser mayor a cero.");
            return false;
        }
        if (stock < cantidad) {
            System.out.println("  [Sin stock] No hay suficientes ejemplares de \"" + titulo + "\".");
            System.out.println("  Stock disponible: " + stock);
            return false;
        }
        stock -= cantidad;
        System.out.println("  Venta realizada: " + cantidad + " ejemplar(es) de \"" + titulo + "\".");
        return true;
    }

    /**
     * Repone stock del libro.
     * Método con parámetro, sin retorno de valor.
     * @param cantidad Ejemplares a agregar al stock
     */
    public void reponer(int cantidad) {
        if (cantidad <= 0) {
            System.out.println("  [Error] La cantidad a reponer debe ser mayor a cero.");
            return;
        }
        stock += cantidad;
        System.out.println("  Reposición: +" + cantidad + " ejemplares de \"" + titulo + "\". Stock actual: " + stock);
    }

    /**
     * Aplica un descuento porcentual al precio del libro.
     * Método con parámetro double, retorna el nuevo precio.
     * @param porcentaje Porcentaje de descuento (0-100)
     * @return Nuevo precio con el descuento aplicado
     */
    public double aplicarDescuento(double porcentaje) {
        if (porcentaje < 0 || porcentaje > 100) {
            System.out.println("  [Error] El porcentaje debe estar entre 0 y 100.");
            return precio;
        }
        precio = precio - (precio * porcentaje / 100);
        System.out.printf("  Descuento del %.0f%% aplicado a \"%s\". Nuevo precio: $%.2f%n",
                porcentaje, titulo, precio);
        return precio;
    }

    /**
     * Retorna el precio total de una cantidad de ejemplares.
     * Sobrecarga del concepto de cálculo de precio.
     * @param cantidad Número de ejemplares
     * @return Precio total
     */
    public double calcularTotal(int cantidad) {
        return precio * cantidad;
    }

    /**
     * Retorna el precio total aplicando un descuento especial.
     * Sobrecarga de calcularTotal — misma operación, distinta firma.
     * @param cantidad   Número de ejemplares
     * @param descuento  Porcentaje de descuento a aplicar
     * @return Precio total con descuento
     */
    public double calcularTotal(int cantidad, double descuento) {
        double precioConDescuento = precio - (precio * descuento / 100);
        return precioConDescuento * cantidad;
    }

    /**
     * Verifica si el libro tiene stock disponible.
     * Método sin parámetros con retorno booleano.
     * @return true si hay al menos un ejemplar en stock
     */
    public boolean tieneStock() {
        return stock > 0;
    }

    // ── Método estático ───────────────────────────────────────────────────────

    /**
     * Retorna la cantidad total de libros registrados en el sistema.
     * Al ser estático, pertenece a la CLASE, no a un objeto particular.
     * @return Total de instancias Libro creadas
     */
    public static int getTotalLibrosRegistrados() {
        return totalLibrosRegistrados;
    }

    // ── Getters ───────────────────────────────────────────────────────────────

    public String getTitulo()  { return titulo; }
    public String getAutor()   { return autor;  }
    public String getIsbn()    { return isbn;   }
    public double getPrecio()  { return precio; }
    public int    getStock()   { return stock;  }
}
