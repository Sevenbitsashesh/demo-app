package com.todoapp.sevenbits11.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {


    EditText etName;
    EditText etPassword;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        bindView();
        initialize();
    }

    private void initialize() {
        login();
    }

    public void bindView() {

        etName = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);

        btnLogin = findViewById(R.id.btnLogin);
    }
    public void login() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("Login","clicked"+ etName.getText().toString().trim().equals("user")+" "+ etPassword.getText().toString().trim().equals("123456"));
                if(etName.getText().toString().trim().equals("user") && etPassword.getText().toString().trim().equals("123456")) {
                    Intent intent = new Intent(LoginActivity.this, NewExpenseActivity.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(LoginActivity.this,"Invalid Credential",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
