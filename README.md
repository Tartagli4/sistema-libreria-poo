*Sistema de Librería — POO en Java
Alumno: Tartaglia, Juan Ignacio
Asignatura: Programación Avanzada
Proyecto: Sistema de gestión de una librería


Estructura del proyecto (NetBeans)
SistemaLibreria/
├── src/
│   ├── Libro.java       ← Clase Libro con atributos, constructores sobrecargados y métodos
│   ├── Cliente.java     ← Clase Cliente con encapsulamiento y métodos
│   ├── Libreria.java    ← Clase Libreria que gestiona libros, clientes y ventas
│   └── Main.java        ← Clase principal: demuestra todos los conceptos
└── README.md

Descripción de las clases

Libro: Representa un libro del catálogo.
Atributos: titulo, autor, isbn, precio, stock (todos private)
Atributo estático: totalLibrosRegistrados
Constructores sobrecargados:
Libro(titulo, autor, isbn, precio, stock) — información completa
Libro(titulo, autor, precio) — sin ISBN ni stock
Libro(titulo, autor) — solo identificación básica
Métodos destacados:
vender(int cantidad) → boolean
reponer(int cantidad) → void
aplicarDescuento(double porcentaje) → double
calcularTotal(int cantidad) → double (sobrecargado)
calcularTotal(int cantidad, double descuento) → double (sobrecargado)
tieneStock() → boolean
getTotalLibrosRegistrados() → int (estático)



Cliente: Representa a un cliente registrado en la librería.
Atributos: nombre, email, telefono, saldo, comprasRealizadas (todos private)
Constructores sobrecargados:
Cliente(nombre, email, telefono, saldo) — datos completos
Cliente(nombre, email, telefono) — sin saldo inicial
Cliente(nombre) — solo nombre
Métodos destacados:
cargarSaldo(double monto) → void
realizarPago(double monto) → boolean
puedePagar(double monto) → boolean
obtenerResumen() → String



Libreria: Gestiona el catálogo y las operaciones.
Constructores sobrecargados:
Libreria(nombre, capLibros, capClientes) — capacidades personalizadas
Libreria(nombre) — capacidades por defecto (50 libros, 100 clientes)
Métodos de búsqueda sobrecargados:
buscarLibroPorTitulo(String) → Libro
buscarLibroPorAutor(String) → Libro
Ventas sobrecargadas:
realizarVenta(cliente, libro, cantidad) — precio normal
realizarVenta(cliente, libro, cantidad, descuento) — con descuento
