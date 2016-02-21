package se.simbio.bitkointlin

import se.simbio.bitkointlin.http.HttpClient
import se.simbio.bitkointlin.model.Info
import se.simbio.bitkointlin.model.fromJson

class Bitkointlin(val user: String,
                  val password: String,
                  val httpAddress: String,
                  val httpClient: HttpClient) {

    fun getBalance(success: (balance: Double) -> Unit, error: (message: String) -> Unit) {
        httpClient.post(this, "getbalance", { result ->
            success(result.asDouble)
        }, { errorMessage ->
            error(errorMessage)
        })
    }

    fun getBestBlockHash(success: (bestBlochHash: String) -> Unit, error: (message: String) -> Unit) {
        httpClient.post(this, "getbestblockhash", { result ->
            success(result.asString)
        }, { errorMessage ->
            error(errorMessage)
        })
    }

    fun getInfo(success: (info: Info) -> Unit, error: (message: String) -> Unit) {
        httpClient.post(this, "getinfo", { result ->
            success(fromJson(result.asJsonObject))
        }, { errorMessage ->
            error(errorMessage)
        })
    }


}