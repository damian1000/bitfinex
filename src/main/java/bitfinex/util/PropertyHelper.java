package bitfinex.util;

public class PropertyHelper {

    public String getApiKey() {
        String value = System.getenv().get("BITFINEX_API_KEY");
        if (value == null) {
            throw new RuntimeException("Please specify BITFINEX_API_KEY in env");
        }
        return value;
    }

    public String getApiSecret() {
        String value =  System.getenv().get("BITFINEX_API_SECRET");
        if (value == null) {
            throw new RuntimeException("Please specify BITFINEX_API_SECRET in env");
        }
        return value;
    }

}
