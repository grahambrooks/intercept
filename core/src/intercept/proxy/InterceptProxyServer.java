package intercept.proxy;

import intercept.configuration.ProxyConfig;
import intercept.logging.ApplicationLog;
import intercept.logging.EventLog;
import intercept.logging.EventLogger;
import intercept.model.LogEntry;
import intercept.utils.Function;
import intercept.utils.Utils;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

/**
 * Implementation of ProxyServer accepts incoming connections and creates a ProxyChannel to handle requests on that
 * channel.
 */
class InterceptProxyServer implements ProxyServer {
    private final ProxyConfig proxyConfig;
    private final ApplicationLog applicationLog;
    private final EventLog log = new EventLog();
    private final EventLogger logger = new EventLogger(log);
    private ServerSocket serverSocket = null;

    private final ProxyStatus[] proxyStatus = {ProxyStatus.stopped};

    private Thread proxyThread;

    protected InterceptProxyServer(ProxyConfig proxyConfig, ApplicationLog applicationLog) {
        this.proxyConfig = proxyConfig;
        this.applicationLog = applicationLog;
    }

    public void start() {
        proxyThread = new Thread() {
            @Override
            public void run() {
                if (openProxySocket()) {
                    try {
                        proxyStatus[0] = ProxyStatus.running;
                        acceptConnections();
                    } finally {
                        Utils.close(serverSocket);
                        serverSocket = null;
                        proxyStatus[0] = ProxyStatus.stopped;
                    }
                } else {
                    proxyStatus[0] = ProxyStatus.failed;
                }
            }
        };
        proxyStatus[0] = ProxyStatus.starting;
        proxyThread.start();

        waitForThreadToStart();

        if (proxyStatus[0] != ProxyStatus.running) {
            applicationLog.log("Proxy thread error : " + proxyStatus[0]);
        }
    }

    @Override
    public ProxyStatus status() {
        return proxyStatus[0];
    }

    private void waitForThreadToStart() {
        while (proxyStatus[0] == ProxyStatus.starting) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void acceptConnections() {
        while (!serverSocket.isClosed()) {
            Socket client;
            try {
                client = serverSocket.accept();
                ProxyChannel t = new ProxyChannel(client, proxyConfig, logger, applicationLog);
                t.start();
            } catch (IOException e) {
                applicationLog.log("Failed to accept connection " + e);
            }
        }
    }

    private boolean openProxySocket() {
        try {
            serverSocket = new ServerSocket(proxyConfig.getPort());
        } catch (IOException e) {
            applicationLog.log("Proxy " + proxyConfig.getName() + " thread failed to start on port " + proxyConfig.getPort() + "\n" + e.toString());

            return false;
        }

        applicationLog.trace("Started Proxy server on port " + proxyConfig.getPort());

        return true;
    }

    public void stop() {
        Utils.close(serverSocket);
        while (proxyStatus[0] == ProxyStatus.running) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        proxyThread = null;
    }

    public EventLog getLogs() {
        return log;
    }

    @Override
    public String getName() {
        return proxyConfig.getName();
    }

    public ProxyConfig getConfig() {
        return this.proxyConfig;
    }

    public <T> T response(Function<T> function) {
        return function.execute(getLogs());
    }
}
