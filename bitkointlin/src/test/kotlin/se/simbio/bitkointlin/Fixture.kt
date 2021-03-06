package se.simbio.bitkointlin

import com.google.gson.JsonElement
import se.simbio.bitkointlin.http.HttpClient

// Bitkointlin
val USER = "User"
val PASSWORD = "Password"
val HTTP_ADDRESS = "http://127.0.0.1:8332/"
val HTTP_CLIENT = object : HttpClient {
    override fun post(bitkointlin: Bitkointlin, method: String, success: (JsonElement) -> Unit, error: (String) -> Unit) {
    }
}

// Balance
val BALANCE_RESULT = 0.0
val BALANCE_GET_BALANCE_METHOD_NAME = "getbalance"
val BALANCE_GET_BALANCE_ERROR_RETURN_EXAMPLE = "Balance Error"
val BALANCE_GET_BALANCE_SUCCESS_RETURN_EXAMPLE = """
    {"result": $BALANCE_RESULT,
    "error":null,
    "id":"sentId"}
"""

// Best Block Hash
val BEST_BLOCK_HASH_RESULT = "000000000000000002e7469aa3f6f2a2c5d99f986cb56804b745b21e86567a0e"
val BEST_BLOCK_HASH_GET_BEST_BLOCK_HASH_METHOD_NAME = "getbestblockhash"
val BEST_BLOCK_HASH_GET_BEST_BLOCK_HASH_ERROR_RETURN_EXAMPLE = "Best Block Error"
val BEST_BLOCK_HASH_GET_BEST_BLOCK_HASH_SUCCESS_RETURN_EXAMPLE = """
    {"result": "$BEST_BLOCK_HASH_RESULT",
    "error":null,
    "id":"sentId"}
"""

// Difficulty
val DIFFICULTY_RESULT = 163491654908.95925903
val DIFFICULTY_GET_DIFFICULTY_METHOD_NAME = "getdifficulty"
val DIFFICULTY_GET_DIFFICULTY_ERROR_RETURN_EXAMPLE = "Difficulty Error"
val DIFFICULTY_GET_DIFFICULTY_SUCCESS_RETURN_EXAMPLE = """
    {"result": $DIFFICULTY_RESULT,
    "error":null,
    "id":"sentId"}
"""

// Info
val INFO_BALANCE = 4.5
val INFO_BLOCKS = 6L
val INFO_CONNECTIONS = 8L
val INFO_DIFFICULTY = 9.10
val INFO_ERRORS = ""
val INFO_KEY_POOL_OLDEST = 11L
val INFO_KEY_POOL_SIZE = 12L
val INFO_PAY_TX_FEE = 13.14
val INFO_PROTOCOL_VERSION = 2L
val INFO_PROXY = "Some Proxy"
val INFO_RELAY_FEE = 15.16
val INFO_TEST_NET = false
val INFO_TIME_OFFSET = 7L
val INFO_VERSION = 1L
val INFO_WALLET_VERSION = 3L
val INFO_GET_INFO_METHOD_NAME = "getinfo"
val INFO_GET_INFO_ERROR_RETURN_EXAMPLE = "Info Error"
val INFO_GET_INFO_SUCCESS_RESULT_EXAMPLE = """
    {"result":{
        "version": $INFO_VERSION,
        "protocolversion": $INFO_PROTOCOL_VERSION,
        "walletversion": $INFO_WALLET_VERSION,
        "balance": $INFO_BALANCE,
        "blocks": $INFO_BLOCKS,
        "timeoffset": $INFO_TIME_OFFSET,
        "connections": $INFO_CONNECTIONS,
        "proxy": "$INFO_PROXY",
        "difficulty": $INFO_DIFFICULTY,
        "testnet": $INFO_TEST_NET,
        "keypoololdest": $INFO_KEY_POOL_OLDEST,
        "keypoolsize": $INFO_KEY_POOL_SIZE,
        "paytxfee": $INFO_PAY_TX_FEE,
        "relayfee": $INFO_RELAY_FEE,
        "errors": "$INFO_ERRORS"},
    "error":null,
    "id":"sentId"}
"""