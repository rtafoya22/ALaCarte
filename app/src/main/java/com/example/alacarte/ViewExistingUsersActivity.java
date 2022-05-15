package com.example.alacarte;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import com.example.alacarte.DB.AppDataBase;
import com.example.alacarte.DB.User;
import com.example.alacarte.DB.UserDAO;

import java.util.List;

public class ViewExistingUsersActivity extends AppCompatActivity {

    TextView textViewExistingUsers;
    UserDAO userDAO;
    List<User> users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_existing_users);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        textViewExistingUsers = findViewById(R.id.textViewExistingUsers);
        textViewExistingUsers.setMovementMethod(new ScrollingMovementMethod());

        getUserDataBase();

        printAllUsers();
    }

    private void printAllUsers() {
        if(users.size() <= 0){
            textViewExistingUsers.setText(R.string.no_users);
        }
        StringBuilder sb = new StringBuilder();
        for(User user : users){
            sb.append(user);
            sb.append("\n");
            sb.append("------------------");
            sb.append("\n");
        }
        textViewExistingUsers.setText(sb.toString());
    }

    private void getUserDataBase() {
        userDAO = Room.databaseBuilder(this, AppDataBase.class, AppDataBase.dbName)
                .allowMainThreadQueries()
                .build()
                .UserDAO();
        users = userDAO.getUsers();
    }

}