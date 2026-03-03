import application.ProcessFile;
import application.Resultado;

import static application.ProcessFile.processFile;

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
    }

    public static void validArgs(String[] args) {
        if (args.length == 0) {
            System.out.println("No se han proporcionado argumentos.");
            System.exit(1);
        }
    }

}