package domain;

public class ParsePerson {

    public static Person parse(String linea, int lineNumber) throws InvalidLineFormatException {
        String[] partes = linea.split(":", -1); // El segundo argumento asegura que se incluyan partes vacías

        if (partes.length < 1 || partes.length > 3) {
            throw new InvalidLineFormatException(lineNumber,
                    "La línea debe tener entre 1 y 3 campos separados por ':'");
        }

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
