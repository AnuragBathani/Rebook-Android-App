package com.example.rebook;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class add_book extends AppCompatActivity {

    EditText price,author,book_name,phone;
    Button uploadbtn;
    ImageView imageview4;
    FloatingActionButton imagepicker;
    private final int REQ_CODE=42;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        price=(EditText) findViewById(R.id.addbook_book_price);
        author=(EditText) findViewById(R.id.addbook_author);
        book_name=(EditText) findViewById(R.id.addbook_book_name);
        phone=(EditText) findViewById(R.id.addbook_phone);
        uploadbtn=(Button) findViewById(R.id.addbook_uploadbtn);
        imagepicker=(FloatingActionButton)findViewById(R.id.add_book_image_btn);
        imageview4=(ImageView)findViewById(R.id.imageview4);


        //when image btn is clicked

        imagepicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gallery=new Intent(Intent.ACTION_PICK);
                gallery.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityIfNeeded(gallery,REQ_CODE);

            }
        });




        // when button clicked
        uploadbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String p=price.getText().toString();
                String a=author.getText().toString();
                String bn=book_name.getText().toString();
                book_data data=new book_data(bn,a,p);
                GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(add_book.super.getApplicationContext());

                if(account!=null)
                {
                    FirebaseDatabase fb=FirebaseDatabase.getInstance();
                    DatabaseReference addref=fb.getReference("user_detail");
                    addref.child(account.getDisplayName()).child("sell book").child(bn).setValue(data);
                }

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK)
        {
            if(requestCode==REQ_CODE){

                imageview4.setImageURI(data.getData());

            }

        }
    }
}