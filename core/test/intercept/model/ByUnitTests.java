package intercept.model;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ByUnitTests {
    class TestCollection<T> {
        private Collection<T> c;

        public TestCollection(Collection<T> c) {
            this.c = c;
        }

        public void filtered(LogFilter<T> logFilter, FilterTarget<T> filterTarget) {
            for (T o : c) {
                logFilter.filter(o, filterTarget);
            }
        }
    }

    @Test
    public void typeFilterAddsObjectsThatMatchType() {
        ArrayList<SimpleLogElement> list = new ArrayList<SimpleLogElement>();
        list.add(new SimpleLogElement("Hello"));

        TestCollection<SimpleLogElement> collection = new TestCollection<SimpleLogElement>(list);
        final Collection<SimpleLogElement> result = new ArrayList<SimpleLogElement>();
        collection.filtered(By.type(SimpleLogElement.class), new FilterTarget<SimpleLogElement>() {
            public void add(SimpleLogElement element) {
                result.add(element);
            }
        });

        assertThat(result.size(), is(1));

    }
}
