/*
 * Decompiled with CFR 0.137.
 */
package api.http;

import com.sun.net.httpserver.Authenticator;
import com.sun.net.httpserver.HttpContext;

public class HttpHandler {
    private final HttpContext context;

    public HttpHandler(HttpContext context) {
        this.context = context;
    }

    public void setAuthenticator(Authenticator e) {
        this.context.setAuthenticator(e);
    }
}

