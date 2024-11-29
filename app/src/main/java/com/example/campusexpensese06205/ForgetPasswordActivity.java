package com.example.campusexpensese06205;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.campusexpensese06205.database.UserDb;


public class ForgetPasswordActivity extends AppCompatActivity {
    EditText edtUsername, edtEmail;
    Button btnSubmit, btnCancel;
    UserDb userDb;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        edtUsername = findViewById(R.id.edtUsername);
        edtEmail = findViewById(R.id.edtEmail);
        btnSubmit = findViewById(R.id.btnSubmit);
        btnCancel = findViewById(R.id.btnCancel);
        userDb = new UserDb(ForgetPasswordActivity.this);

        backToLogin();
        checkForgetPassword();
    }

    private void checkForgetPassword(){
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = edtUsername.getText().toString().trim();
                String email = edtEmail.getText().toString().trim();
                if(textUtils.isEmpty(username)) {
                    edtUsername.setError("Username can be not empty");
                    return;
                }
                if(textUtils.isEmpty(email)){
                    edtEmail.setError("Email can be not empty");
                    return;
                }
                boolean checking = userDb.checkUsernameEmail(username, email);
                if(checking) {
                    // cho phep cap nhat mat khau
                    Intent intent = new Intent ( ForgetPasswordActivity.this, UpdatePasswordActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("USERNAME_ACCOUNT", username);
                    bundle.putString("EMAIL_ACCOUNT", email);
                    intent.putExtras(bundle);
                    startActivity(intent);
                } else {
                    Toast.makeText(ForgetPasswordActivity.this, "Username or Email invalid", Toast.LENGTH_SHORT).show();


                }
            }
        });
    }

    private void backToLogin(){
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtUsername.setText("");
                edtEmail.setText("");
                Intent intent = new Intent(ForgetPasswordActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
