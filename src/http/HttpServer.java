package http;

import com.sun.net.httpserver.HttpContext;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executor;

public class HttpServer {
    private com.sun.net.httpserver.HttpServer http;

    public HttpServer(InetSocketAddress inetAddress, int backlog) throws IOException {
        this.http = com.sun.net.httpserver.HttpServer.create(inetAddress, backlog);
    }

    public HttpServer(InetSocketAddress inetAddress) throws IOException {
        this(inetAddress, 0);
    }

    public HttpServer(int port, int backlog) throws IOException {
        this(new InetSocketAddress(port), backlog);
    }

    public HttpServer(int port) throws IOException {
        this(port, 0);
    }

    public void setExecutor(Executor e) {
        this.http.setExecutor(e);
    }

    public HttpHandler setDefaultHandler(HttpResponseHandler response) throws IOException {
        return this.addHandler("/", response);
    }

    public HttpHandler addHandler(String url, HttpResponseHandler response) throws IOException {
        HttpContext context = this.http.createContext(url);
        context.setHandler(e -> {
            response.handle(new HttpRequest(url, e), new HttpResponse(e));
        });
        return new HttpHandler(context);
    }

    public HttpHandler handle(String method, String url, HttpResponseHandler response) throws IOException {
        HttpContext context = this.http.createContext(url);
        context.setHandler(e -> {
            if(e.getRequestMethod().equals(method)) response.handle(new HttpRequest(url, e), new HttpResponse(e));
            return;
        });
        return new HttpHandler(context);
    }

    public HttpHandler get(String url, HttpResponseHandler response) throws IOException {
        return this.handle("GET", url, response);
    }

    public HttpHandler post(String url, HttpResponseHandler response) throws IOException {
        return this.handle("POST", url, response);
    }

    public HttpHandler put(String url, HttpResponseHandler response) throws IOException {
        return this.handle("PUT", url, response);
    }

    public HttpHandler delete(String url, HttpResponseHandler response) throws IOException {
        return this.handle("DELETE", url, response);
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

