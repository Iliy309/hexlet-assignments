package exercise;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

// BEGIN

public class App {

    public static void swapKeyValue(KeyValueStorage storage) {
        for (var items : storage.toMap().entrySet()) {
            storage.unset(items.getKey());
            storage.set(items.getValue(),items.getKey());
        }
    }
}
// END
