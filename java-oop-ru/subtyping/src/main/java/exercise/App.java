package exercise;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

// BEGIN

public class App {

    public static KeyValueStorage swapKeyValue(KeyValueStorage storage) {
        var swapStorage = new InMemoryKV();
        for (var items : storage.toMap().entrySet()) {
            swapStorage.set(items.getValue(), items.getKey());
        }
        return swapStorage;
    }

}
// END
