package com.example.alacarte;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.alacarte.DB.AppDataBase;
import com.example.alacarte.DB.User;
import com.example.alacarte.DB.UserDAO;
import com.example.alacarte.databinding.ActivityHomeBinding;
import com.example.alacarte.productDB.Product;
import com.example.alacarte.productDB.ProductDAO;
import com.example.alacarte.productDB.ProductDataBase;

import java.util.List;

public class HomeActivity extends AppCompatActivity {
    private static final String USER_ID_KEY = "com.example.alacarte.usersIdKey";
    private static final String PREFERENCES_KEY = "com.example.alacarte.PREFERENCES_KEY";

    private ActivityHomeBinding mHomeBinding;

    private int mUserId = -1;
    private SharedPreferences mPreferences = null;

    private User mUser;
    private UserDAO mUserDAO;

    ProductDAO mProductDAO;
    List<Product> mProducts;

    private Button mSearchButton;
    private Button mCartButton;
    private Button mCancelOrderButton;
    private Button mBrowseButton;

    private Button mAdminButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        getUserDataBase();

        mHomeBinding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(mHomeBinding.getRoot());

        mSearchButton = mHomeBinding.buttonSearch;
        mCartButton = mHomeBinding.buttonCart;
        mCancelOrderButton = mHomeBinding.buttonCancelorder;
        mAdminButton = mHomeBinding.buttonAdmin;
        mBrowseButton = mHomeBinding.buttonBrowse;

        mAdminButton = findViewById(R.id.buttonAdmin);

        if(mUser != null && mUser.getPassword().equals("admin2")){
            mAdminButton.setVisibility(View.VISIBLE);
        }

        mSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
                startActivity(intent);
            }
        });

        mCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),CartActivity.class);
                startActivity(intent);
            }
        });

        mAdminButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AdminActivity.class);
                startActivity(intent);
            }
        });

        mBrowseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), BrowseActivity.class);
                startActivity(intent);
            }
        });
        mCancelOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CancelOrderActivity.class);
                startActivity(intent);
            }
        });

    }

    private void clearUserFromIntent(){
        getIntent().putExtra(USER_ID_KEY, -1);
    }

    private void clearUserFromPref(){
        addUserToPreference(-1);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if(mUser != null){
            MenuItem item = menu.findItem(R.id.userMenuLogout);
            item.setTitle(mUser.getUsername());
        }
        return super.onPrepareOptionsMenu(menu);
    }

    private void addUserToPreference(int userId) {
        if(mPreferences == null){
            getPrefs();
        }
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putInt(USER_ID_KEY, userId);
    }

    private void checkForUser() {
        mUserId = getIntent().getIntExtra(USER_ID_KEY, -1);
        if(mUserId != -1){
            return;
        }

        if(mPreferences == null){
            getPrefs();
        }
        mUserId = mPreferences.getInt(USER_ID_KEY, -1);

        if(mUserId != -1){
            return;
        }

        List<User> usersList = mUserDAO.getAllUsers();

        if(usersList.size() <= 0){
            User adminUser = new User("admin2","admin2", "Admin2", true);
            User testUser = new User("testuser1", "testuser1", "Testuser1" , false);

            mUserDAO.registerUser(adminUser);
            mUserDAO.registerUser(testUser);
        }
        Intent intent = LoginActivity.intentFactory(this);
        startActivity(intent);
    }

    private void getPrefs() {
        mPreferences = this.getSharedPreferences(PREFERENCES_KEY,Context.MODE_PRIVATE);
    }

    private void getUserDataBase() {
        mUserDAO = Room.databaseBuilder(this, AppDataBase.class, AppDataBase.dbName)
                .allowMainThreadQueries()
                .build()
                .UserDAO();
    }

    private void logoutUser(){
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);

        alertBuilder.setMessage(R.string.logout);

        alertBuilder.setPositiveButton(getString(R.string.yes),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        clearUserFromIntent();
                        clearUserFromPref();
                        mUserId = -1;
                        checkForUser();
                    }
                });
        alertBuilder.setNegativeButton(getString(R.string.no),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
        alertBuilder.create().show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.ex_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.userMenuLogout:
                logoutUser();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public static Intent IntentFactory(Context context, int UserId){
        Intent intent = new Intent(context, HomeActivity.class);
        intent.putExtra(USER_ID_KEY, UserId);
        return intent;
    }
}

