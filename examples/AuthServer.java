public static void main(String[] args) throws IOException{
	    HttpServer server = new HttpServer(new InetSocketAddress(80), 0);
	    
	    AuthenticatonHandler authHandler = new AuthenticatonHandler((user)->{
				if(user.getUsername().equals("Username") && user.getPassword().equals("Password")) {
					return true;
				}else {
					return false;
				}
			});
			
			authHandler.setFailedAuthAction((req, res)->{
				System.out.println("Authentication failed: " + req.getRemoteAddress());
				res.writeHead(200, (head)->{
					head.setHeader("Content-Type", "text/plain");
				});
				res.write("Failed!");
				res.end();
			});
	  
	  server.addHandler("/login", (req, res)->{
		  res.writeHead(200, (head)->{
			  head.setHeader("Content-Type", "text/plain");
		  });
		  res.write("Login");
		  res.end();
	  }).setAuthenticator(authHandler);
	  
	  server.start();
	}
