package com.example.rebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Book_list extends AppCompatActivity {

    FloatingActionButton fb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        fb=(FloatingActionButton) findViewById(R.id.fab);

        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(Book_list.this,add_book.class));

            }
        });



    }
}