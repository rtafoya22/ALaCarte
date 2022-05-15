package com.example.alacarte;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.alacarte.DB.AppDataBase;
import com.example.alacarte.DB.User;
import com.example.alacarte.DB.UserDAO;
import com.example.alacarte.databinding.ActivityRegisterBinding;

public class RegisterActivity extends AppCompatActivity {

    private ActivityRegisterBinding mRegisterBinding;

    private EditText mUsernameInput;
    private EditText mPasswordInput;
    private EditText mRePasswordInput;

    private EditText mUsernameSign;
    private EditText mPasswordSign;
    private Button mSignupButton;

    private Button mSignUp;
    private Button mLoginReturn;

     UserDAO mUserDAO;

   private String mUsername, mPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        getUserDataBase();
        wireupDisplay();

        mRegisterBinding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(mRegisterBinding.getRoot());


        mUsernameInput = mRegisterBinding.usernameSignup;
        mPasswordInput = mRegisterBinding.passwordSignup;
        mRePasswordInput = mRegisterBinding.passwordReSignup;
        mSignUp = mRegisterBinding.buttonSignUp;
        mLoginReturn = mRegisterBinding.buttonBackLogin;

        mLoginReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

        mSignUp.setOnClickListener(v -> {
            String userName = mUsernameInput.getText().toString().trim();
            String password = mPasswordInput.getText().toString().trim();
            boolean admin = isAdmin();
            User newUser = new User(userName, password, password, admin);
            if(validAccount(newUser)){
                mUserDAO.registerUser(newUser);
                Toast.makeText(RegisterActivity.this, "New User Registered!", Toast.LENGTH_SHORT).show();

                startActivity(new Intent(RegisterActivity.this, MainActivity.class));
            }
        });

    }
    private void getUserDataBase() {
        mUserDAO = Room.databaseBuilder(this, AppDataBase.class, AppDataBase.dbName)
                .allowMainThreadQueries()
                .build()
                .UserDAO();
    }

    private void getValuesFromDisplay(){
        mUsername = mUsernameInput.getText().toString();
        mPassword = mPasswordInput.getText().toString();
    }

    private void wireupDisplay(){
        mUsernameSign = findViewById(R.id.usernameSignup);
        mPasswordSign = findViewById(R.id.passwordSignup);

        mSignupButton = findViewById(R.id.buttonSignUp);

        mSignupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getValuesFromDisplay();
                User user = mUserDAO.getUser(mUsername, mPassword);
                if(user != null){
                    mUserDAO.registerUser(user);
                    Toast.makeText(RegisterActivity.this, "New user added\n"+user, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    private boolean validAccount(User user){
        if(mUserDAO.getUserByUsername(user.getUsername()) != null){
            Toast.makeText(RegisterActivity.this, "Username already taken, please choose a different one.", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(user.getUsername().isEmpty() || user.getPassword().length() < 6){
            Toast.makeText(RegisterActivity.this, "Password doesn't meet requirements", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private boolean isAdmin(){
        return mPasswordInput.toString().equals("admin2") && mUsernameInput.toString().equals("admin2");
    }
}