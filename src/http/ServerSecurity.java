package http;

import com.sun.net.httpserver.HttpExchange;

public class ServerSecurity {
    private SecurityProtocol protocol;

    public ServerSecurity(SecurityProtocol protocol) {
        this.protocol = protocol;
    }

    public boolean authenticate(HttpExchange e) {
        return this.protocol.handle(new HttpRequest(e), new HttpResponse(e));
    }

    private static class BasicSecurity implements SecurityProtocol {
        private BasicSecurity() {
        }

        @Override
        public boolean handle(HttpRequest req, HttpResponse res) {
        	res.writeHead(403, head -> head.setHeader("Content-Type", "text/html"));
			res.write("<h1>403 BLOCKED BY SERVERSECURITY!</h1>");
			res.end();
            return false;
        }
    }

    private static class NoSecurity implements SecurityProtocol {
        private NoSecurity() {
        }

        @Override
        public boolean handle(HttpRequest req, HttpResponse res) {
            return true;
        }
    }

    public static enum Security {
        BASIC{

            @Override
            public ServerSecurity get() {
                return new ServerSecurity(new BasicSecurity());
            }
        }
        ,
        NO_SECURITY{

            @Override
            public ServerSecurity get() {
                return new ServerSecurity(new NoSecurity());
            }
        };
        

        private Security() {
        }

        public abstract ServerSecurity get();

    }

    public static interface SecurityProtocol {
        public boolean handle(HttpRequest req, HttpResponse res);
    }
}
