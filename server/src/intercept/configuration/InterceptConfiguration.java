package intercept.configuration;

import intercept.logging.ApplicationLog;
import intercept.logging.ConsoleApplicationLog;
import intercept.server.ApplicationCommand;
import intercept.utils.Block;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class InterceptConfiguration {
    private int configurationPort;
    private final Map<String, ProxyConfig> configurations;
    private final ApplicationLog applicationLog;
    private ApplicationCommand command = ApplicationCommand.start;
    private static final Map<String, OptionHandler> optionHandlers = new HashMap<String, OptionHandler>();

    interface OptionHandler {
        void handle(Queue<String> args);
    }


    public InterceptConfiguration(final ApplicationLog applicationLog, String... args) {
        this.applicationLog = applicationLog;
        configurationPort = 2000;
        configurations = new HashMap<String, ProxyConfig>();

        optionHandlers.put("-verbose", new OptionHandler() {
            public void handle(Queue<String> args) {
                applicationLog.setVerbose();
            }
        });

        optionHandlers.put("-port", new OptionHandler() {
            public void handle(Queue<String> args) {
                if (!args.isEmpty()) {
                    String arg = args.remove();
                    try {
                        configurationPort = Integer.parseInt(arg);
                    } catch (NumberFormatException e) {
                        applicationLog.log("Invalid port number argument \"" + arg + "\" ignored");
                    }
                } else {
                    applicationLog.log("Invalid option parameter to -port - Integer port number required");
                }
            }
        });

        optionHandlers.put("-stop", new OptionHandler() {
            public void handle(Queue<String> args) {
                command = ApplicationCommand.stop;
            }
        });

        Queue<String> commandLine = new LinkedList<String>();
        for (String arg : args) {
            commandLine.add(arg);
        }
        parseArgs(commandLine);
    }

    public void parseArgs(Queue<String> args) {
        parseOptions(args);

        ConfigurationFileReader reader = new ConfigurationFileReader(applicationLog);
        try {

            if (args.size() > 0) {
                reader.readConfiguration(new FileInputStream(args.remove()), this);
            } else {
                reader.readConfiguration(new FileInputStream("intercept.config"), this);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void parseOptions(Queue<String> args) {
        while (!args.isEmpty() && args.peek().startsWith("-")) {
            String option = args.remove();
            OptionHandler handler = optionHandlers.get(option);
            if (handler != null) {
                handler.handle(args);
            } else {
                applicationLog.log("Invalid option " + option + " ignored");
            }


        }
    }

    public void eachProxy(Block<ProxyConfig> visitor) {
        for (String name : configurations.keySet()) {
            visitor.yield(configurations.get(name));
        }
    }

    public int getConfigurationPort() {
        return configurationPort;
    }

    public ApplicationCommand getCommand() {
        return command;
    }

    public void setConfigurationPort(int portNumber) {
        this.configurationPort = portNumber;
    }

    public void add(ProxyConfig proxyConfig) {
        configurations.put(proxyConfig.getName(), proxyConfig);
    }

    public Boolean hasProxy(String proxyName) {
        return configurations.containsKey(proxyName);
    }
}
