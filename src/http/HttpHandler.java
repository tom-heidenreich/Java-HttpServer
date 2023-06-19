package http;

import http.HttpServer.HttpResponseHandler;

public abstract class HttpHandler {

    private final HttpResponseHandler handler;

    public HttpHandler(HttpResponseHandler handler) {
        this.handler = handler;
    }

    public abstract boolean accepts(HttpRequest request);
    
    public void handle(HttpRequest request, HttpResponse response) {
        this.handler.handle(request, response);
    }
}