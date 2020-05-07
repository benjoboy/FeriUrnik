package com.skupina1.urnik;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.BufferedSink;

import java.io.IOException;

public class LoginActivity extends Activity {

    private TextView mtw7;
    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mtw7 = findViewById(R.id.textView7);
    }

    public String getJson(){
        EditText et1 = findViewById(R.id.editText2);
        String email = et1.getText().toString();
        et1 = findViewById(R.id.editText3);
        String pass = et1.getText().toString();
        try {
            JSONObject obj = new JSONObject();
            obj.put("email", email);
            obj.put("passwordHash", pass);

            return obj.toString();
        } catch (JSONException e){
            Log.e("TestApp", "unexpected json exception", e);
            return null;
        }
    }

    public void testswitch(View v){
        switch (v.getId()) {
            case R.id.buttonLogin:
                testget(getJson());
                break;
            case R.id.buttonRegister:
                Intent intent2 = new Intent(this,  SignupActivity.class);
                startActivity(intent2);
                break;
        }
    }

    public void testget(String json) {
        String url = "https://furnik-backend.azurewebsites.net/uporabnik/login";
        Request request = new Request.Builder()
                .url(url)
                .addHeader("Content-Type", "application/json")
                .post(RequestBody.create(JSON, json))
                .build();
        MainActivity.client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(response.isSuccessful()){
                    final String myResponse = response.body().string();
                    LoginActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mtw7.setText(myResponse);
                        }
                    });
                    Intent intent = new Intent(getBaseContext(),  MainActivity.class);
                    startActivity(intent);
                } else {
                    final String myResponse = response.body().string();
                    LoginActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mtw7.setText(myResponse);
                        }
                    });
                }
            }
        });
    }

    public String post(String json) throws IOException {
        String url = "https://feri-urnik.herokuapp.com/uporabnik/login";
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Response response = MainActivity.client.newCall(request).execute();
        return response.body().string();
    }
}
