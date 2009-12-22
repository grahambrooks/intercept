package intercept.server.components;

import org.antlr.stringtemplate.StringTemplate;

public class TemplateAttribute {
    private String name;
    private Object value;

    public TemplateAttribute(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    public final void set(StringTemplate template) {
        template.setAttribute(name, value);
    }

    public boolean isFor(String name) {
        return this.name.equals(name);
    }
}
