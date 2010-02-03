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
        ArrayList<LogElement> list = new ArrayList<LogElement>();
        list.add(new SimpleLogElement("Hello"));

        TestCollection<LogElement> collection = new TestCollection<LogElement>(list);
        final Collection<LogElement> result = new ArrayList<LogElement>();
        collection.filtered(By.type(SimpleLogElement.class), new FilterTarget<LogElement>() {
            @Override
            public void add(LogElement element) {
                result.add(element);
            }
        });

        assertThat(result.size(), is(1));

    }
}
