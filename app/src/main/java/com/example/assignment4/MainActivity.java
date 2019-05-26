package com.example.assignment4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    EditText editTextid,editTextpw;
    Button buttonLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextid = findViewById(R.id.uid);
        editTextpw = findViewById(R.id.pwd);
        buttonLogin = findViewById(R.id.btnLogin);


        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Retrofit retrofit = new Retrofit.Builder().baseUrl("http://10.0.2.2:3000/")
                        .addConverterFactory(GsonConverterFactory.create()).build();
                User_Interface userInterface = retrofit.create(User_Interface.class);

                Call<ResponseBody> logincall = userInterface.userLogin
                        (new User(editTextid.getText().toString(),editTextpw.getText().toString()));

                logincall.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        String session = response.headers().get("Set-Cookie");
                        Intent intent = new Intent(MainActivity.this,DashboardActivity.class);
                        intent.putExtra("mysession",session);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

    }
}
