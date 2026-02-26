package JavaBasico;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Ejercicio1 {
    public static void main(String[] args) {

        validArgs(args);

        Resultado resultado = processFile(args[0]);

        // Mostrar personas válidas
        System.out.println("\n--- Personas Cargadas Correctamente ---");
        resultado.getPersonas().forEach(System.out::println);

        // Imprimir resultados
        System.out.println("\n--- Resumen ---");
        System.out.println("Total de personas cargadas correctamente: " + resultado.getLineasCorrectas());
        System.out.println("Total de líneas incorrectas: " + resultado.getLineasIncorrectas());
    }

    public static void validArgs(String[] args) {
        if (args.length == 0) {
            System.out.println("No se han proporcionado argumentos.");
            System.exit(1);
        }
    }

    public static Resultado processFile(String ruta) {

        List<Person> personas = new ArrayList<>();
        int[] lineasCorrectas = {0};
        int[] lineasIncorrectas = {0};

        // Abrir y leer el archivo utilizando Streams
        try (Stream<String> lineas = Files.lines(Paths.get(ruta))) {


            int[] lineaNum = {0}; // Contador de líneas para el manejo de excepciones

            lineas.forEach(linea -> {
                lineaNum[0]++;

                try {

                    personas.add(parsePerson(linea, lineaNum[0]));
                    lineasCorrectas[0]++; // Incrementamos las líneas correctas

                } catch (InvalidLineFormatException e) {

                    // Imprimir en consola el error y la línea
                    System.err.println("Error en la línea " + e.getLineNumber() + ": " + e.getMessage());
                    System.err.println("Contenido de la línea: " + linea);
                    lineasIncorrectas[0]++; // Incrementamos las líneas incorrectas

                }
            });
        } catch (IOException e) {

            System.out.println("Error al leer el archivo: " + e.getMessage());

        }
        return new Resultado(personas, lineasCorrectas[0], lineasIncorrectas[0]);
    }

    private static Person parsePerson(String linea, int lineNumber) throws InvalidLineFormatException {
        String[] partes = linea.split(":", -1); // El segundo argumento asegura que se incluyan partes vacías

        // Validar nombre obligatorio
        if (partes.length == 0 || partes[0].isEmpty()) {
            throw new InvalidLineFormatException(lineNumber, "Campo name que es obligatorio vacío " + partes.length);
        }

        // Extraer campos
        String name = partes[0].trim();
        String town = (partes.length >= 2) ? partes[1] : "";
        int age = 0;

        // Edad opcional
        if (partes.length >= 3 && !partes[2].isEmpty()) {
            try {

                age = Integer.parseInt(partes[2]);

            } catch (NumberFormatException e) {

                throw new InvalidLineFormatException(lineNumber, "Edad no válida: " + partes[2] + "'");

            }
        }
        return new Person(name, town, age);
    }
}