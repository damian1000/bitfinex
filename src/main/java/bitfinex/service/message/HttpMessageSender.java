package bitfinex.service.message;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class HttpMessageSender {

    public String sendPostMessage(String url, String nonce, String apiKey, String signedSignature, String body) throws IOException {
        HttpPost postMethod = new HttpPost(url);
        postMethod.addHeader("Content-Type", "application/json");
        postMethod.addHeader("bfx-nonce", nonce);
        postMethod.addHeader("bfx-apikey", apiKey);
        postMethod.addHeader("bfx-signature", signedSignature);
        postMethod.setEntity(new StringEntity(body, ContentType.APPLICATION_JSON));
        String result = "";
        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(postMethod)) {
            result = EntityUtils.toString(response.getEntity());
        }
        return result;
    }

}
