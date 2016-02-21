package se.simbio.sample.android;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import se.simbio.bitkointlin.Bitkointlin;
import se.simbio.bitkointlin.http.okhttp.HttpClientImplOkHttp;
import se.simbio.bitkointlin.model.Info;

public class MainActivity extends AppCompatActivity {

    private ProgressDialog progressDialog;

    /**
     * You need to setup your bitcoin environment and set below the correct data
     * more info: https://bitcoin.org/en/developer-guide
     */
    final Bitkointlin bitkointlin = new Bitkointlin(
            "your_user",
            "your_password",
            "http://10.0.2.2:8332/",
            new HttpClientImplOkHttp());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    private void showDialog(String method) {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(method + " called, waiting...");
        progressDialog.setCancelable(false);
        progressDialog.setIndeterminate(true);
        progressDialog.show();
    }

    private void showSuccess(final String method, final String message) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                progressDialog.dismiss();
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle(method + " success")
                        .setMessage(message)
                        .show();
            }
        });
    }

    private void showError(final String method, final String message) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                progressDialog.dismiss();
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle(method + " error")
                        .setMessage(message)
                        .show();
            }
        });
    }

    public void getBalance(View view) {
        final String method = "getBalance";
        showDialog(method);
        bitkointlin.getBalance(new Function1<Double, Unit>() {
            @Override
            public Unit invoke(Double balance) {
                showSuccess(method, "Balance: " + balance);
                return null;
            }
        }, new Function1<String, Unit>() {
            @Override
            public Unit invoke(String error) {
                showError(method, error);
                return null;
            }
        });
    }

    public void getBestBlockHash(View view) {
        final String method = "getBestBlockHash";
        showDialog(method);
        bitkointlin.getBestBlockHash(new Function1<String, Unit>() {
            @Override
            public Unit invoke(String bestBlockHash) {
                showSuccess(method, "Best BlockHash: " + bestBlockHash);
                return null;
            }
        }, new Function1<String, Unit>() {
            @Override
            public Unit invoke(String error) {
                showError(method, error);
                return null;
            }
        });
    }

    public void getDifficulty(View view) {
        final String method = "getDifficulty";
        showDialog(method);
        bitkointlin.getDifficulty(new Function1<Double, Unit>() {
            @Override
            public Unit invoke(Double difficulty) {
                showSuccess(method, "Difficulty: " + difficulty);
                return null;
            }
        }, new Function1<String, Unit>() {
            @Override
            public Unit invoke(String error) {
                showError(method, error);
                return null;
            }
        });
    }

    public void getInfo(View view) {
        final String method = "getInfo";
        showDialog(method);
        bitkointlin.getInfo(new Function1<Info, Unit>() {
            @Override
            public Unit invoke(Info info) {
                showSuccess(method, "Info: \n\t" +
                        "- Version: " + info.getVersion() + "\n\t" +
                        "- Protocol Version: " + info.getProtocolVersion() + "\n\t" +
                        "- Wallet Version: " + info.getWalletVersion() + "\n\t" +
                        "- Balance: " + info.getBalance() + "\n\t" +
                        "- Blocks: " + info.getBlocks() + "\n\t" +
                        "- Time Offset: " + info.getTimeOffset() + "\n\t" +
                        "- Connections: " + info.getConnections() + "\n\t" +
                        "- Proxy: " + info.getProxy() + "\n\t" +
                        "- Difficulty: " + info.getDifficulty() + "\n\t" +
                        "- Test Net: " + info.getTestNet() + "\n\t" +
                        "- Key Pool Oldest: " + info.getKeyPoolOldest() + "\n\t" +
                        "- Key Pool Size: " + info.getKeyPoolSize() + "\n\t" +
                        "- Pay Tx Fee: " + info.getPayTxFee() + "\n\t" +
                        "- Relay Fee: " + info.getRelayFee() + "\n\t" +
                        "- Errors: " + info.getErrors() + "\n\t");
                return null;
            }
        }, new Function1<String, Unit>() {
            @Override
            public Unit invoke(String error) {
                showError(method, error);
                return null;
            }
        });
    }

}
