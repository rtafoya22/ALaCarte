package com.example.alacarte;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import com.example.alacarte.productDB.Product;
import com.example.alacarte.productDB.ProductDAO;
import com.example.alacarte.productDB.ProductDataBase;

import java.util.List;

public class BrowseActivity extends AppCompatActivity {

    TextView textViewBrowseItems;
    ProductDAO mProductDAO;
    List<Product> mProducts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        textViewBrowseItems = findViewById(R.id.textViewBrowseProducts);
        textViewBrowseItems.setMovementMethod(new ScrollingMovementMethod());

        //getProductDataBase();

        //printAllItems();

    }

    private void printAllItems() {
        if(mProducts.size() <= 0){
            textViewBrowseItems.setText(R.string.no_items);
        }
        StringBuilder sb = new StringBuilder();
        for(Product product : mProducts){
            if(product.getName().isEmpty()){
                continue;
            }
            sb.append(product);
            sb.append("\n");
            sb.append("------------------------");
            sb.append("\n");
        }
        textViewBrowseItems.setText(sb.toString());
    }

    private void getProductDataBase() {
        mProductDAO = Room.databaseBuilder(this, ProductDataBase.class, ProductDataBase.dbName)
                .allowMainThreadQueries()
                .build()
                .ProductDAO();
        mProducts = mProductDAO.getProducts();
    }
}