package bitfinex.util;

public class SignatureHelper {

    public String createSignatureHelper(String body, String nonce, String path) {
        return String.format("/api/%s%s%s", path, nonce, body);
    }

}
