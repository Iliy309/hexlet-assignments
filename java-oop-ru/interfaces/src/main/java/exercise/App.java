package exercise;

import java.util.List;

// BEGIN
public class App {

    public static void main(String[] args) {
    }

    public static List<String> buildApartmentsList(List<Home> homes, int index) {
        return homes.stream()
                .sorted((home1, home2) -> home1.compareTo(home2))
                .map(home -> home.toString())
                .limit(index)
                .toList();
    }
}

// END
