package exercise;

import java.util.stream.Collectors;
import java.util.Map;

// BEGIN
public abstract class Tag {

    private Map<String, String> attribute;
    private String name;

    public Tag(String name, Map<String, String> attribute) {
        this.name = name;
        this.attribute = attribute;
    }

    protected Map<String, String> getAttribute(){
        return attribute;
    }

    protected String getName(){
        return name;
    }

    public  String toString(){
        var html = new StringBuilder().append("<" + getName());
        for (var items : getAttribute().entrySet()) {
            html.append(String.format(" %s=\"%s\"", items.getKey(), items.getValue()));
        }
        return html.append(">").toString();
        //<img class="v-10" id="wop">
    }
}

// END
