package com.example.alacarte;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alacarte.productDB.Product;
import com.example.alacarte.productDB.ProductDAO;
import com.example.alacarte.productDB.ProductDataBase;

import java.util.List;


public class AdminAddItemActivity extends AppCompatActivity {

    TextView textViewItemsDisplay;
    EditText editTextItemName;
    EditText editTextItemPrice;
    EditText editTextItemDescription;
    EditText editTextNumberOfItems;
    Button buttonSubmitAddition;
    ProductDAO productDAO;
    List<Product> products;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_item);

        textViewItemsDisplay = findViewById(R.id.textViewItemsDisplay);
        textViewItemsDisplay.setMovementMethod(new ScrollingMovementMethod());

        editTextItemName = findViewById(R.id.editTextItemName);
        editTextItemPrice = findViewById(R.id.editTextItemPrice);
        editTextItemDescription = findViewById(R.id.editTextItemDescription);
        editTextNumberOfItems = findViewById(R.id.editTextNumberOfItems);

        buttonSubmitAddition = findViewById(R.id.buttonSubmitAddition);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
    private void implementProductDAO() {
        productDAO = Room.databaseBuilder(this, ProductDataBase.class, ProductDataBase.dbName)
                .allowMainThreadQueries()
                .build()
                .ProductDAO();
    }


}