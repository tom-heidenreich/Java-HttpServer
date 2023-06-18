package http;

import com.sun.net.httpserver.HttpContext;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executor;

public class HttpServer {
    private com.sun.net.httpserver.HttpServer http;
    private ServerSecurity security = ServerSecurity.Security.NO_SECURITY.get();

    public HttpServer(InetSocketAddress inetAddress, int backlog) throws IOException {
        this.http = com.sun.net.httpserver.HttpServer.create(inetAddress, backlog);
    }

    public void setExecutor(Executor e) {
        this.http.setExecutor(e);
    }

    public void setServerSecurity(ServerSecurity security) {
        this.security = security;
    }

    public void setServerSecurity(ServerSecurity.Security security) {
        this.setServerSecurity(security.get());
    }

    public HttpHandler setDefaultHandler(HttpResponseHandler response) throws IOException {
        HttpContext context = this.http.createContext("/");
        context.setHandler(e -> {
            if (this.security.authenticate(e)) {
                response.handle(new HttpRequest(e), new HttpResponse(e));
            }
        });
        return new HttpHandler(context);
    }

    public HttpHandler addHandler(String url, HttpResponseHandler response) throws IOException {
        HttpContext context = this.http.createContext(url);
        context.setHandler(e -> {
            if (this.security.authenticate(e)) {
                response.handle(new HttpRequest(url, e), new HttpResponse(e));
            }
        });
        return new HttpHandler(context);
    }

    public void start() {
        this.http.start();
    }

    public void start(ServerActionHandler handler) {
        this.http.start();
        handler.handle();
    }
    
    public void stop(int state) {
    	this.http.stop(state);
    }
    
    public void stop(int state, ServerActionHandler handler) {
        this.http.stop(state);
        handler.handle();
    }

    public static interface HttpResponseHandler {
        public void handle(HttpRequest var1, HttpResponse var2);
    }

    public static interface ServerActionHandler {
        public void handle();
    }

}

