package com.example.myapplication;


import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    EditText eng_input, ch_input;
    Button update_button, delete_button;
    String id, eng, ch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        eng_input = findViewById(R.id.eng_input2);
        ch_input = findViewById(R.id.ch_input2);
        update_button = findViewById(R.id.update_button);
        delete_button = findViewById(R.id.delete_button);
        getAndSetIntentData();

        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
                eng = eng_input.getText().toString().trim();
                ch = ch_input.getText().toString().trim();
                myDB.updateData(id, eng, ch);
            }
        });

    }

    void getAndSetIntentData(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("單字") &&
                getIntent().hasExtra("翻譯")){
            //Getting Data from Intent
            id = getIntent().getStringExtra("id");
            eng = getIntent().getStringExtra("單字");
            ch = getIntent().getStringExtra("翻譯");

            //Setting Intent Data
            eng_input.setText(eng);
            ch_input.setText(ch);
            Log.d("stev", eng+" "+ch+" ");
        }else{
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }
}