package se.simbio.sample.android;

import android.os.Handler;
import android.support.annotation.NonNull;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import okhttp3.Credentials;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import se.simbio.bitkointlin.Bitkointlin;
import se.simbio.bitkointlin.http.HttpClient;

public class HttpClientImplOkHttp implements HttpClient {

    final OkHttpClient client = new OkHttpClient();
    final MediaType mediaType = MediaType.parse("application/json; charset=utf-8");

    @Override
    public void post(@NonNull Bitkointlin bitkointlin, @NonNull String method, @NonNull final Function1<? super JsonElement, Unit> success, @NonNull final Function1<? super String, Unit> error) {

        JsonObject body = new JsonObject();
        body.addProperty("method", method);
        body.add("params", new JsonArray());

        String credential = Credentials.basic(bitkointlin.getUser(), bitkointlin.getPassword());

        RequestBody requestBody = RequestBody.create(mediaType, body.toString());
        final Request request = new Request.Builder()
                .header("Authorization", credential)
                .url(bitkointlin.getHttpAddress())
                .post(requestBody)
                .build();

        final Handler handler = new Handler();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Response response = client.newCall(request).execute();
                    if (response.isSuccessful()) {
                        final String result = response.body().string();
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                JsonObject json = new JsonParser().parse(result).getAsJsonObject();
                                boolean hasError = json.has("error") && !(json.get("error") instanceof JsonNull);
                                if (hasError) {
                                    error.invoke(json.get("error").getAsString());
                                } else if (!json.has("result")) {
                                    error.invoke("no error reported nut there is no result");
                                } else {
                                    success.invoke(json.get("result"));
                                }
                            }
                        });
                    } else {
                        final String message = response.message();
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                error.invoke(message);
                            }
                        });
                    }
                } catch (final Exception e) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            error.invoke(e.getMessage());
                        }
                    });
                }
            }
        }).start();
    }

}
