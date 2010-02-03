package intercept.model;

import intercept.utils.ResultBlock;

public class Calculate {
    public static final ResultBlock<LogElement, Long> Weight = new ResultBlock<LogElement, Long>() {
        @Override
        public Long yield(LogElement item) {
            return 0L;
        }

        @Override
        public Long result() {
            return 0L;
        }
    };
}
