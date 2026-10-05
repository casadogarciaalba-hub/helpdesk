package helpdesk;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class ArchivoTickets {

    private String nombreArchivo;

    public ArchivoTickets(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public void guardar(ArrayList<Ticket> tickets) throws IOException {
        try (PrintWriter escritor = new PrintWriter(new FileWriter(nombreArchivo))) {
            for (Ticket t : tickets) {
                escritor.println(t.getId() + ";" + t.isCerrado() + ";" + t.getDescripcion());
            }
        }
    }

    public ArrayList<Ticket> cargar() throws IOException {
        ArrayList<Ticket> lista = new ArrayList<>();
        File archivo = new File(nombreArchivo);
        if (!archivo.exists()) {
            return lista;
        }
        try (BufferedReader lector = new BufferedReader(new FileReader(archivo))) {
            String linea = lector.readLine();
            while (linea != null) {
                lista.add(convertirLinea(linea));
                linea = lector.readLine();
            }
        }
        return lista;
    }

    private Ticket convertirLinea(String linea) {
        String[] partes = linea.split(";", 3);
        if (partes.length != 3) {
            throw new IllegalArgumentException("Línea incorrecta: " + linea);
        }
        int id = Integer.parseInt(partes[0]);
        String estado = partes[1];
        if (!estado.equals("true") && !estado.equals("false")) {
            throw new IllegalArgumentException("Estado incorrecto: " + linea);
        }
        Ticket t = new Ticket(id, partes[2]);
        if (estado.equals("true")) {
            t.cerrar();
        }
        return t;
    }

}
