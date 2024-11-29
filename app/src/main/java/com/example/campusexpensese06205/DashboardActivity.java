package com.example.campusexpensese06205;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class DashboardActivity extends AppCompatActivity {
    Button btnLogin;
    Button btnCancel; // Sửa từ btnCancle thành btnCancel
    TextView tvUser;

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        // Initialize buttons
        btnLogin = findViewById(R.id.btnLogin);
        btnCancel = findViewById(R.id.btnCancel); // Khởi tạo btnCancel

        // Initialize TextView
        tvUser = findViewById(R.id.tvUser);

        // Lấy dữ liệu từ activity khác
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            String name = bundle.getString("MY_Name", ""); // Đổi biến tên
            tvUser.setText(name); // Hiển thị tên người dùng
        }
    }
}
