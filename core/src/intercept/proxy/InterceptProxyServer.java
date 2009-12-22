package intercept.proxy;

import intercept.configuration.ProxyConfig;
import intercept.logging.ApplicationLog;
import intercept.logging.EventLog;
import intercept.logging.EventLogger;
import static intercept.logging.EventLogger.e;
import intercept.utils.Utils;

import java.net.ServerSocket;
import java.net.Socket;

/**
 * Implementation of ProxyServer accepts incoming connections and creates a ProxyChannel to handle requests on that
 * channel.
 */
class InterceptProxyServer implements ProxyServer {
    private final ProxyConfig proxyConfig;
    private final ApplicationLog applicationLog;
    private final EventLog log = new EventLog();
    private final EventLogger logger = new EventLogger(log);

    private Thread proxyThread;

    protected InterceptProxyServer(ProxyConfig proxyConfig, ApplicationLog applicationLog) {
        this.proxyConfig = proxyConfig;
        this.applicationLog = applicationLog;
    }

    public void start() {
        final boolean[] threadRunning = {false};
        proxyThread = new Thread() {
            @Override
            public void run() {
                ServerSocket server = null;
                try {
                    server = new ServerSocket(proxyConfig.getPort());
                    if (proxyConfig.getDebugLevel() > 0) {
                        logger.log(e("Started Proxy server on port ", proxyConfig.getPort()));
                    }
                    threadRunning[0] = true;
                    while (true) {
                        Socket client = server.accept();
                        ProxyChannel t = new ProxyChannel(client, proxyConfig, logger, applicationLog);
                        t.start();
                    }
                } catch (Exception e) {
                    if (proxyConfig.getDebugLevel() > 0) {
                        logger.log(e("ProxyServer Thread error: " + e));
                    }
                } finally {
                    Utils.close(server);
                    server = null;
                }
            }
        };

        proxyThread.start();

        while (!threadRunning[0]) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void stop() {
        proxyThread.stop();
    }

    public EventLog getLogs() {
        return log;
    }

    @Override
    public String getName() {
        return proxyConfig.getName();
    }

    public ProxyConfig getConfig(){
        return this.proxyConfig;
    }
}
