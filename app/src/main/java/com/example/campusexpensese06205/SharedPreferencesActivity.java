package com.example.campusexpensese06205;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SharedPreferencesActivity  extends AppCompatActivity {
    EditText edtNumber1, edtNumber2,edtResult;

    Button btnCalculate, btnClear;

    TextView tvHistory;
    private String history ="";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_preference);
        edtNumber1 = findViewById(R.id.edtNumber1);
        edtNumber2 = findViewById(R.id.edtNumber2);
        edtResult = findViewById(R.id.edtResult);
        btnCalculate = findViewById(R.id.btnCalculate);
        btnClear = findViewById(R.id.btnClear);
        tvHistory = findViewById(R.id.tvHistory);
        edtResult.setEnabled(false);

        // lay du lieu tu SharedPreferences
        SharedPreferences shared = getSharedPreferences("calculateMath",MODE_PRIVATE);
        history = shared.getString("historyMath","");
        tvHistory.setText(history);//

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                long number1 = Integer.parseInt(edtNumber1.getText().toString().trim());
                long number2 = Integer.parseInt(edtNumber2.getText().toString().trim());
                long result = number1 + number2;
                edtResult.setText(String.valueOf(result));
                history += number1 + " + " + number2 + " = " + result;
                tvHistory.setText(history);
                edtNumber1.setText("");
                edtNumber2.setText("");
                history +="\n";

            }
        });
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                history = "";
                tvHistory.setText(history);
                edtNumber1.setText("");
                edtNumber2.setText("");
                edtResult.setText("");
            }
        });


    }

    @Override
    protected void onPause() {
        super.onPause();
        // xu li luu du lieu
        SharedPreferences myPrefs =  getSharedPreferences("calculateMath", MODE_PRIVATE);
        SharedPreferences.Editor editor= myPrefs.edit();
        editor.putString("historyMath",history);
        editor.apply();
    }
}
;