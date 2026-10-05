package helpdesk;

import java.util.ArrayList;

public class GestorTickets {
    private ArrayList<Ticket> tickets;
    private int siguienteId;

    public GestorTickets() {
        tickets = new ArrayList<>();
        siguienteId = 1;
    }

    public Ticket crear(String descripcion) {
        Ticket nuevo = new Ticket(siguienteId, descripcion);
        tickets.add(nuevo);
        siguienteId++;
        return nuevo;
    }

    public Ticket buscar(int id) {
        for (Ticket t : tickets) {
            if (t.getId() == id) {
                return t;
            }
        }
        return null;
    }

    public ArrayList<Ticket> getTickets() {
        return new ArrayList<>(tickets);
    }

    public int contarTotal() {
        return tickets.size();
    }

    public int contarAbiertas() {
        int abiertas = 0;
        for (Ticket t : tickets) {
            if (!t.isCerrado()) {
                abiertas++;
            }
        }
        return abiertas;
    }

    public int contarCerradas() {
        int cerradas = 0;
        for (Ticket t : tickets) {
            if (t.isCerrado()) {
                cerradas++;
            }
        }
        return cerradas;
    }

    public void agregarCargado(Ticket t) {
        if (buscar(t.getId()) != null) {
            throw new IllegalArgumentException("Identificador repetido: " + t.getId());
        }
        tickets.add(t);
        if (t.getId() >= siguienteId) {
            siguienteId = t.getId() + 1;
        }
    }
}
