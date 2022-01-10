package com.example.myapplication;


import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Notification;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    EditText title_input, ch_input;
    Button update_button, delete_button;
    String id, title, ch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        title_input = findViewById(R.id.title_input2);
        ch_input = findViewById(R.id.ch_input2);
        update_button = findViewById(R.id.update_button);
        delete_button = findViewById(R.id.delete_button);
        getAndSetIntentData();

        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(title);
        }

        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
                title = title_input.getText().toString().trim();
                ch = ch_input.getText().toString().trim();
                myDB.updateData(id, title, ch);
            }
        });

        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();
            }
        });

    }

    void getAndSetIntentData(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("標題") &&
                getIntent().hasExtra("備忘錄")){
            //Getting Data from Intent
            id = getIntent().getStringExtra("id");
            title = getIntent().getStringExtra("標題");
            ch = getIntent().getStringExtra("備忘錄");

            //Setting Intent Data
            title_input.setText(title);
            ch_input.setText(ch);
            Log.d("stev", title+" "+ch);
        }else{
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("刪除 " + title + " ?");
        builder.setMessage("確定要刪除 " + title + " ?");
        builder.setPositiveButton("是", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
                myDB.deleteOneRow(id);
                finish();
                //dfdskpfksaopfkopdksfaopjfpodsjfjsofajoidfjoisajfioajfoi
                // jfdoisafjoidjoifasjoijfoiajfoidjaofijdifoaj
                // fjdsoiafjoidfjaoifjoiafjos
                // fdjoiafjdoifjoiafjoifjisjoafjasoigjaoif
                //dsfhashfodjfoasfjodsfjoaojif
                //fdsafsafdsafdsafdsfasd
                //dsfjaoisfjdoisajfoiasjoi
            }
        });
        builder.setNegativeButton("否", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }

}