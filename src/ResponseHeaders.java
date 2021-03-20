/*
 * Decompiled with CFR 0.137.
 */
package api.http;

import com.sun.net.httpserver.Headers;

public class ResponseHeaders {
    private Headers headers;

    public ResponseHeaders(Headers headers) {
        this.headers = headers;
    }

    public void setHeader(String key, String value) {
        this.headers.set(key, value);
    }

    public /* varargs */ void setCookie(String key, String value, String ... tags) {
        String bakedCookie = String.valueOf(key) + "=" + value + "; ";
        for (String s : tags) {
            bakedCookie = String.valueOf(bakedCookie) + s + ", ";
        }
        bakedCookie = bakedCookie.substring(0, bakedCookie.length() - 2);
        bakedCookie = String.valueOf(bakedCookie) + ";";
        this.headers.set("Set-Cookie", bakedCookie);
    }
}

