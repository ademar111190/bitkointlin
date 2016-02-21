package se.simbio.bitkointlin

import com.google.gson.JsonElement
import com.google.gson.JsonParser
import org.junit.Assert.*
import org.junit.Test
import se.simbio.bitkointlin.http.HttpClient

class BitkointlinTest {

    @Test fun testData() {
        val bitkointlin = Bitkointlin(USER, PASSWORD, HTTP_ADDRESS, HTTP_CLIENT)
        assertEquals(bitkointlin.user, USER)
        assertEquals(bitkointlin.password, PASSWORD)
        assertEquals(bitkointlin.httpAddress, HTTP_ADDRESS)
        assertEquals(bitkointlin.httpClient, HTTP_CLIENT)
    }

    @Test fun testGetBalanceSuccess() {
        val mockHttpClient = object : HttpClient {
            override fun post(bitkointlin: Bitkointlin, method: String, success: (JsonElement) -> Unit, error: (String) -> Unit) {
                assertEquals(method, BALANCE_GET_BALANCE_METHOD_NAME)
                success(JsonParser().parse(BALANCE_GET_BALANCE_SUCCESS_RETURN_EXAMPLE).asJsonObject.get("result"))
            }
        }
        val bitkointlin = Bitkointlin(USER, PASSWORD, HTTP_ADDRESS, mockHttpClient)
        bitkointlin.getBalance({ balance ->
            assertEquals(balance, BALANCE_RESULT, 0.0)
        }, { error ->
            fail("Error shouldn't be called. Error message: $error")
        })
    }

    @Test fun testGetBalanceError() {
        val mockHttpClient = object : HttpClient {
            override fun post(bitkointlin: Bitkointlin, method: String, success: (JsonElement) -> Unit, error: (String) -> Unit) {
                assertEquals(method, BALANCE_GET_BALANCE_METHOD_NAME)
                error(BALANCE_GET_BALANCE_ERROR_RETURN_EXAMPLE)
            }
        }
        val bitkointlin = Bitkointlin(USER, PASSWORD, HTTP_ADDRESS, mockHttpClient)
        bitkointlin.getBalance({ balance ->
            fail("Success shouldn't be called. balance: $balance")
        }, { error ->
            assertEquals(error, BALANCE_GET_BALANCE_ERROR_RETURN_EXAMPLE)
        })
    }

    @Test fun testGetBestBlockHashSuccess() {
        val mockHttpClient = object : HttpClient {
            override fun post(bitkointlin: Bitkointlin, method: String, success: (JsonElement) -> Unit, error: (String) -> Unit) {
                assertEquals(method, BEST_BLOCK_HASH_GET_BEST_BLOCK_HASH_METHOD_NAME)
                success(JsonParser().parse(BEST_BLOCK_HASH_GET_BEST_BLOCK_HASH_SUCCESS_RETURN_EXAMPLE).asJsonObject.get("result"))
            }
        }
        val bitkointlin = Bitkointlin(USER, PASSWORD, HTTP_ADDRESS, mockHttpClient)
        bitkointlin.getBestBlockHash({ bestBlockHash ->
            assertEquals(bestBlockHash, BEST_BLOCK_HASH_RESULT)
        }, { error ->
            fail("Error shouldn't be called. Error message: $error")
        })
    }

    @Test fun testGetBestBlockHashError() {
        val mockHttpClient = object : HttpClient {
            override fun post(bitkointlin: Bitkointlin, method: String, success: (JsonElement) -> Unit, error: (String) -> Unit) {
                assertEquals(method, BEST_BLOCK_HASH_GET_BEST_BLOCK_HASH_METHOD_NAME)
                error(BEST_BLOCK_HASH_GET_BEST_BLOCK_HASH_ERROR_RETURN_EXAMPLE)
            }
        }
        val bitkointlin = Bitkointlin(USER, PASSWORD, HTTP_ADDRESS, mockHttpClient)
        bitkointlin.getBestBlockHash({ bestBlockHash ->
            fail("Success shouldn't be called. bestBlockHash: $bestBlockHash")
        }, { error ->
            assertEquals(error, BEST_BLOCK_HASH_GET_BEST_BLOCK_HASH_ERROR_RETURN_EXAMPLE)
        })
    }

    @Test fun testGetInfoSuccess() {
        val mockHttpClient = object : HttpClient {
            override fun post(bitkointlin: Bitkointlin, method: String, success: (JsonElement) -> Unit, error: (String) -> Unit) {
                assertEquals(method, INFO_GET_INFO_METHOD_NAME)
                success(JsonParser().parse(INFO_GET_INFO_SUCCESS_RESULT_EXAMPLE).asJsonObject.get("result"))
            }
        }
        val bitkointlin = Bitkointlin(USER, PASSWORD, HTTP_ADDRESS, mockHttpClient)
        bitkointlin.getInfo({ info ->
            assertNotNull(info)
            assertEquals(info.balance, INFO_BALANCE, 0.0)
            assertEquals(info.blocks, INFO_BLOCKS)
            assertEquals(info.connections, INFO_CONNECTIONS)
            assertEquals(info.difficulty, INFO_DIFFICULTY, 0.0)
            assertEquals(info.errors, INFO_ERRORS)
            assertEquals(info.keyPoolOldest, INFO_KEY_POOL_OLDEST)
            assertEquals(info.keyPoolSize, INFO_KEY_POOL_SIZE)
            assertEquals(info.payTxFee, INFO_PAY_TX_FEE, 0.0)
            assertEquals(info.protocolVersion, INFO_PROTOCOL_VERSION)
            assertEquals(info.proxy, INFO_PROXY)
            assertEquals(info.relayFee, INFO_RELAY_FEE, 0.0)
            assertEquals(info.testNet, INFO_TEST_NET)
            assertEquals(info.timeOffset, INFO_TIME_OFFSET)
            assertEquals(info.version, INFO_VERSION)
            assertEquals(info.walletVersion, INFO_WALLET_VERSION)
        }, { error ->
            fail("Error shouldn't be called. Error message: $error")
        })
    }

    @Test fun testGetInfoError() {
        val mockHttpClient = object : HttpClient {
            override fun post(bitkointlin: Bitkointlin, method: String, success: (JsonElement) -> Unit, error: (String) -> Unit) {
                assertEquals(method, INFO_GET_INFO_METHOD_NAME)
                error(INFO_GET_INFO_ERROR_RETURN_EXAMPLE)
            }
        }
        val bitkointlin = Bitkointlin(USER, PASSWORD, HTTP_ADDRESS, mockHttpClient)
        bitkointlin.getInfo({ info ->
            fail("Success shouldn't be called. Info model: $info")
        }, { error ->
            assertEquals(error, INFO_GET_INFO_ERROR_RETURN_EXAMPLE)
        })
    }

}