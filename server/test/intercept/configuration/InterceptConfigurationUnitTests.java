package intercept.configuration;

import intercept.logging.ApplicationLog;
import intercept.logging.ConsoleApplicationLog;
import intercept.server.ApplicationCommand;
import intercept.utils.Block;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;
import static org.mockito.Mockito.*;

import java.util.LinkedList;

public class InterceptConfigurationUnitTests {
    ApplicationLog nullLogger = mock(ApplicationLog.class);

    @Test
    public void configurationDefaultsToStartingServer() {
        InterceptConfiguration configuration = new InterceptConfiguration(nullLogger);

        configuration.parseArgs(new LinkedList<String>());

        assertThat(configuration.getCommand(), is(ApplicationCommand.start));
    }

    @Test
    public void portNumberCanBePassedAsCommandLineOption() {
        InterceptConfiguration configuration = new InterceptConfiguration(nullLogger);
        LinkedList<String> options = new LinkedList<String>();
        options.add("-port");
        options.add("123");
        configuration.parseArgs(options);

        assertThat(configuration.getConfigurationPort(), equalTo(123));
    }

    @Test
    public void acceptsConfigurationFileParameter() {
        InterceptConfiguration configuration = new InterceptConfiguration(nullLogger);
        LinkedList<String> options = new LinkedList<String>();
        options.add("-port");
        options.add("123");
        options.add("intercept.config");
        configuration.parseArgs(options);

        Block<ProxyConfig> mockVisitor = mock(Block.class);

        configuration.eachProxy(mockVisitor);

        verify(mockVisitor, times(1)).yield((ProxyConfig) anyObject());
    }

    @Test
    public void configurationLoadsConfigurationFileIfNoArgumentsSupplied() {
        InterceptConfiguration configuration = new InterceptConfiguration(nullLogger);
        configuration.parseArgs(new LinkedList<String>());

        Block<ProxyConfig> mockVisitor = mock(Block.class);

        configuration.eachProxy(mockVisitor);

        verify(mockVisitor, times(1)).yield((ProxyConfig) anyObject());
    }

    @Test
    public void defaultConfigurationPortIs200() {
        InterceptConfiguration configuration = new InterceptConfiguration(nullLogger);
        configuration.parseArgs(new LinkedList<String>());

        assertThat(configuration.getConfigurationPort(), equalTo(2000));
    }

    @Test
    public void warningGivenForInvalidPortNumber() {
        ConsoleApplicationLog mockLogger = mock(ConsoleApplicationLog.class);
        InterceptConfiguration configuration = new InterceptConfiguration(mockLogger);
        LinkedList<String> options = new LinkedList<String>();
        options.add("-port");
        options.add("ewoir");

        configuration.parseArgs(options);

        verify(mockLogger).log("Invalid port number argument \"ewoir\" ignored");
    }
}
