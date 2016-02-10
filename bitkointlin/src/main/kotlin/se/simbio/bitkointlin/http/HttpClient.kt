package se.simbio.bitkointlin.http

import com.google.gson.JsonElement
import se.simbio.bitkointlin.Bitkointlin

interface HttpClient {

    fun post(bitkointlin: Bitkointlin,
             method: String,
             success: (result: JsonElement) -> Unit,
             error: (errorMessage: String) -> Unit)

}