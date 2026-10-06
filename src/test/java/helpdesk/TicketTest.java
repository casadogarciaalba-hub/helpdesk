package helpdesk;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TicketTest {

    @Test
    void ticketNuevoEmpiezaAbierto() {
        Ticket t = new Ticket(1, "Falla el teclado");
        assertFalse(t.isCerrado());
    }

    @Test
    void cerrarTicketAbierto() {
        Ticket t = new Ticket(1, "Falla el teclado");
        assertTrue(t.cerrar());
        assertTrue(t.isCerrado());
    }

    @Test
    void cerrarTicketYaCerrado() {
        Ticket t = new Ticket(1, "Falla el teclado");
        t.cerrar();
        assertFalse(t.cerrar());
        assertTrue(t.isCerrado());
    }

    @Test
    void rechazaDescripcionEnBlanco() {
        assertThrows(IllegalArgumentException.class, () -> new Ticket(1, "   "));
    }

    @Test
    void rechazaDescripcionNula() {
        assertThrows(IllegalArgumentException.class, () -> new Ticket(1, null));
    }

    @Test
    void rechazaIdentificadorNoPositivo() {
        assertThrows(IllegalArgumentException.class, () -> new Ticket(0, "Falla el teclado"));
    }

}