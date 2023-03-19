package com.example.count;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity{
    EditText et;
    Button btnCount;
    TextView showValue;
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et = findViewById(R.id.text);
        btnCount = findViewById(R.id.hitung);
        showValue = findViewById(R.id.nilai);

        btnCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                showValue.setText(String.valueOf(count));
            }
        });
        if (savedInstanceState != null){
            count = savedInstanceState.getInt("savedValue");
            showValue.setText(String.valueOf(count));
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString("savedValue",
                String.valueOf(showValue.getText()));
    }

    @Override
    public void onRestoreInstanceState (Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState != null) {
            String count = savedInstanceState.getString("savedValue");
            if (count != null)
                showValue.setText(count);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sp = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        SharedPreferences spc = getSharedPreferences("MySharedCount", MODE_PRIVATE);
        String s1 = sp.getString("et", "");
        String s2 = spc.getString("value", "");
        et.setText(s1);
        showValue.setText(s2);
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences sp = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        SharedPreferences spc = getSharedPreferences("MySharedCount", MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sp.edit();
        SharedPreferences.Editor myCount = spc.edit();
        myEdit.putString("et", et.getText().toString());
        myEdit.apply();
        myCount.putString("value", showValue.getText().toString());
        myCount.apply();
    }
}