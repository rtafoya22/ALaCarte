package com.example.alacarte;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;

import com.example.alacarte.DB.AppDataBase;
import com.example.alacarte.DB.User;
import com.example.alacarte.DB.UserDAO;
import com.example.alacarte.productDB.Product;
import com.example.alacarte.productDB.ProductDAO;
import com.example.alacarte.productDB.ProductDataBase;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String[] meals = getResources().getStringArray(R.array.meals);

        AutoCompleteTextView editText = findViewById(R.id.autoSearch);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, meals);
        editText.setAdapter(adapter);

    }

}















//    AutoCompleteTextView autoCompleteTextView;
//    TextView textViewItemInfo;
//    Button buttonAddItemToCart;
//    UserDAO mUserDAO;
//    ProductDAO mProductDAO;
//    User mUser;
//    List<Product> mProducts;
//    ArrayList<String> mProductNames;
//
//    ArrayAdapter<String> arrayAdapter;
//    SharedPreferences sharedPreferences;
//    private static final String SHARED_PREF_NAME = "pref";
//    private static final String KEY_NAME = "username";



//    getUserDataBase();
//
//    getProductDataBase();
//
//    getSharedPreferences();
//
//    wireUpDisplay();


// mProductNames = new java.util.ArrayList<>();
//        for(Product product : mProducts){
//        if(product.getName().isEmpty() || product.getDescription().isEmpty() || product.getPrice() <= 0){
//        continue;
//        }
//        mProductNames.add(product.getName());
//        }
//        mProductNames.toArray();
//
//
//        //Initialize Adapter
//        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, mProductNames);
//
//        //Get suggestion after the number of letters typed(can also be set in xml file)
//        autoCompleteTextView.setThreshold(1);
//
//        //Set adapter
//        autoCompleteTextView.setAdapter(arrayAdapter);
//
//        //Set Listener
//        autoCompleteTextView.setOnItemClickListener((parent, view, position, id) -> {
//        textViewItemInfo.setText(mProductDAO.getItemFromName(arrayAdapter.getItem(position)).toString());
//        buttonAddItemToCart.setVisibility(View.VISIBLE);
//        //closeKeyboard();
//
//        Product cartItem = mProductDAO.getItemFromName(arrayAdapter.getItem(position));
//        if(cartItem.getQuantity() <= 0){
//        buttonAddItemToCart.setEnabled(false);
//        buttonAddItemToCart.setText(R.string.sold_out);
//        // StyleableToast.makeText(SearchActivity.this, cartItem.getName() + " is no longer in stock.", R.style.specialToast).show();
//        } else {
//        buttonAddItemToCart.setEnabled(true);
//        buttonAddItemToCart.setText(R.string.add_item_to_cart);
//        buttonAddItemToCart.setOnClickListener(v -> {
//        int currentQuantity = cartItem.getQuantity();
//        cartItem.setQuantity(currentQuantity - 1);
//        mProductDAO.update(cartItem);
//        textViewItemInfo.setText(mProductDAO.getItemFromName(arrayAdapter.getItem(position)).toString());
//        //user.getUsersCart().add(cartItem);
//        mUserDAO.updateUser(mUser);
//        // StyleableToast.makeText(getApplicationContext(), cartItem.getName() + " Added to Cart", R.style.specialToast).show();
//        if(cartItem.getQuantity() <= 0){
//        buttonAddItemToCart.setEnabled(false);
//        buttonAddItemToCart.setText(R.string.sold_out);
//        //StyleableToast.makeText(SearchActivity.this, cartItem.getName() + " is no longer in stock.", R.style.specialToast).show();
//        }
//        });
//        }
//
//        });

//    private void wireUpDisplay() {
//        textViewItemInfo = findViewById(R.id.textViewItemInfo);
//        buttonAddItemToCart = findViewById(R.id.buttonAddItemToCart);
//    }
//
//    private void getUserDataBase() {
//        mUserDAO = Room.databaseBuilder(this, AppDataBase.class, AppDataBase.dbName)
//                .allowMainThreadQueries()
//                .build()
//                .UserDAO();
//    }
//    private void getProductDataBase() {
//        mProductDAO = Room.databaseBuilder(this, ProductDataBase.class, ProductDataBase.dbName)
//                .allowMainThreadQueries()
//                .build()
//                .ProductDAO();
//        mProducts = mProductDAO.getItems();
//    }
//
//    private void getSharedPreferences(){
//        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
//        String name = sharedPreferences.getString(KEY_NAME, null);
//        if(name != null){
//            mUser = mUserDAO.getUserByUsername(name);
//        }
//    }