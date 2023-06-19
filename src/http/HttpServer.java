package http;

import http.uri.URIMatcher;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.LinkedList;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class HttpServer {
    private com.sun.net.httpserver.HttpServer http;

    private final URIMatcher<HttpHandler> matcher = new URIMatcher<>();

    public HttpServer(InetSocketAddress inetAddress, int backlog) throws IOException {
        this.http = com.sun.net.httpserver.HttpServer.create(inetAddress, backlog);

        // default executor
        this.http.setExecutor(Executors.newCachedThreadPool());

        // create context to handle all requests
        this.http.createContext("/", exchange -> {
            try {
                HttpRequest req = new HttpRequest(exchange);
                HttpResponse res = new HttpResponse(exchange);

                // find the handler
                LinkedList<HttpHandler> handlers = (LinkedList<HttpHandler>) this.matcher.matches(req.getRequestURI());

                if(handlers.size() == 0) {
                    res.writeHead(404);
                    res.write("404 Not Found");
                    res.end();
                    return;
                }

                // remove handlers that don't accept the request
                handlers.removeIf(e -> !e.accepts(req));

                if(handlers.size() == 0) {
                    res.writeHead(405);
                    res.write("405 Method Not Allowed");
                    res.end();
                    return;
                }

                handlers.getLast().handle(req, res);

            }catch(Exception e) {
                e.printStackTrace();

                HttpResponse res = new HttpResponse(exchange);
                res.writeHead(500);
                res.write("500 Internal Server Error");
                res.end();
            }
        });
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
        return this.handle("*", url, response);
    }

    public HttpHandler handle(String method, String url, HttpResponseHandler response) throws IOException {
        HttpHandler handler = new HttpHandler(response) {
            @Override
            public boolean accepts(HttpRequest request) {
                return method.equals("*") || method.equals(request.getRequestMethod());
            }
        };
        this.matcher.add(url, handler);
        return handler;
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

