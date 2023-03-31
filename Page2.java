package com.example.labreport3;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Page2 extends AppCompatActivity {
    Button previous, next, home, update, delete;
    TextView name, university;
    SQLiteDatabase db;
    @SuppressLint({"MissingInflatedId", "Range"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_page2);

        previous = findViewById(R.id.previous);
        next = findViewById(R.id.next);
        home = findViewById(R.id.home);
        name = findViewById(R.id.name);
        university = findViewById(R.id.univesity);


        db = openOrCreateDatabase("Shamim", MODE_PRIVATE, null);

        final Cursor c= db.rawQuery("select* from student", null);
        c.moveToFirst();
        name.setText(c.getString(c.getColumnIndex("name")));
        university.setText(c.getString(c.getColumnIndex("university")));

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    c.moveToPrevious();
                    name.setText(c.getString(c.getColumnIndex("name")));
                    university.setText(c.getString(c.getColumnIndex("university")));
                }
                catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "First Record", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    c.moveToNext();
                    name.setText(c.getString(c.getColumnIndex("name")));

                    university.setText(c.getString (c.getColumnIndex("university")));
                }
                catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Last Record", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        });


        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "Back to Home", Toast.LENGTH_SHORT).show();
            }
        });

    }
}