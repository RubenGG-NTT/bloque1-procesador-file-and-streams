package JavaBasico;

public class Person {
    private String name;
    private String town;
    private int age;

    public Person(String name, String town, int age) {
        this.name = name;
        this.town = town;
        this.age = age;
    }

    @Override
    public String toString() {
        return name + " - " + (town.isEmpty() ? "Sin ciudad" : town) + " - " + (age == 0 ? "Edad desconocida" : String.valueOf(age));
    }

}
