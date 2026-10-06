package helpdesk;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class AplicacionHelpDesk {

    private Scanner teclado;
    private GestorTickets gestor;
    private ArchivoTickets archivo;

    public AplicacionHelpDesk(GestorTickets gestor, ArchivoTickets archivo) {
        this.teclado = new Scanner(System.in);
        this.gestor = gestor;
        this.archivo = archivo;
    }

    public static void main(String[] args) {
        ArchivoTickets archivo = new ArchivoTickets("tickets.txt");
        GestorTickets gestor = new GestorTickets();

        try {
            ArrayList<Ticket> cargados = archivo.cargar();
            for (Ticket t : cargados) {
                gestor.agregarCargado(t);
            }
        } catch (IOException | IllegalArgumentException e) {
            System.out.println("ERROR al cargar tickets.txt: " + e.getMessage());
            System.out.println("El programa se detiene y el archivo no se modifica.");
            return;
        }

        System.out.println("Incidencias cargadas: " + gestor.contarTotal());
        AplicacionHelpDesk app = new AplicacionHelpDesk(gestor, archivo);
        app.ejecutar();
    }

    public void ejecutar() {
        int opcion;
        do {
            mostrarMenu();
            opcion = leerEntero("Elige una opción: ");
            switch (opcion) {
                case 1:
                    crearIncidencia();
                    break;
                case 2:
                    listarIncidencias();
                    break;
                case 3:
                    buscarIncidencia();
                    break;
                case 4:
                    cerrarIncidencia();
                    break;
                case 5:
                    mostrarEstadisticas();
                    break;
                case 6:
                    guardarIncidencias();
                    break;
                case 0:
                    System.out.println("Programa finalizado. ¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción incorrecta. Elige un número del 0 al 6.");
            }
        } while (opcion != 0);
    }

    private void mostrarMenu() {
        System.out.println();
        System.out.println("HELPDESK DEL CENTRO");
        System.out.println("1. Crear incidencia");
        System.out.println("2. Listar incidencias");
        System.out.println("3. Buscar incidencia por identificador");
        System.out.println("4. Cerrar incidencia");
        System.out.println("5. Mostrar estadísticas");
        System.out.println("6. Guardar incidencias");
        System.out.println("0. Salir (recuerda guardar antes con la opción 6)");
    }

    private int leerEntero(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String texto = teclado.nextLine();
            try {
                return Integer.parseInt(texto.trim());
            } catch (NumberFormatException e) {
                System.out.println("Debes escribir un número. Inténtalo de nuevo.");
            }
        }
    }

    private void crearIncidencia() {
        System.out.print("Descripción: ");
        String descripcion = teclado.nextLine();
        try {
            Ticket nuevo = gestor.crear(descripcion);
            System.out.println("Incidencia creada con identificador " + nuevo.getId() + ".");
        } catch (IllegalArgumentException e) {
            System.out.println("No se ha creado la incidencia: " + e.getMessage());
        }
    }

    private void listarIncidencias() {
        ArrayList<Ticket> lista = gestor.getTickets();
        if (lista.isEmpty()) {
            System.out.println("No hay incidencias registradas.");
            return;
        }
        for (Ticket t : lista) {
            mostrarTicket(t);
        }
    }

    private void buscarIncidencia() {
        int id = leerEntero("Identificador: ");
        Ticket t = gestor.buscar(id);
        if (t == null) {
            System.out.println("No existe ninguna incidencia con el identificador " + id + ".");
        } else {
            mostrarTicket(t);
        }
    }

    private void cerrarIncidencia() {
        int id = leerEntero("Identificador de la incidencia a cerrar: ");
        Ticket t = gestor.buscar(id);
        if (t == null) {
            System.out.println("No existe ninguna incidencia con el identificador " + id + ".");
        } else if (t.cerrar()) {
            System.out.println("Incidencia " + id + " cerrada correctamente.");
        } else {
            System.out.println("La incidencia " + id + " ya estaba cerrada.");
        }
    }

    private void mostrarEstadisticas() {
        System.out.println("Total de incidencias: " + gestor.contarTotal());
        System.out.println("Abiertas: " + gestor.contarAbiertas());
        System.out.println("Cerradas: " + gestor.contarCerradas());
    }

    private void guardarIncidencias() {
        try {
            archivo.guardar(gestor.getTickets());
            System.out.println("Incidencias guardadas correctamente en tickets.txt.");
        } catch (IOException e) {
            System.out.println("ERROR: no se han podido guardar las incidencias: " + e.getMessage());
        }
    }

    private void mostrarTicket(Ticket t) {
        String estado;
        if (t.isCerrado()) {
            estado = "CERRADA";
        } else {
            estado = "ABIERTA";
        }
        System.out.println(t.getId() + " - " + t.getDescripcion() + " - " + estado);
    }

}