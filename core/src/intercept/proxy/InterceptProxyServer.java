package intercept.proxy;

import intercept.configuration.ProxyConfig;
import intercept.logging.ApplicationLog;
import intercept.logging.DefaultEventLogger;
import intercept.logging.EventLog;
import intercept.logging.EventLogger;
import intercept.utils.Function;
import intercept.utils.Utils;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static intercept.proxy.ProxyStatus.*;

/**
 * Implementation of ProxyServer accepts incoming connections and creates a ProxyChannel to handle requests on that
 * channel.
 */
class InterceptProxyServer implements ProxyServer {
    private final ProxyConfig proxyConfig;
    private final ApplicationLog applicationLog;
    private final EventLog log = new EventLog();
    private final EventLogger logger = new DefaultEventLogger(log);
    private ServerSocket serverSocket = null;

    private final transient ProxyStatus[] proxyStatus = {stopped};

    private Thread proxyThread;

    protected InterceptProxyServer(ProxyConfig proxyConfig, ApplicationLog applicationLog) {
        this.proxyConfig = proxyConfig;
        this.applicationLog = applicationLog;
    }

    public void start() {
        proxyThread = createProxyWorkerThread();
        startProxyWorkerThread();

        if (!is(running)) {
            applicationLog.log("Proxy thread error : " + proxyStatus[0]);
        }
    }

    private Thread createProxyWorkerThread() {
        proxyStatus[0] = starting;
        return new Thread() {
            @Override
            public void run() {
                if (openProxySocket()) {
                    try {
                        proxyStatus[0] = running;
                        acceptConnections();
                    } finally {
                        Utils.close(serverSocket);
                        serverSocket = null;
                        proxyStatus[0] = stopped;
                    }
                } else {
                    proxyStatus[0] = failed;
                }
            }
        };
    }

    public ProxyStatus status() {
        return proxyStatus[0];
    }

    private void startProxyWorkerThread() {
        proxyThread.start();

        while (is(starting)) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private boolean is(ProxyStatus status) {
        return status() == status;
    }

    private void acceptConnections() {
        while (!serverSocket.isClosed()) {
            Socket client;
            try {
                client = serverSocket.accept();
                ProxyChannel t = new ProxyChannel(client, proxyConfig, logger, applicationLog);
                t.start();
                applicationLog.trace("Accepted connection");
            } catch (IOException e) {
                applicationLog.log("Failed to accept connection " + e);
            }
        }
    }

    private boolean openProxySocket() {
        try {
            serverSocket = new ServerSocket(proxyConfig.getPort());
            applicationLog.trace("Started Proxy server on port " + proxyConfig.getPort());
            return true;
        } catch (IOException e) {
            applicationLog.log("Proxy " + proxyConfig.getName() + " thread failed to start on port " + proxyConfig.getPort() + "\n" + e.toString());
            return false;
        }
    }

    public void stop() {
        Utils.close(serverSocket);
        while (proxyStatus[0] == running) {
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

    public <T> T response(Function<T, EventLog> function) {
        return function.execute(getLogs());
    }
}
