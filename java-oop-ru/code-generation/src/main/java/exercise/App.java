package exercise;

import java.nio.file.Path;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

import javax.print.event.PrintJobAdapter;

// BEGIN
public class App {

    public static void save(Path path, Car car){
        try {
            var data = car.serialize();
            Files.write(path, data.getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Car extract(Path path){
        Car car = null;
        try {
            var json = Files.readAllBytes(path).toString();
            car = Car.deserialize(json);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return car;
    }
}
// END
