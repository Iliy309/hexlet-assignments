package exercise.daytime;
import jakarta.annotation.PostConstruct;

import java.util.logging.Logger;

public class Day implements Daytime {
    private String name = "day";

    public String getName() {
        return name;
    }

    // BEGIN
    @PostConstruct
    public  void logger(){
        System.out.println("Autowired createded bean " + name );
    }
    // END
}
