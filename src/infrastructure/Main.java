package infrastructure;

import application.ProcessFile;
import application.Resultado;

import java.util.Optional;

public class Main {
    public static void main(String[] args) {

        validArgs(args);

        Resultado resultado = ProcessFile.processFile(args[0]);

        // Mostrar personas válidas
        System.out.println("\n--- Personas Cargadas Correctamente ---");
        resultado.getPersonas().forEach(System.out::println);

        // Imprimir resultados
        System.out.println("\n--- Resumen ---");
        System.out.println("Total de personas cargadas correctamente: " + resultado.getLineasCorrectas());
        System.out.println("Total de líneas incorrectas: " + resultado.getLineasIncorrectas());

        filtrosStreams(resultado);
    }

    public static void validArgs(String[] args) {
        if (args.length == 0) {
            System.out.println("No se han proporcionado argumentos.");
            System.exit(1);
        }
    }

    public static void filtrosStreams(Resultado resultado) {
        System.out.println("\n--- Personas menores de 25 años ---");
        resultado.getPersonas().stream()
                .filter(p -> p.getAge() < 25 && p.getAge() != 0)
                .forEach(p ->
                        System.out.println("Name: " + p.getName() +
                                ". Town: " + (p.getTown().trim().isEmpty() ? "unknown" : p.getTown().trim()) +
                                ". Age: " + p.getAge())
                );

        System.out.println("\n--- Eliminar personas cuyo nombre empieza por 'A' ---");
        resultado.getPersonas().stream()
                .filter(p -> !p.getName().startsWith("A"))
                .forEach(p ->
                        System.out.println("Name: " + p.getName() +
                                ". Town: " + (p.getTown().trim().isEmpty() ? "unknown" : p.getTown().trim()) +
                                ". Age: " + (p.getAge() == 0 ? "unknown" : p.getAge()))
                );

        System.out.println("\n--- Primera persona con ciudad 'Madrid' ---");
        Optional<String> primerPersonaMadrid = resultado.getPersonas().stream()
                .filter(p -> p.getAge() < 25 && p.getAge() != 0)
                .filter(p -> p.getTown().equalsIgnoreCase("Madrid"))
                .map(p -> "Name: " + p.getName() + ". Town: " + p.getTown() + ". Age: " + (p.getAge() == 0 ? "unknown" : p.getAge()))
                .findFirst();

        if (primerPersonaMadrid.isPresent()) {
            System.out.println(primerPersonaMadrid.get());
        } else {
            System.out.println("No se encontró ninguna persona con ciudad 'Madrid'.");
        }

        System.out.println("\n--- Primera persona con ciudad 'Barcelona' ---");
        Optional<String> primerPersonaBarcelona = resultado.getPersonas().stream()
                .filter(p -> p.getAge() < 25 && p.getAge() != 0)
                .filter(p -> p.getTown().equalsIgnoreCase("Barcelona"))
                .map(p -> "Name: " + p.getName() + ". Town: " + p.getTown() + ". Age: " + (p.getAge() == 0 ? "unknown" : p.getAge()))
                .findFirst();

        if (primerPersonaBarcelona.isPresent()) {
            System.out.println(primerPersonaBarcelona.get());
        } else {
            System.out.println("No se encontró ninguna persona con ciudad 'Barcelona'.");
        }

        System.out.println();
    }

}