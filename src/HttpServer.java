package host;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executor;

import com.sun.net.httpserver.HttpContext;

public class HttpServer {
	private com.sun.net.httpserver.HttpServer http;
	
	public HttpServer(InetSocketAddress inetAddress, int backlog) throws IOException {
		http = com.sun.net.httpserver.HttpServer.create(inetAddress, backlog);
	}
	
	public void setExecutor(Executor e) {
		http.setExecutor(e);
	}
	
	public HttpHandler setDefaultHandler(HttpResponseHandler response) throws IOException {
		HttpContext context = http.createContext("/");
		context.setHandler((e) -> response.handle(new HttpRequest(e), new HttpResponse(e)));
		return new HttpHandler(context);
	}
	
	public HttpHandler addHandler(String url, HttpResponseHandler response) throws IOException {
		HttpContext context = http.createContext(url);
		context.setHandler((e) -> response.handle(new HttpRequest(e), new HttpResponse(e)));
		return new HttpHandler(context);
	}
	
	public interface HttpResponseHandler{
		public void handle(HttpRequest req, HttpResponse res);
	}
	
	public void start() {
		http.start();
	}
	
	public void start(ServerStartHandler handler) {
		http.start();
		handler.handle();
	}
	
	public interface ServerStartHandler{
		public void handle();
	}
}
