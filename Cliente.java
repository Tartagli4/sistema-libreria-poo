/**
 * Clase Cliente
 *
 * Representa a un cliente registrado en la librería.
 * Demuestra encapsulamiento, constructores sobrecargados
 * y métodos con parámetros y retorno de valores.
 *
 * Autor: Tartaglia, Juan Ignacio
 * Asignatura: Programación Orientada a Objetos
 */
public class Cliente {

    // ── Atributos privados ────────────────────────────────────────────────────
    private String nombre;
    private String email;
    private String telefono;
    private double saldo;          // Saldo disponible en cuenta del cliente
    private int    comprasRealizadas;

    // Contador estático de clientes registrados
    private static int totalClientesRegistrados = 0;

    // ── Constructores sobrecargados ───────────────────────────────────────────

    /**
     * Constructor 1: cliente con todos los datos y saldo inicial.
     * @param nombre    Nombre completo
     * @param email     Correo electrónico
     * @param telefono  Número de teléfono
     * @param saldo     Saldo inicial en la cuenta
     */
    public Cliente(String nombre, String email, String telefono, double saldo) {
        this.nombre    = nombre;
        this.email     = email;
        this.telefono  = telefono;
        this.saldo     = saldo;
        this.comprasRealizadas = 0;
        totalClientesRegistrados++;
    }

    /**
     * Constructor 2: sobrecarga — sin saldo inicial (arranca en 0).
     * @param nombre   Nombre completo
     * @param email    Correo electrónico
     * @param telefono Número de teléfono
     */
    public Cliente(String nombre, String email, String telefono) {
        this(nombre, email, telefono, 0.0);
    }

    /**
     * Constructor 3: sobrecarga — solo nombre (datos mínimos).
     * @param nombre Nombre completo
     */
    public Cliente(String nombre) {
        this(nombre, "Sin email", "Sin teléfono", 0.0);
    }

    // ── Métodos públicos ──────────────────────────────────────────────────────

    /**
     * Muestra los datos del cliente en consola.
     */
    public void mostrarInfo() {
        System.out.println("==============================");
        System.out.println("  Cliente : " + nombre);
        System.out.println("  Email   : " + email);
        System.out.println("  Tel.    : " + telefono);
        System.out.printf ("  Saldo   : $%.2f%n", saldo);
        System.out.println("  Compras : " + comprasRealizadas);
        System.out.println("==============================");
    }

    /**
     * Carga saldo a la cuenta del cliente.
     * Método con parámetro, sin retorno.
     * @param monto Monto a cargar
     */
    public void cargarSaldo(double monto) {
        if (monto <= 0) {
            System.out.println("  [Error] El monto a cargar debe ser positivo.");
            return;
        }
        saldo += monto;
        System.out.printf("  Saldo cargado: +$%.2f | Nuevo saldo de %s: $%.2f%n",
                monto, nombre, saldo);
    }

    /**
     * Descuenta el monto de una compra del saldo del cliente.
     * Método con parámetro double y retorno booleano.
     * @param monto Monto a descontar
     * @return true si el pago fue exitoso, false si saldo insuficiente
     */
    public boolean realizarPago(double monto) {
        if (monto <= 0) {
            System.out.println("  [Error] El monto debe ser mayor a cero.");
            return false;
        }
        if (saldo < monto) {
            System.out.printf("  [Saldo insuficiente] %s necesita $%.2f pero tiene $%.2f%n",
                    nombre, monto, saldo);
            return false;
        }
        saldo -= monto;
        comprasRealizadas++;
        System.out.printf("  Pago de $%.2f procesado para %s. Saldo restante: $%.2f%n",
                monto, nombre, saldo);
        return true;
    }

    /**
     * Verifica si el cliente tiene saldo suficiente para un monto dado.
     * @param monto Monto a verificar
     * @return true si tiene saldo suficiente
     */
    public boolean puedePagar(double monto) {
        return saldo >= monto;
    }

    /**
     * Retorna un resumen del cliente en una sola línea (String).
     * Método sin parámetros con retorno String.
     * @return Resumen del cliente
     */
    public String obtenerResumen() {
        return String.format("Cliente: %-20s | Compras: %2d | Saldo: $%.2f",
                nombre, comprasRealizadas, saldo);
    }

    // ── Método estático ───────────────────────────────────────────────────────

    public static int getTotalClientesRegistrados() {
        return totalClientesRegistrados;
    }

    // ── Getters ───────────────────────────────────────────────────────────────

    public String getNombre()           { return nombre;           }
    public String getEmail()            { return email;            }
    public String getTelefono()         { return telefono;         }
    public double getSaldo()            { return saldo;            }
    public int    getComprasRealizadas(){ return comprasRealizadas; }
}
