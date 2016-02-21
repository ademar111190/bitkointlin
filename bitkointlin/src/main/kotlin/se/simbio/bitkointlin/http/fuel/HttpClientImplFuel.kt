package se.simbio.bitkointlin.http.fuel

import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.result.Result
import com.google.gson.*
import se.simbio.bitkointlin.Bitkointlin
import se.simbio.bitkointlin.http.HttpClient

class HttpClientImplFuel : HttpClient {

    override fun post(bitkointlin: Bitkointlin,
                      method: String,
                      success: (result: JsonElement) -> Unit,
                      error: (errorMessage: String) -> Unit) {
        val body = JsonObject()
        body.addProperty("method", method)
        body.add("params", JsonArray())
        bitkointlin.httpAddress.httpPost()
                .authenticate(bitkointlin.user, bitkointlin.password)
                .body(body.toString())
                .responseString { request, response, result ->
                    when (result) {
                        is Result.Success -> {
                            val json = JsonParser().parse(result.get()).asJsonObject
                            val hasError = json.has("error") && json.get("error") !is JsonNull
                            if (hasError) {
                                error(json.get("error").asString)
                            } else if (!json.has("result")) {
                                error("no error reported nut there is no result")
                            } else {
                                success(json.get("result"))
                            }
                        }
                        else -> {
                            error(response.httpResponseMessage)
                        }
                    }
                }
    }

}