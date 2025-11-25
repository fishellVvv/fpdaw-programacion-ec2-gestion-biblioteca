package biblioteca.simple.modelo;

import biblioteca.simple.contratos.Prestable;

// La clase Videojuego hereda de Producto (título, id, año, formato)
// e implementa la interfaz Prestable (prestar, devolver, estaPrestado)
public class Videojuego extends Producto implements Prestable {

    // Atributos específicos de un Videojuego
    private String plataforma;
    private int edadMinima;

    // Control del estado de préstamo
    private boolean prestado;      // Indica si el videojuego está prestado o no
    private Usuario prestadoA;     // Usuario al que se le ha prestado el videojuego

    // Constructor para objetos que ya existen en la base de datos
    public Videojuego(int id, String titulo, String anho, Formato formato, String plataforma, int edadMinima) {
        // Llama al constructor de Producto que recibe id
        super(id, titulo, anho, formato);
        this.plataforma = plataforma;
        this.edadMinima = edadMinima;
    }

    // Constructor para crear videojuegos nuevos (sin id inicial)
    public Videojuego(String titulo, String anho, Formato formato, String plataforma, int edadMinima) {
        // Llama al constructor de Producto que NO tiene id
        super(titulo, anho, formato);
        this.plataforma = plataforma;
        this.edadMinima = edadMinima;
    }

    // Getters para obtener información específica del videojuego
    public String getPlataforma() {
        return plataforma;
    }
    public int getEdadMinima() {
        return edadMinima;
    }

    // Implementación del método de la interfaz Prestable
    @Override
    public void prestar(Usuario u) {
        // No se puede prestar si ya está prestado
        if (prestado) throw new IllegalStateException("Ya prestado");

        this.prestado = true;
        this.prestadoA = u;
    }

    // Devuelve el videojuego (lo marca como no prestado)
    @Override
    public void devolver() {
        this.prestado = false;
        this.prestadoA = null;
    }

    // Comprueba si está prestado
    @Override
    public boolean estaPrestado() {
        return this.prestado;
    }

    // Representación en texto útil para impresión o depuración
    @Override
    public String toString() {
        return "Videojuego{" +
                "plataforma='" + plataforma + '\'' +
                ", edadMinima=" + edadMinima +
                ", id=" + id +
                ", titulo='" + titulo + '\'' +
                ", anho='" + anho + '\'' +
                ", formato=" + formato +
                '}';
    }
}
