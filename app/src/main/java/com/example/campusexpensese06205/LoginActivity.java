package com.example.campusexpensese06205;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.campusexpensese06205.model.UserModel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class LoginActivity extends AppCompatActivity {
    TextView tvRegister, tvForgetPassword;
    EditText edtUser, edtPass;
    Button btnLogin;
    UserDb userDb;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linear_layout_login);
        tvRegister = findViewById(R.id.tvRegister);
        edtUser = findViewById(R.id.edtUsername);
        edtPass = findViewById(R.id.edtPassword);
        btnLogin= findViewById(R.id.btnLogin);
        userDb = new UserDb(LoginActivity.this);
        tvForgetPassword = findViewById(R.id.tvForgetPassword);

        tvForgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,ForgetPasswordActivity.class);
                startActivity(intent);
            }
        });

        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
        loginWithDatabaseSQLite();



    }
    private void loginWithDataFile(){
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = edtUser.getText().toString().trim();
                String pass = edtPass.getText().toString().trim();
                if(TextUtils.isEmpty(user)){
                    edtUser.setError("Username can be not empty");
                    return;
                }
                if(TextUtils.isEmpty(pass)){
                    edtPass.setError("Password can be not empty");
                    return;
                }
                // doc du lieu tu file trong local storage
                // check du lieu nguoi dung dang nhap
                try{
                    UserModel checkLogin = userDb.checkUserLogin(user, pass);
                    assert checkLogin !=null;

                    if (checkLogin.getUsername() != null){
                        Intent intent = new Intent(LoginActivity.this,MenuActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putInt("ID_USER", checkLogin.getId());
                        bundle.putString("USERNAME_USER", checkLogin.getUsername());
                        intent.putExtras(bundle);
                        startActivity(intent);
                        finish();

                    }else {
                        Toast.makeText(LoginActivity.this,"Account invalid",Toast.LENGTH_LONG).show();
                    }

                }catch (Exception e){
                    throw new RuntimeException(e);

                }

            }
        });
    }

    private void loginWithDatabaseSQLite() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = edtUser.getText().toString().trim();
                String pass = edtPass.getText().toString().trim();
                if(TextUtils.isEmpty(user)){
                    edtUser.setError("Username can be not empty");
                    return;
                }
                if(TextUtils.isEmpty(pass)){
                    edtPass.setError("Password can be not empty");
                    return;
                }
                // doc du lieu tu file trong local storage
                // check du lieu nguoi dung dang nhap
                try{
                    FileInputStream fileInputStream = openFileInput("account.txt");
                    int read = -1;
                    StringBuilder builder = new StringBuilder();
                    while ((read = fileInputStream.read()) != -1){
                        builder.append((char) read);
                    }
                    boolean checkLogin = false;
                    String[] infoAccount = null;
                    infoAccount = builder.toString().trim().split("\n");
                    for (String account :infoAccount){
                        String username = account.substring(0,account.indexOf("|")).trim();
                        String password = account.substring(account.indexOf("|")+1).trim();
                        if (username.equals(user) && password.equals(pass)){
                            checkLogin = true;
                            break;
                        }

                    }
                    if (checkLogin){
                        Intent intent = new Intent(LoginActivity.this,MenuActivity.class);
                        startActivity(intent);
                        finish();

                    }else {
                        Toast.makeText(LoginActivity.this,"Account invalid",Toast.LENGTH_LONG).show();
                    }

                }catch (Exception e){
                    throw new RuntimeException(e);

                }

            }
        });
    }
}