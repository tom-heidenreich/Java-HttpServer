/*
 * Decompiled with CFR 0.137.
 */
package api.http;

import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.io.OutputStream;

public class HttpResponse {
    private HttpExchange e;

    public HttpResponse(HttpExchange e) {
        this.e = e;
    }

    public void writeHead(int status) {
        try {
            this.e.sendResponseHeaders(status, 0L);
        }
        catch (IOException e1) {
            e1.printStackTrace();
        }
    }
    
    public void writeHead(int status, ResponseHeadersHandler handler) {
        try {
            handler.handle(new ResponseHeaders(this.e.getResponseHeaders()));
            this.e.sendResponseHeaders(status, 0L);
        }
        catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public void write(String payload) {
        try {
            OutputStream output = this.e.getResponseBody();
            output.write(payload.getBytes());
            output.flush();
        }
        catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public void write(byte[] payload) {
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
    }

    public void write(int payload) {
        this.write(String.valueOf(payload));
    }

    public static interface ResponseHeadersHandler {
        public void handle(ResponseHeaders var1);
    }

}

