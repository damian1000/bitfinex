package kbitfinex.message

import org.apache.http.client.methods.HttpPost
import org.apache.http.entity.ContentType
import org.apache.http.entity.StringEntity
import org.apache.http.impl.client.HttpClients
import org.apache.http.util.EntityUtils

class HttpMessageSender {

    fun sendPostMessage(url: String, nonce: String, apiKey: String, signedSignature: String, body: String): String {
        val postMethod = HttpPost(url)
        postMethod.addHeader("Content-Type", "application/json")
        postMethod.addHeader("bfx-nonce", nonce)
        postMethod.addHeader("bfx-apikey", apiKey)
        postMethod.addHeader("bfx-signature", signedSignature)
        postMethod.entity = StringEntity(body, ContentType.APPLICATION_JSON)
        HttpClients.createDefault().use { httpClient ->
            httpClient.execute(postMethod).use { response ->
                return EntityUtils.toString(response.entity)
            }
        }
    }

}