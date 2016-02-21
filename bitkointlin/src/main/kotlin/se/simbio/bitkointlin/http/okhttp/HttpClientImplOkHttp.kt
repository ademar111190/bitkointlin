package se.simbio.bitkointlin.http.okhttp

import com.google.gson.*
import okhttp3.*
import se.simbio.bitkointlin.Bitkointlin
import se.simbio.bitkointlin.http.HttpClient
import java.io.IOException

class HttpClientImplOkHttp : HttpClient {

    val client = OkHttpClient()
    val mediaType = MediaType.parse("application/json; charset=utf-8")

    override fun post(bitkointlin: Bitkointlin, method: String, success: (JsonElement) -> Unit, error: (String) -> Unit) {
        val body = JsonObject()
        body.addProperty("method", method)
        body.add("params", JsonArray())
        val credential = Credentials.basic(bitkointlin.user, bitkointlin.password)
        val requestBody = RequestBody.create(mediaType, body.toString())
        val request = Request.Builder()
                .header("Authorization", credential)
                .url(bitkointlin.httpAddress)
                .post(requestBody)
                .build()
        val call = client.newCall(request);
        call.enqueue(object : Callback {
            override fun onFailure(call: Call?, e: IOException?) {
                error.invoke("${e?.message}")
            }

            override fun onResponse(call: Call?, response: Response?) {
                if (response != null && response.isSuccessful) {
                    val result = response.body().string();
                    val json = JsonParser().parse(result).asJsonObject
                    val hasError = json.has("error") && json.get("error") !is JsonNull;
                    if (hasError) {
                        error.invoke(json.get("error").asString);
                    } else if (!json.has("result")) {
                        error.invoke("no error reported nut there is no result");
                    } else {
                        success.invoke(json.get("result"));
                    }
                } else {
                    error.invoke("${response?.message()}");
                }
            }
        })
    }

}