package se.simbio.bitkointlin.model

import com.google.gson.JsonObject

fun fromJson(json: JsonObject): Info {
    return Info(json.get("version").asLong,
            json.get("protocolversion").asLong,
            json.get("walletversion").asLong,
            json.get("balance").asDouble,
            json.get("blocks").asLong,
            json.get("timeoffset").asLong,
            json.get("connections").asLong,
            json.get("proxy").asString,
            json.get("difficulty").asDouble,
            json.get("testnet").asBoolean,
            json.get("keypoololdest").asLong,
            json.get("keypoolsize").asLong,
            json.get("paytxfee").asDouble,
            json.get("relayfee").asDouble,
            json.get("errors").asString)
}

data class Info(val version: Long,
                val protocolVersion: Long,
                val walletVersion: Long,
                val balance: Double,
                val blocks: Long,
                val timeOffset: Long,
                val connections: Long,
                val proxy: String,
                val difficulty: Double,
                val testNet: Boolean,
                val keyPoolOldest: Long,
                val keyPoolSize: Long,
                val payTxFee: Double,
                val relayFee: Double,
                val errors: String)