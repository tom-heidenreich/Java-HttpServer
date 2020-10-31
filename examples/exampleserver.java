public class ExampleServer {
  public static void main(String[] args) throws IOException {
		HttpServer server = new HttpServer(new InetSocketAddress(80), 0);
		
		server.setDefaultHandler((req, res)->{
			switch(req.getRequestURI()) {
				case "/home":
					res.writeHead(200, (head)->{
						head.setHeader("Content-Type", "text/plain");
					});
					res.write("Home");
					res.end();
					break;
				default:
					res.writeHead(302, (head)->{
						head.setHeader("Location", "/home");
					});
					res.end();
					break;
			}
		});
		
		server.start(()->{
			System.out.println("Server started!");
		});
	}
}
