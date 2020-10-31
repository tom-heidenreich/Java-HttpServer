package host;

import java.util.List;

import com.sun.net.httpserver.Authenticator;
import com.sun.net.httpserver.Filter;
import com.sun.net.httpserver.HttpContext;

public class HttpHandler {
	private final HttpContext context;
	
	public HttpHandler(HttpContext context) {
		this.context = context;
	}
	
	public void setAuthenticator(Authenticator e) {
		context.setAuthenticator(e);
	}
	
	public Authenticator getAuthenticator() {
		return context.getAuthenticator();
	}
	
	public List<Filter> getFilters() {
		return context.getFilters();
	}
}