package http;

import com.sun.net.httpserver.HttpContext;

public class HttpHandler {
    private final HttpContext context;

    public HttpHandler(HttpContext context) {
        this.context = context;
    }
}

