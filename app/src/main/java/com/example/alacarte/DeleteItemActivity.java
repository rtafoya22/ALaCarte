package com.example.alacarte;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class DeleteItemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_item);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
}