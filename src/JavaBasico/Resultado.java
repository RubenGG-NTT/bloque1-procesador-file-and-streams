package JavaBasico;

import java.util.ArrayList;
import java.util.List;

public class Resultado {

    private List<Person> personas = new ArrayList<>();
    private int lineasCorrectas = 0;
    private int lineasIncorrectas = 0;


    public Resultado(List<Person> personas, int lineasCorrectas, int lineasIncorrectas) {
        this.personas = personas;
        this.lineasCorrectas = lineasCorrectas;
        this.lineasIncorrectas = lineasIncorrectas;
    }

    public List<Person> getPersonas() {
        return personas;
    }

    public int getLineasCorrectas() {
        return lineasCorrectas;
    }

    public int getLineasIncorrectas() {
        return lineasIncorrectas;
    }

}
