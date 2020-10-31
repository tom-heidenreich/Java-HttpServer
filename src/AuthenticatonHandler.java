package host;

import com.sun.net.httpserver.HttpExchange;

public class AuthenticatonHandler extends BasicAuthenticator {
	
	private AuthenticationHandler handler;

	public AuthenticatonHandler(AuthenticationHandler handler) {
		super("");
		this.handler = handler;
	}

	public class User {
		private final String username;
		private final String password;
		
		public User(String username, String password) {
			this.username = username;
			this.password = password;
		}
		public String getUsername() {
			return username;
		}
		public String getPassword() {
			return password;
		}
	}
	
	public interface AuthenticationHandler{
		public boolean handle(User user);
	}
	
	@Override
	public Result authenticate(HttpExchange arg0) {
		return super.authenticate(arg0);

	}
	
	@Override
	public boolean checkCredentials(String username, String password) {
		return handler.handle(new User(username, password));
	}
}