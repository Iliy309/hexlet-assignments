package exercise;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

// BEGIN

public class App {

    public static void swapKeyValue(KeyValueStorage storage) {
        var copy = new HashMap<>(storage.toMap());
        storage.toMap().clear();
        for (var items : copy.entrySet()) {
            storage.set(items.getValue(), items.getKey());
        }
    }
}
// END
