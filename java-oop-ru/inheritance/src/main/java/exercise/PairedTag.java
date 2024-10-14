package exercise;

import java.util.Map;
import java.util.ArrayList;
import java.util.List;

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
    }
}

// END
