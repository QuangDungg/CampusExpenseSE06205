package com.example.campusexpensese06205;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class UpdatePasswordActivity extends AppCompatActivity {
    EditText edtNewPassword, edtConfirmPassword;
    Button btnUpdatePassword;
    UserDb userDb;
    Intent intent;
    Bundle bundle;
    private String username;
    private String email;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_password);
        edtNewPassword = findViewById(R.id.edtNewPassword);
        edtConfirmPassword = findViewById(R.id.edtConfirmPassword);
        btnUpdatePassword = findViewById(R.id.btnUpdate);
        userDb = new UserDb(UpdatePasswordActivity.this);
        intent = getIntent();
        bundle = intent.getExtras();
        if (bundle != null) {
            username = bundle.getString("USERNAME_ACCOUNT", "");
            email = bundle.getString("EMAIL_ACCOUNT", "");
        }
        updatePassword();
    }

    private void updatePassword() {
        btnUpdatePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newPassword = edtNewPassword.getText().toString().trim();
                String confirmPassword = edtConfirmPassword.getText().toString().trim();
                if(TextUtils.isEmpty(newPassword)){
                    edtNewPassword.setError("New password not empty");
                    return;
                }
                if(TextUtils.isEmpty(confirmPassword)){
                    edtConfirmPassword.setError("Confirm password not empty");
                    return;
                }
                if(!confirmPassword.equals(newPassword)){
                    edtConfirmPassword.setError("Confirm password do not equals new Password");
                    return;
                }
                int update = userDb.updatePassword(newPassword,username, email);
                if(update == -1) {
                    //loi khong update duoc mat khau
                    Toast.makeText(UpdatePasswordActivity.this, "Update password failed", Toast.LENGTH_SHORT).show();
                } else{
                    // thanh cong
                    Intent intentLogin = new Intent(UpdatePasswordActivity.this, LoginActivity.class);
                    startActivity(intentLogin);
                    finish();
                }
            }
        });
    }
}
