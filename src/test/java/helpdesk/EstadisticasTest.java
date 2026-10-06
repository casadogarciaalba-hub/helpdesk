package helpdesk;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EstadisticasTest {

    @Test
    void gestorVacio() {
        GestorTickets gestor = new GestorTickets();
        assertEquals(0, gestor.contarTotal());
        assertEquals(0, gestor.contarAbiertas());
        assertEquals(0, gestor.contarCerradas());
    }

    @Test
    void dosIncidenciasAbiertas() {
        GestorTickets gestor = new GestorTickets();
        gestor.crear("Falla el teclado");
        gestor.crear("Sin conexión a Internet");
        assertEquals(2, gestor.contarTotal());
        assertEquals(2, gestor.contarAbiertas());
        assertEquals(0, gestor.contarCerradas());
    }

    @Test
    void dosIncidenciasConUnaCerrada() {
        GestorTickets gestor = new GestorTickets();
        gestor.crear("Falla el teclado");
        Ticket segunda = gestor.crear("Sin conexión a Internet");
        segunda.cerrar();
        assertEquals(2, gestor.contarTotal());
        assertEquals(1, gestor.contarAbiertas());
        assertEquals(1, gestor.contarCerradas());
    }

}