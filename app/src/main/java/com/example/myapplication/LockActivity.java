package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LockActivity extends AppCompatActivity {
    EditText pw;
    Button bt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lock);
        pw = findViewById(R.id.pw);
        bt = findViewById(R.id.bt);
        bt.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v) {
                String text1 = pw.getText().toString();
                if(text1.equals("1234"))
                {
                    Intent intent =new Intent(LockActivity.this, MainActivity.class);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(LockActivity.this, "密碼錯誤!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}