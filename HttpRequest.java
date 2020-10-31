package host;

import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.URI;
import java.util.Set;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;

public class HttpRequest {
	private HttpExchange e;
	
	public HttpRequest(HttpExchange e){
		this.e = e;
	}
	
	public String getRequestURI() {
		return e.getRequestURI().toASCIIString();
	}
	
	public String getRequestMethod() {
		return e.getRequestMethod();
	}
	
	public Set<String> getRequestHeaders() {
		return e.getRequestHeaders().keySet();
	}
	
	public String getRequestHeader(String key) {
		return e.getRequestHeaders().getFirst(key);
	}
	
	public InputStream getRequestBody() {
		return e.getRequestBody();
	}
	
	public InetSocketAddress getRemoteAddress() {
		return e.getRemoteAddress();
	}
}
