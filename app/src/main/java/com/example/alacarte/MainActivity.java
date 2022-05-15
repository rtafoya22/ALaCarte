package com.example.alacarte;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.alacarte.DB.AppDataBase;
import com.example.alacarte.DB.User;
import com.example.alacarte.DB.UserDAO;
import com.example.alacarte.databinding.ActivityMainBinding;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mActivityMainBinding;


    private Button mButtonLogin;
    private Button mButtonRegister;

    private UserDAO mUserDAO;
    private int mUserId = -1;

    List<User> users;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mActivityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mActivityMainBinding.getRoot());

        mButtonLogin = mActivityMainBinding.buttonLogin;
        mButtonRegister = mActivityMainBinding.buttonRegister;

        getUserDataBase();
        checkForUser();

        mButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });


        mButtonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
    private void getUserDataBase() {
        mUserDAO = Room.databaseBuilder(this, AppDataBase.class, AppDataBase.dbName)
                .allowMainThreadQueries()
                .build()
                .UserDAO();
    }

    private void checkForUser() {
        if(mUserId != -1){
            return;
        }

        users = mUserDAO.getAllUsers();
        if(users.size() <= 0){
            User admin = new User("admin2", "admin2", "admin", true);
            users.add(admin);

            User testUser = new User("testuser1", "testuser1", "user", false);
            users.add(testUser);

            mUserDAO.registerUser(testUser, admin);
        }
    }
}