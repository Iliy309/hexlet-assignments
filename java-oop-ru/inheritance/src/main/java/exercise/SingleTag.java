package exercise;

import java.util.Map;
import java.lang.StringBuilder;

// BEGIN
public class SingleTag extends Tag{

    public SingleTag(String name, Map<String, String> map){
        super(name, map);
    }

    // public  String toString(){
    //     var html = new StringBuilder().append("<" + getName());
    //     for (var items : getAttribute().entrySet()) {
    //         html.append(String.format(" %s=\"%s\"", items.getKey(), items.getValue()));
    //     }
    //     return html.append(">").toString();
    //     //<img class="v-10" id="wop">
    // }
    
}
// END
