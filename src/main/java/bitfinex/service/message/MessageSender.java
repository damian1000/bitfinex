package bitfinex.service.message;

import bitfinex.util.PropertyHelper;
import bitfinex.util.SignatureHelper;
import bitfinex.util.SigningHelper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class MessageSender {

    private final SignatureHelper signatureHelper = new SignatureHelper();
    private final SigningHelper signingHelper = new SigningHelper();
    private final HttpMessageSender messageSender = new HttpMessageSender();
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final PropertyHelper propertyHelper = new PropertyHelper();

    public String sendMessage(String apiPath, Object message) {
        String body = createJson(message);
        return sendMessageInternal(apiPath, body);
    }

    public String sendMessage(String apiPath) {
        String body = "{}";
        return sendMessageInternal(apiPath, body);
    }

    private String sendMessageInternal(String apiPath, String jsonBody) {
        String apiKey = propertyHelper.getApiKey();
        String apiSecret = propertyHelper.getApiSecret();
        String nonce = String.valueOf(System.currentTimeMillis() * 1000);

        String signature = signatureHelper.createSignatureHelper(jsonBody, nonce, apiPath);
        String signedSignature = signingHelper.sign(signature, apiSecret);

        String uri = String.format("https://api.bitfinex.com/%s", apiPath);

        System.out.printf("Sending %s to %s%n", jsonBody, apiPath);

        try {
            return messageSender.sendPostMessage(uri, nonce, apiKey, signedSignature, jsonBody);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String createJson(Object message) {
        try {
            return objectMapper.writeValueAsString(message);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
