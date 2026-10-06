package helpdesk;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class GestorTicketsTest {

    @Test
    void coleccionInicialmenteVacia() {
        GestorTickets gestor = new GestorTickets();
        assertTrue(gestor.getTickets().isEmpty());
    }

    @Test
    void identificadoresConsecutivos() {
        GestorTickets gestor = new GestorTickets();
        Ticket primero = gestor.crear("Falla el teclado");
        Ticket segundo = gestor.crear("Sin conexión a Internet");
        assertEquals(1, primero.getId());
        assertEquals(2, segundo.getId());
    }

    @Test
    void buscarDevuelveElMismoObjeto() {
        GestorTickets gestor = new GestorTickets();
        Ticket creado = gestor.crear("Falla el teclado");
        assertSame(creado, gestor.buscar(1));
    }

    @Test
    void buscarInexistenteDevuelveNull() {
        GestorTickets gestor = new GestorTickets();
        gestor.crear("Falla el teclado");
        assertNull(gestor.buscar(99));
    }

    @Test
    void creacionInvalidaNoCambiaNada() {
        GestorTickets gestor = new GestorTickets();
        gestor.crear("Falla el teclado");
        assertThrows(IllegalArgumentException.class, () -> gestor.crear("   "));
        assertEquals(1, gestor.getTickets().size());
        Ticket siguiente = gestor.crear("Sin conexión a Internet");
        assertEquals(2, siguiente.getId());
    }

    @Test
    void laListaDevueltaNoCambiaLaInterna() {
        GestorTickets gestor = new GestorTickets();
        gestor.crear("Falla el teclado");
        ArrayList<Ticket> copia = gestor.getTickets();
        copia.clear();
        assertEquals(1, gestor.getTickets().size());
    }

}