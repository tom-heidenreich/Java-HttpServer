package host;

import java.io.IOException;
import java.io.OutputStream;

import com.sun.net.httpserver.HttpExchange;

public class HttpResponse {
	
	private HttpExchange e;
	
	public HttpResponse(HttpExchange e){
		this.e = e;
	}
	
	public void writeHead(int arg0, ResponseHeadersHandler handler) {
		try {
			handler.handle(new ResponseHeaders(e.getResponseHeaders()));
			e.sendResponseHeaders(arg0, 0);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	public interface ResponseHeadersHandler{
		public void handle(ResponseHeaders headers);
	}
	
	public void write(String payload) {
		try {
			final OutputStream output = e.getResponseBody();
			output.write(payload.getBytes());
			output.flush();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	public void end() {
		e.close();
	}
}