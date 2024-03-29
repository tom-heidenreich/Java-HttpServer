package http;

import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.io.OutputStream;

public class HttpResponse {
    private HttpExchange e;

    private boolean headersSent = false;
    private boolean ended = false;

    public HttpResponse(HttpExchange e) {
        this.e = e;
    }

    public void writeHead(int status) {
        if(this.headersSent) throw new IllegalStateException("Headers already sent");
        try {
            this.e.sendResponseHeaders(status, 0L);
            this.headersSent = true;
        }
        catch (IOException e1) {
            e1.printStackTrace();
        }
    }
    
    public void writeHead(int status, ResponseHeadersHandler handler) {
        handler.handle(new ResponseHeaders(this.e.getResponseHeaders()));
        this.writeHead(status);
    }

    public void write(String payload) {
        this.write(payload.getBytes());
    }

    public void write(byte[] payload) {
        if(this.ended) throw new IllegalStateException("Response already ended");
        try {
            OutputStream output = this.e.getResponseBody();
            output.write(payload);
            output.flush();
        }
        catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public void end() {
        this.e.close();
        this.ended = true;
    }

    public HttpResponse status(int status) {
        if(this.headersSent) throw new IllegalStateException("Headers already sent");
        this.writeHead(status);
        return this;
    }

    public void send(String payload) {
        if(!this.headersSent) this.writeHead(200);
        this.write(payload);
        this.end();
    }

    public void send(int status, String payload) {
        this.writeHead(status);
        this.write(payload);
        this.end();
    }

    public void redirect(String location) {
        this.writeHead(302, headers -> {
            headers.setHeader("Location", location);
        });
        this.end();
    }

    public static interface ResponseHeadersHandler {
        public void handle(ResponseHeaders var1);
    }
}