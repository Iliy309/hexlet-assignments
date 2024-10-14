package exercise;

import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// BEGIN
public class PairedTag extends Tag {

    private String body;
    private List<Tag> childTags;

    public PairedTag(String name, Map<String, String> map, String body, List<Tag> childTags) {
        super(name, map);
        this.body = body;
        this.childTags = new ArrayList<>(childTags);
    }

    @Override
    public String toString() {
        System.out.println(super.toString());
        var html = new StringBuilder()
                            .append(super.toString())
                            .append(body);
        childTags.forEach(tag -> {
            html.append(tag.toString());
        });

        html.append("</" + getName() + ">");

        return html.toString();
        // <p id="abc">Text paragraph</p>
        // Tag div = new PairedTag(
        // "div",
        // Map.of("class", "y-5"),
        // "",
        // List.of(
        // new SingleTag("br", Map.of("id", "s")),
        // new SingleTag("hr", Map.of("class", "a-5"))
        // )
        // <div class="y-5"><br id="s"><hr class="a-5"></div>
    }

}
// END
