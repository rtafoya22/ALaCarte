package com.example.alacarte;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.alacarte.DB.AppDataBase;
import com.example.alacarte.DB.User;
import com.example.alacarte.DB.UserDAO;
import com.example.alacarte.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding mLoginBinding;

    private EditText mUsernameInput;
    private EditText mPasswordInput;
    private Button mLoginButtonSu;
    private Button mLoginButton;

    private EditText mUsernameSign;
    private EditText mPasswordSign;
    private Button mSignupButton;

    private UserDAO mUserDAO;

    private String mUsername, mPassword;
    private User mUsers;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getDataBase();
        wireUpDisplay();

        mUsernameSign = findViewById(R.id.loginUserName);
        mPasswordSign = findViewById(R.id.loginPassword);
        mSignupButton = findViewById(R.id.loginButtonHome);

        getDataBase();

        mSignupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = mUsernameSign.getText().toString().trim();
                String password = mPasswordSign.getText().toString().trim();

                User user = mUserDAO.getUser(username, password);
                if (user != null) {
                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                    //intent.putExtra("User", user);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(LoginActivity.this, "Unregistered user, or incorrect", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void getDataBase() {
        mUserDAO = Room.databaseBuilder(this, AppDataBase.class, AppDataBase.dbName)
                .allowMainThreadQueries()
                .build()
                .UserDAO();
    }

    private void wireUpDisplay() {

        mLoginBinding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(mLoginBinding.getRoot());

        mUsernameInput = mLoginBinding.loginUserName;
        mPasswordInput = mLoginBinding.loginPassword;
        mLoginButton = mLoginBinding.loginButtonHome;
        mLoginButtonSu = mLoginBinding.loginButtonSign;

        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getValuesFromScreen();

                if(isUserInDatabase()){
                    if(!validatePassword()){
                        Toast.makeText(LoginActivity.this, "Invalid Password", Toast.LENGTH_SHORT)
                                .show();
                    } else {
                        Intent intent = HomeActivity.IntentFactory(getApplicationContext(), mUsers.getId());
                        startActivity(intent);
                    }
                }
            }
        });
        mLoginButtonSu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    private boolean validatePassword() {
        return mUsers.getPassword().equals(mPassword);
    }

    private void getValuesFromScreen(){
        mUsername = mUsernameInput.getText().toString();
        mPassword = mPasswordInput.getText().toString();
    }

    private boolean isUserInDatabase(){

        mUsers = mUserDAO.getUserByUsername(mUsername);

        if(mUsers == null){
            Toast.makeText(this, "no user" + mUsername + " found", Toast.LENGTH_SHORT)
                    .show();
            return false;
        }

        return true;
    }
    public static Intent intentFactory(Context context){
        Intent intent = new Intent(context, LoginActivity.class);

        return intent;
    }
}