package com.skupina1.urnik;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class SignupActivity extends Activity {

    private TextView tw8;
    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        tw8 = findViewById(R.id.textView8);
    }

    public String getJson(){
        EditText et1 = findViewById(R.id.editText1);
        String ime = et1.getText().toString();
        et1 = findViewById(R.id.editText2);
        String priimek = et1.getText().toString();
        et1 = findViewById(R.id.editText3);
        String email = et1.getText().toString();
        et1 = findViewById(R.id.editText4);
        String pass = et1.getText().toString();
        JSONArray arr = new JSONArray();
        Spinner spinner = findViewById(R.id.spinnerStudij);
        arr.put(getId(spinner.getSelectedItem().toString()));
        try {
            JSONObject obj = new JSONObject();
            obj.put("ime", ime);
            obj.put("priimek", priimek);
            obj.put("email", email);
            obj.put("passwordHash", pass);
            obj.put("studij", arr);
            obj.put("izbire", new JSONArray());

            return obj.toString();
        } catch (JSONException e){
            Log.e("TestApp", "unexpected json exception", e);
            return null;
        }
    }

    public void testswitch(View v){
        if(v == findViewById(R.id.buttonRegister)){
            testpost(getJson());
        }
    }

    private String getId(String choice){ //za testing vedno vrne R-IT UN 3letnik
        switch (choice) {
            case "R-IT":
                return "5eb1ec58ce422e4358d22a2a";
            case "ITK":
                return "5eb1ec58ce422e4358d22a2a";
            case "Elektrotehnika":
                return "5eb1ec58ce422e4358d22a2a";
            case "Telekomunikacije":
                return "5eb1ec58ce422e4358d22a2a";
            case "Medijske komunikacije":
                return "5eb1ec58ce422e4358d22a2a";
            case "Mehatronika":
                return "5eb1ec58ce422e4358d22a2a";
            default:
                return "5eb1ec58ce422e4358d22a2a";
        }
    }

    public void testpost(String json) {
        String url = "https://furnik-backend.azurewebsites.net/uporabnik/signup";
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
                    SignupActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //what to do when successful response
                            tw8.setText(myResponse);
                        }
                    });
                    Intent intent = new Intent(getBaseContext(),  LoginActivity.class);
                    startActivity(intent);
                } else {
                    final String myResponse = response.body().string();
                    SignupActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //what to do when not successful response
                            tw8.setText(myResponse);
                        }
                    });
                }
            }
        });
    }
}
