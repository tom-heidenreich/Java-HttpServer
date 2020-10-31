package host;

import com.sun.net.httpserver.Headers;

public class ResponseHeaders {
	
	private Headers headers;
	
	public ResponseHeaders(Headers headers) {
		this.headers = headers;
	}
	
	public void setHeader(String key, String value) {
		headers.set(key, value);
	}
}