package intercept.configuration;

import intercept.logging.ApplicationLog;
import org.antlr.runtime.ANTLRInputStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;

import java.io.IOException;
import java.io.InputStream;

public class ConfigurationFileReader {
    private ApplicationLog logger;

    public ConfigurationFileReader(ApplicationLog logger){
        this.logger = logger;
    }

    public void readConfiguration(InputStream input, InterceptConfiguration configuration) {
        try {
            ANTLRInputStream is = new ANTLRInputStream(input);
            ConfigurationLexer lexer = new ConfigurationLexer(is);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            ConfigurationParser parser = new ConfigurationParser(tokens);

            parser.configuration(configuration);
        } catch (IOException e1) {
            logger.log(e1.getMessage());
        } catch (RecognitionException e) {
            throw new RuntimeException(e);
        }
    }
}
