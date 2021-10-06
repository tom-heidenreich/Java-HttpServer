public class Cookie {
    private final String key;
    private final String value;

    public Cookie(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public static Cookie bake(String string) {
        return new Cookie(string.split("=")[0], string.split("=")[1]);
    }

    public String getKey() {
        return this.key;
    }

    public String getValue() {
        return this.value;
    }
    
    @Override
    public String toString() {
    	return key + ": " + value;
    }
}
