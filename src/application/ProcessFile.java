package application;

import domain.ParsePerson;
import domain.InvalidLineFormatException;
import domain.Person;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class ProcessFile {

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

                    personas.add(ParsePerson.parse(linea, lineaNum[0]));
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

}
