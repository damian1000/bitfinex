package bitfinex.util;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class SigningHelper {

    private static final String HMAC_SHA1_ALGORITHM = "HmacSHA384";

    public String sign(String message, String apiSecret) {
        try {
            Mac mac = Mac.getInstance(HMAC_SHA1_ALGORITHM);
            mac.init(new SecretKeySpec(apiSecret.getBytes(), HMAC_SHA1_ALGORITHM));
            return bytesToHex(mac.doFinal(message.getBytes()));
        } catch (InvalidKeyException | NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    private String bytesToHex(final byte[] hash) {
        final StringBuffer hexString = new StringBuffer();
        for (byte b : hash) {
            final String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

}
