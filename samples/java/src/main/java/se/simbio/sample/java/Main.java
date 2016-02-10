package se.simbio.sample.java;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import se.simbio.bitkointlin.Bitkointlin;
import se.simbio.bitkointlin.http.fuel.HttpClientImplFuel;
import se.simbio.bitkointlin.model.Info;

public class Main {

    /**
     * You need to setup your bitcoin environment and set below the correct data
     * more info: https://bitcoin.org/en/developer-guide
     */
    static final Bitkointlin bitkointlin = new Bitkointlin(
            "your_user",
            "your_password",
            "http://127.0.0.1:8332/",
            new HttpClientImplFuel());

    public static void main(String[] args) {
        getInfo(new Runnable() {
            @Override
            public void run() {
                getBalance(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("Done");
                    }
                });
            }
        });
    }

    static void getInfo(final Runnable callback) {
        System.out.println("getInfo() called");
        bitkointlin.getInfo(
                new Function1<Info, Unit>() {
                    @Override
                    public Unit invoke(Info info) {
                        System.out.println("getInfo success, info: " + info);
                        callback.run();
                        return null;
                    }
                },
                new Function1<String, Unit>() {
                    @Override
                    public Unit invoke(String error) {
                        System.out.println("getInfo error: " + error);
                        callback.run();
                        return null;
                    }
                });
    }

    static void getBalance(final Runnable callback) {
        System.out.println("getBalance() called");
        bitkointlin.getBalance(
                new Function1<Double, Unit>() {
                    @Override
                    public Unit invoke(Double balance) {
                        System.out.println("getBalance success, balance: " + balance);
                        callback.run();
                        return null;
                    }
                },
                new Function1<String, Unit>() {
                    @Override
                    public Unit invoke(String error) {
                        System.out.println("getBalance error: " + error);
                        callback.run();
                        return null;
                    }
                });
    }

}
