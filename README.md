# HelpDesk del centro

Aplicación de consola en Java para registrar, consultar, cerrar y guardar incidencias.

## Cómo ejecutar

- **Programa:** abrir `AplicacionHelpDesk.java` y pulsar el triángulo verde junto a `main`.
- **Pruebas:** pulsar dos veces Ctrl, escribir `mvn test` y pulsar Enter.

Las incidencias se guardan en `tickets.txt`, en la carpeta del proyecto.

## Responsabilidades de las clases

| Clase | Responsabilidad |
|---|---|
| `Ticket` | Datos de una incidencia (identificador, descripción, estado), validación y cierre. |
| `GestorTickets` | Lista de tickets, identificadores, creación, búsqueda y estadísticas. |
| `ArchivoTickets` | Leer y guardar las incidencias en `tickets.txt`. |
| `AplicacionHelpDesk` | Menú, teclado (Scanner), mensajes y coordinación. |

## Formato del archivo

Una incidencia por línea: `id;cerrado;descripcion`

```
1;false;Falla el teclado
2;true;Sin conexión a Internet
```

## Limitaciones conocidas

- No hay guardado automático: hay que usar la opción 6 antes de salir.
- Si `tickets.txt` tiene datos incorrectos, el programa no arranca y hay que corregirlo a mano.
- No se pueden borrar, editar ni reabrir incidencias.