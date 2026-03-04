package application;

import java.util.Optional;

public class FiltrosStream {

    public static void filtrosStreams(Resultado resultado) {

        filtroMenores25(resultado);
        eliminarPersonasConNombreA(resultado);
        primeraPersonaConCiudadMadrid(resultado);
        primeraPersonaConCiudadBarcelona(resultado);

        System.out.println();
    }

    public static void filtroMenores25(Resultado resultado) {
        System.out.println("\n--- Personas menores de 25 años ---");
        resultado.getPersonas().stream()
                .filter(p -> p.getAge() < 25 && p.getAge() != 0)
                .forEach(p ->
                        System.out.println("Name: " + p.getName() +
                                ". Town: " + (p.getTown().trim().isEmpty() ? "unknown" : p.getTown().trim()) +
                                ". Age: " + p.getAge())
                );
    }

    public static void eliminarPersonasConNombreA(Resultado resultado) {
        System.out.println("\n--- Eliminar personas cuyo nombre empieza por 'A' ---");
        resultado.getPersonas().stream()
                .filter(p -> !p.getName().startsWith("A"))
                .forEach(p ->
                        System.out.println("Name: " + p.getName() +
                                ". Town: " + (p.getTown().trim().isEmpty() ? "unknown" : p.getTown().trim()) +
                                ". Age: " + (p.getAge() == 0 ? "unknown" : p.getAge()))
                );
    }

    public static void primeraPersonaConCiudadMadrid(Resultado resultado) {
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
    }

    public static void primeraPersonaConCiudadBarcelona(Resultado resultado) {
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
    }

}
