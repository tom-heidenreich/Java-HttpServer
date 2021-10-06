import com.sun.net.httpserver.Authenticator;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpPrincipal;

public abstract class BasicAuthenticator
extends Authenticator {
    protected String realm;

    public BasicAuthenticator(String realm) {
        this.realm = realm;
    }

    public String getRealm() {
        return this.realm;
    }

    @Override
    public Authenticator.Result authenticate(HttpExchange t) {
        int colon;
        Headers rmap = t.getRequestHeaders();
        String auth = rmap.getFirst("Authorization");
        if (auth == null) {
            Headers map = t.getResponseHeaders();
            map.set("WWW-Authenticate", "Basic realm=\"" + this.realm + "\"");
            return new Authenticator.Retry(401);
        }
        int sp = auth.indexOf(32);
        if (sp == -1 || !auth.substring(0, sp).equals("Basic")) {
            return new Authenticator.Failure(401);
        }
        byte[] b = Base64.base64ToByteArray(auth.substring(sp + 1));
        String userpass = new String(b);
        String uname = userpass.substring(0, colon = userpass.indexOf(58));
        if (this.checkCredentials(uname, userpass.substring(colon + 1))) {
            return new Authenticator.Success(new HttpPrincipal(uname, this.realm));
        }
        Headers map = t.getResponseHeaders();
        map.set("WWW-Authenticate", "Basic realm=\"" + this.realm + "\"");
        return new Authenticator.Failure(401);
    }

    public abstract boolean checkCredentials(String var1, String var2);
}

