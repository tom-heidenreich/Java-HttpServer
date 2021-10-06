import com.sun.net.httpserver.HttpExchange;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Set;

public class HttpRequest {
    private HttpExchange e;
    private String uri;
    private HashMap<String, String> params;

    public HttpRequest(HttpExchange e) {
        this("", e);
    }

    public HttpRequest(String uri, HttpExchange e) {
        this.e = e;
        this.uri = e.getRequestURI().toASCIIString().replaceFirst(uri, "");
        if (this.uri.contains("?")) {
            this.setParams(this.uri.substring(this.uri.indexOf("?") + 1, this.uri.length()));
            this.uri = this.uri.substring(0, this.uri.indexOf("?"));
        }
    }

    public InetSocketAddress getLocalAddress() {
    	return e.getLocalAddress();
    }
    
    public String getRequestURI() {
        return this.uri;
    }

    public String getRequestMethod() {
        return this.e.getRequestMethod();
    }

    public Set<String> getRequestHeaders() {
        return this.e.getRequestHeaders().keySet();
    }

    public String getRequestHeader(String key) {
    	try {
    		return this.e.getRequestHeaders().getFirst(key);
    	}catch(NullPointerException e) {
    		return null;
    	}
    }

    public String getRequestBody() {
    	StringBuilder buf = new StringBuilder(512);
    	try {
    		InputStreamReader isr =  new InputStreamReader(e.getRequestBody(),"utf-8");
    		BufferedReader br = new BufferedReader(isr);
    		
    		int b;
    		while ((b = br.read()) != -1) {
    		    buf.append((char) b);
    		}

    		br.close();
    		isr.close();
    	}catch(IOException e) {
    	}
    	return buf.toString();
    }
    
    public byte[] getRequestBodyBytes() throws IOException {
    	return e.getRequestBody().readAllBytes();
    }

    public Cookie[] getCookies() {
        String[] strings = this.getRequestHeader("Cookie").split("; ");
        Cookie[] cookies = new Cookie[strings.length];
        for (int i = 0; i < strings.length; ++i) {
            cookies[i] = Cookie.bake(strings[i]);
        }
        return cookies;
    }

    public String getCookie(String key) {
    	try {
	        for (Cookie c : this.getCookies()) {
	            if (!key.equals(c.getKey())) continue;
	            return c.getValue();
	        }
    	}catch(NullPointerException e) {}
    	return null;
    }

    private void setParams(String s) {
        String[] params = s.split("&");
        this.params = new HashMap<String, String>();
        for (int i = 0; i < params.length; ++i) {
            this.params.put(params[i].substring(0, params[i].indexOf("=")), params[i].substring(params[i].indexOf("=") + 1, params[i].length()));
        }
    }

    public String getParam(String key) {
    	try {
    		return this.params.get(key);
    	}catch(NullPointerException e) {
    		return null;
    	}
    }

    public Set<String> getParams() {
        if (this.params == null) {
            return null;
        }
        return this.params.keySet();
    }

    public InetAddress getRemoteAddress() {
        return this.e.getRemoteAddress().getAddress();
    }
}

