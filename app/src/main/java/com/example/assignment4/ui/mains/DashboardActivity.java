package com.example.assignment4.ui.mains;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import com.example.assignment4.R;
import com.example.assignment4.User_Interface;
import com.example.assignment4.models.Items;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DashboardActivity extends AppCompatActivity {

    TextView textView;
    String session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        textView = findViewById(R.id.tv);

        Intent intent = getIntent();
        session = intent.getStringExtra("mysession");

        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://10.0.2.2:3000/")
                .addConverterFactory(GsonConverterFactory.create()).build();
        User_Interface userInterface = retrofit.create(User_Interface.class);
        Call<List<Items>> callitems = userInterface.getItems(session);

        callitems.enqueue(new Callback<List<Items>>() {
            @Override
            public void onResponse(Call<List<Items>> call, Response<List<Items>> response) {
                List<Items> items = response.body();
                for(Items item:items){
                    String data= "";
                    data += item.getItemName()+ "\n";
                    data +=item.getItemPrice()+ "\n";
                    data +=item.getItemImageName()+ "\n";
                    data +=item.getItemDescription()+ "\n";
                    textView.append(data);
                }
            }

            @Override
            public void onFailure(Call<List<Items>> call, Throwable t) {
                Toast.makeText(DashboardActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
