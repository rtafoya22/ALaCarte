package com.example.alacarte;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminActivity extends AppCompatActivity {

    Button buttonAddItem;
    Button buttonDeleteItem;
    Button buttonModifyItem;
    Button buttonViewExistingItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        buttonAddItem = findViewById(R.id.buttonAddItem);
        buttonDeleteItem = findViewById(R.id.buttonDeleteItem);
        buttonModifyItem = findViewById(R.id.buttonModifyItem);
        buttonViewExistingItems = findViewById(R.id.buttonViewExistingItems);


        buttonAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AdminAddItemActivity.class);
                startActivity(intent);
            }
        });

        buttonDeleteItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), DeleteItemActivity.class);
                startActivity(intent);
            }
        });

        buttonModifyItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ModifyItemActivity.class);
                startActivity(intent);
            }
        });

        buttonViewExistingItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ViewExistingUsersActivity.class);
                startActivity(intent);
            }
        });
    }
}