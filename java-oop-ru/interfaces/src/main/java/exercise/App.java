package exercise;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

// BEGIN
public class App {

    public static void main(String[] args) {
        Locale.setDefault(Locale.US); 
        List<Home> apartments = new ArrayList<>(List.of(
            new Flat(41, 3, 10),
            new Cottage(125.5, 2),
            new Flat(80, 10, 2),
            new Cottage(150, 3)
        ));

        List<String> result = App.buildApartmentsList(apartments, 3);

        // System.out.println(result.toString());
    }

    public static List<String> buildApartmentsList(List<Home> homes, int index){
        return homes.stream()
            .sorted((home1, home2) -> home1.compareTo(home2))
            .map(home -> home.toString())
            .limit(index)
            .toList();
    }
}

// END
