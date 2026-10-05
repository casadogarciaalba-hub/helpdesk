package helpdesk;

public class Ticket {
    private final int id;
    private final String descripcion;
    private boolean cerrado;

    public Ticket(int id, String descripcion) {
        if (id <= 0) {
            throw new IllegalArgumentException("El identificador debe ser positivo.");
        }
        if (descripcion == null || descripcion.isBlank()) {
            throw new IllegalArgumentException("La descripción no puede estar vacía.");
        }
        this.id = id;
        this.descripcion = descripcion;
        this.cerrado = false;
    }

    public boolean cerrar() {
        if (cerrado) {
            return false;
        }
        cerrado = true;
        return true;
    }

    public int getId() {
        return id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public boolean isCerrado() {
        return cerrado;
    }

}
