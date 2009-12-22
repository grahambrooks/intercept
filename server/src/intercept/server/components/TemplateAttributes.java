package intercept.server.components;

import org.antlr.stringtemplate.StringTemplate;

import java.util.Arrays;
import java.util.List;

public class TemplateAttributes {
    protected final List<TemplateAttribute> attributes;

    public TemplateAttributes(final TemplateAttribute[] attributes) {
        this.attributes = Arrays.asList(attributes);
    }

    public void applyTo(StringTemplate template) {
        for (TemplateAttribute attribute : attributes) {
            attribute.set(template);
        }
    }

    public boolean contains(String name) {
        for (TemplateAttribute attribute : attributes) {
            if (attribute.isFor(name)) {
                return true;
            }
        }
        return false;
    }
}
