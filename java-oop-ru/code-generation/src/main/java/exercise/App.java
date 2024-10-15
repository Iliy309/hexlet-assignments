package exercise;

import java.nio.file.Path;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

// BEGIN
public class App {
    public static void main(String[] args) {
        System.out.println("hello world");
        var path = Path.of("src/test/resources/car.json");
        Car instance = App.extract(path);
        System.out.println(instance);
        User user = instance.getOwner();
        System.out.println(user);
    }

    public static void save(Path path, Car car) {
        try {
            var data = car.serialize();
            Files.write(path, data.getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Car extract(Path path) {
        Car car = null;
        try {
            var json = new String(Files.readAllBytes(path));
            car = Car.deserialize(json);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return car;
    }
}
// END
