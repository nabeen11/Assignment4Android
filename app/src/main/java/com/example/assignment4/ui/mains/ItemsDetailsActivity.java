package com.example.assignment4.ui.mains;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.assignment4.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class ItemsDetailsActivity extends AppCompatActivity {

    TextView name, description, price;
    CircleImageView circleImageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items_details);

        name = findViewById(R.id.detailName);
        description = findViewById(R.id.detailDescription);
        price = findViewById(R.id.detailPrice);
        circleImageView = findViewById(R.id.detailImgView);

        Bundle bundle = getIntent().getExtras();

        if (bundle != null){
            circleImageView.setImageResource(bundle.getInt("image"));
            name.setText(bundle.getString("name"));
            price.setText(bundle.getString("price"));
            description.setText(bundle.getString("description"));

        }
    }
}


