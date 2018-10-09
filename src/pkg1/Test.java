import java.util.LinkedList;
public class Test {

    LinkedList<Punkt3D> punkty = new LinkedList<>(0);
    public static void main(String[] args) {
        System.out.printf("1. Wczytaj punkt 3D\n" +
                "2. Wyświetl wszystkie punkty\n" +
                "3. Oblicz odległość\n" +
                "4. Zakończ\n" +
                "?");

        String input = System.console().readLine();
        switch (input){
            case "1":

                double x = System.console().readLine();
                double y = System.console().readLine();
                double z = System.console().readLine();
                punkty.add(new Punkt3D(x,y,z));
        }
    }


}