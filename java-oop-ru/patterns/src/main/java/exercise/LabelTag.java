package exercise;

import java.util.List;
import java.text.Collator;
import java.util.ArrayList;

// BEGIN
public class LabelTag implements TagInterface {

    private String label;
    private List<TagInterface> tags;

    public LabelTag(String label, TagInterface tag) {
        this.label = label;
        this.tags = new ArrayList<TagInterface>(List.of(tag));
    }

    public String render() {
        var renderValue = new StringBuilder();
        tags.forEach(tag -> {
            renderValue.append("<label>")
                    .append(label)
                    .append(tag.render())
                    .append("</label>");
        });

        return renderValue.toString();
        // <label>Press Submit<input type="submit" value="Save"></label>
    }

}
// END
