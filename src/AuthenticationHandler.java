import com.sun.net.httpserver.Authenticator;
import com.sun.net.httpserver.HttpExchange;

public class AuthenticationHandler
extends BasicAuthenticator {
    private AuthHandler handler;
    private ProcessedAuthHandler failedHandler;
    private HttpExchange ex;

    public AuthenticationHandler(AuthHandler handler) {
        super("");
        this.handler = handler;
    }

    public void setFailedAuthAction(ProcessedAuthHandler handler) {
        this.failedHandler = handler;
    }

    @Override
    public Authenticator.Result authenticate(HttpExchange arg0) {
        this.ex = arg0;
        return super.authenticate(arg0);
    }

    @Override
    public boolean checkCredentials(String username, String password) {
        if (this.handler.handle(new User(username, password))) {
            return true;
        }
        if (this.failedHandler != null) {
            this.failedHandler.handle(new HttpRequest(this.ex), new HttpResponse(this.ex));
        }
        return false;
    }

    public static interface AuthHandler {
        public boolean handle(User var1);
    }

    public static interface ProcessedAuthHandler {
        public void handle(HttpRequest var1, HttpResponse var2);
    }

    public static class User {
        private final String username;
        private final String password;

        public User(String username, String password) {
            this.username = username;
            this.password = password;
        }

        public String getUsername() {
            return this.username;
        }

        public String getPassword() {
            return this.password;
        }
    }

}

