package intercept.proxy;

import intercept.configuration.ProxyConfig;
import intercept.logging.ApplicationLog;
import intercept.logging.EventLog;
import intercept.logging.EventLogger;
import static intercept.logging.EventLogger.e;
import intercept.utils.Utils;

import java.net.ServerSocket;
import java.net.Socket;

public class ProxyServer extends Thread {
    private ServerSocket server = null;
    private final ProxyConfig proxyConfig;
    private ApplicationLog applicationLog;
    private EventLog log = new EventLog();
    private final EventLogger logger = new EventLogger(log);

    protected ProxyServer(ProxyConfig proxyConfig, ApplicationLog applicationLog) {
        this.proxyConfig = proxyConfig;
        this.applicationLog = applicationLog;
    }

    public void closeSocket() {
        Utils.close(server);
        server = null;
    }

    @Override
    public void run() {
        try {
            server = new ServerSocket(proxyConfig.getPort());
            if (proxyConfig.getDebugLevel() > 0) {
                logger.log(e("Started Proxy server on port ", proxyConfig.getPort()));
            }

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
            closeSocket();
        }
    }

    public EventLog getLogs() {
        return log;
    }
}
