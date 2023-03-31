package com.example.labreport3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button insert, view, exit;
    EditText name, university;
    String stdname, uniname;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);
        insert = findViewById(R.id.insert);
        view = findViewById(R.id.view);
        exit = findViewById(R.id.exit);
        name = findViewById(R.id.name);
        university = findViewById(R.id.univesity);

        db = openOrCreateDatabase("Shamim", MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS student(name VARCHAR, university VARCAR);");

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stdname = name.getText().toString();
                uniname = university.getText().toString();

                db.execSQL("INSERT INTO student VALUES('"+stdname+"', '"+uniname+"');");
                name.setText(null);
                university.setText(null);

                Toast.makeText(getApplicationContext(), "Details added", Toast.LENGTH_SHORT).show();

            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.exit(0);
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Page2.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "Viewing Student List", Toast.LENGTH_SHORT).show();
            }
        });
    }
}