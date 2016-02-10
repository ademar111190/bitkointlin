package se.simbio.sample.kotlin

import se.simbio.bitkointlin.Bitkointlin
import se.simbio.bitkointlin.http.fuel.HttpClientImplFuel

/**
 * You need to setup your bitcoin environment and set below the correct data
 * more info: https://bitcoin.org/en/developer-guide
 */
val bitkointlin = Bitkointlin(
        user = "your_user",
        password = "your_password",
        httpAddress = "http://127.0.0.1:8332/",
        httpClient = HttpClientImplFuel())

fun main(args: Array<String>) {
    getInfo {
        getBalance {
            println("Done")
        }
    }
}

fun getInfo(callback: () -> Unit) {
    println("getInfo() called")
    bitkointlin.getInfo({ info ->
        println("getInfo success, info: $info")
        callback()
    }, { error ->
        println("getInfo error: $error")
        callback()
    })
}

fun getBalance(callback: () -> Unit) {
    println("getBalance() called")
    bitkointlin.getBalance({ balance ->
        println("getBalance success, balance: $balance")
        callback()
    }, { error ->
        println("getBalance error: $error")
        callback()
    })
}