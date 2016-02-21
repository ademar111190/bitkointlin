package se.simbio.sample.kotlin

import se.simbio.bitkointlin.Bitkointlin
import se.simbio.bitkointlin.http.fuel.HttpClientImplFuel
import java.awt.GridLayout
import javax.swing.*

/**
 * You need to setup your bitcoin environment and set below the correct data
 * more info: https://bitcoin.org/en/developer-guide
 */
val bitkointlin = Bitkointlin(
        user = "your_user",
        password = "your_password",
        httpAddress = "http://127.0.0.1:8332/",
        httpClient = HttpClientImplFuel())

val frame = JFrame("Bitkointlin")
var dialog = JDialog()

fun main(args: Array<String>) {
    frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
    frame.layout = GridLayout(0, 1)

    addButton("Get Balance") { getBalance() }
    addButton("Get BestBlockHash") { getBestBlockHash() }
    addButton("Get Difficulty") { getDifficulty() }
    addButton("Get GetInfo") { getInfo() }

    frame.pack()
    frame.setLocationRelativeTo(null)
    frame.isVisible = true
}

fun addButton(method: String, callback: () -> Unit) {
    val button = JButton(method)
    button.addActionListener { callback() }
    frame.add(button)
}

fun showDialog(method: String) {
    dialog = JDialog(frame, method, false)
    dialog.setLocationRelativeTo(frame)
    dialog.add(JTextField("$method called, waiting..."))
    dialog.pack()
    dialog.isVisible = true
}

fun showSuccess(method: String, message: String) {
    dialog.dispose()
    JOptionPane.showMessageDialog(frame, message, "$method success", JOptionPane.INFORMATION_MESSAGE)
}

fun showError(method: String, message: String) {
    dialog.dispose()
    JOptionPane.showMessageDialog(frame, message, "$method error", JOptionPane.ERROR_MESSAGE)
}

fun getBalance() {
    val method = "getBalance"
    showDialog(method)
    bitkointlin.getBalance({ balance ->
        showSuccess(method, "Balance: $balance")
    }, { error ->
        showError(method, "$error")
    })
}

fun getBestBlockHash() {
    val method = "getBestBlockHash"
    showDialog(method)
    bitkointlin.getBestBlockHash({ bestBlockHash ->
        showSuccess(method, "Best Block Hash: $bestBlockHash")
    }, { error ->
        showError(method, "$error")
    })
}

fun getDifficulty() {
    val method = "getDifficulty"
    showDialog(method)
    bitkointlin.getDifficulty({ difficulty ->
        showSuccess(method, "Difficulty: $difficulty")
    }, { error ->
        showError(method, "$error")
    })
}

fun getInfo() {
    val method = "getInfo"
    showDialog(method)
    bitkointlin.getInfo({ info ->
        showSuccess(method, "Info:\n\t" +
                "- Version: ${info.version}\n\t" +
                "- Protocol Version: ${info.protocolVersion}\n\t" +
                "- Wallet Version: ${info.walletVersion}\n\t" +
                "- Balance: ${info.balance}\n\t" +
                "- Blocks: ${info.blocks}\n\t" +
                "- Time Offset: ${info.timeOffset}\n\t" +
                "- Connections: ${info.connections}\n\t" +
                "- Proxy: ${info.proxy}\n\t" +
                "- Difficulty: ${info.difficulty}\n\t" +
                "- Test Net: ${info.testNet}\n\t" +
                "- Key Pool Oldest: ${info.keyPoolOldest}\n\t" +
                "- Key Pool Size: ${info.keyPoolSize}\n\t" +
                "- Pay Tx Fee: ${info.payTxFee}\n\t" +
                "- Relay Fee: ${info.relayFee}\n\t" +
                "- Errors: ${info.errors}\n\t")
    }, { error ->
        showError(method, "$error")
    })
}
