package host;

import com.sun.net.httpserver.Authenticator;
import com.sun.net.httpserver.HttpContext;

public class HttpHandler {
	private final HttpContext context;
	
	public HttpHandler(HttpContext context) {
		this.context = context;
	}
	
	public void setAuthenticator(Authenticator e) {
		context.setAuthenticator(e);
	}
}
