package com.example.projectuxlab;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {

    Button backbtn, submitbtn;
    TextView title, price;
    EditText quantityTF;
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        String titleString = getIntent().getStringExtra("Name");
        String priceString = getIntent().getStringExtra("Price");
        int imageInt = getIntent().getIntExtra("Image", 0);

        title = findViewById(R.id.detailTitleTV);
        price = findViewById(R.id.itemPriceTV);
        image = findViewById(R.id.detailImageView);
        backbtn = findViewById(R.id.backButton);
        submitbtn = findViewById(R.id.buyButton);
        quantityTF = findViewById(R.id.quantityTF);

        title.setText(titleString);
        price.setText(priceString);
        image.setImageResource(imageInt);

        submitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                int itemQty = Integer.parseInt(quantityTF.getText().toString());
                String StrQty = quantityTF.getText().toString();

//                Log.i("testprint", String.valueOf(quantityTF.getText()));
                if(StrQty.isEmpty() || StrQty.matches("0")){
                    submitbtn.setBackgroundResource(R.drawable.buttonpressed_background);
                    Toast.makeText(DetailActivity.this, "Quantity must be filled and more than 0",
                            Toast.LENGTH_SHORT).show();
                }else{
                    submitbtn.setBackgroundResource(R.drawable.buttonpressed_background);
                    Toast.makeText(DetailActivity.this, "Transaction Succeeded!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(DetailActivity.this, ItemActivity.class);
                    startActivity(intent);
                }

            }
        });

        backbtn.setOnClickListener(e->{
            backbtn.setBackgroundResource(R.drawable.buttonpressed_background);
            Intent intent = new Intent(DetailActivity.this, ItemActivity.class);
            startActivity(intent);
        });


    }
}