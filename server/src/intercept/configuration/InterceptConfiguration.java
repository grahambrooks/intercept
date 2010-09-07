package intercept.configuration;

import intercept.server.ApplicationCommand;
import intercept.utils.Block;

import java.util.Queue;

public interface InterceptConfiguration {
    void parseArgs(Queue<String> args);

    void eachProxy(Block<ProxyConfig> visitor);

    int getConfigurationPort();

    ApplicationCommand getCommand();

    void setConfigurationPort(int portNumber);

    void add(ProxyConfig proxyConfig);

    Boolean hasProxy(String proxyName);
}
