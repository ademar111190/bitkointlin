package se.simbio.sample.android;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import se.simbio.bitkointlin.Bitkointlin;
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

    private void logStarted(String method) {
        String message = String.format("Started method: %s", method);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(message);
        progressDialog.setCancelable(false);
        progressDialog.setIndeterminate(true);
        progressDialog.show();
        Log.d("Bitkointlin", message);
    }

    private void logFinished(String message) {
        progressDialog.dismiss();
        new AlertDialog.Builder(this).setMessage(message).show();
        Log.d("Bitkointlin", message);
    }

    public void getBalance(View view) {
        logStarted("getBalance");
        bitkointlin.getBalance(new Function1<Double, Unit>() {
            @Override
            public Unit invoke(Double balance) {
                logFinished("getBalance success, balance: " + balance);
                return null;
            }
        }, new Function1<String, Unit>() {
            @Override
            public Unit invoke(String error) {
                logFinished("getBalance error: " + error);
                return null;
            }
        });
    }

    public void getInfo(View view) {
        logStarted("getInfo");
        bitkointlin.getInfo(new Function1<Info, Unit>() {
            @Override
            public Unit invoke(Info info) {
                logFinished("getInfo success, info: " + info);
                return null;
            }
        }, new Function1<String, Unit>() {
            @Override
            public Unit invoke(String error) {
                logFinished("getInfo error: " + error);
                return null;
            }
        });
    }

}
