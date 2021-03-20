import com.sun.net.httpserver.HttpExchange;

public class AuthenticatonHandler extends BasicAuthenticator {
	
	private AuthHandler handler;
	private FailedAuthHandler failedHandler;
	private HttpExchange ex;

	public AuthenticatonHandler(AuthHandler handler) {
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
	
	public void setFailedAuthAction(FailedAuthHandler handler) {
		failedHandler = handler;
	}
	
	public interface FailedAuthHandler{
		public void handle(HttpRequest req, HttpResponse res);
	}
	
	public interface AuthHandler{
		public boolean handle(User user);
	}
	
	@Override
	public Result authenticate(HttpExchange arg0) {
		ex = arg0;
		return super.authenticate(arg0);

	}
	
	@Override
	public boolean checkCredentials(String username, String password) {
		if(handler.handle(new User(username, password))){
			return true;
		}else {
			failedHandler.handle(new HttpRequest(ex), new HttpResponse(ex));
			return false;
		}
	}
}
