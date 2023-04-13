package com.example.rebook;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class loginscreen extends AppCompatActivity {

    Button loginbtn;
    TextInputEditText textinputlayoutlogin,textPass;
    private FirebaseAuth auth;

    @SuppressLint("CutPasteId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.loginscreen);
        loginbtn=(Button) findViewById(R.id.loginbtn);
        textinputlayoutlogin=(TextInputEditText) findViewById(R.id.textinputlogin_name);
        textPass=(TextInputEditText) findViewById(R.id.textinputloginpass_edittext);
        auth=FirebaseAuth.getInstance();
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Email=textinputlayoutlogin.getText().toString();
                String Password=textPass.getText().toString();
                if (TextUtils.isEmpty(Email)||TextUtils.isEmpty(Password))
                {
                    Toast.makeText(loginscreen.this, "Please enter email and pass word", Toast.LENGTH_SHORT).show();

                }
                else {
                    login(Email,Password);
                }

            }
        });



    }

    private void login(String email, String password) {
        auth.signInWithEmailAndPassword(email, password).addOnSuccessListener(loginscreen.this, new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                    startActivity(new Intent(loginscreen.this,homescreen.class));
            }
        });
    }
}
