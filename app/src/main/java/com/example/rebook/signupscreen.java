package com.example.rebook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class signupscreen extends AppCompatActivity {

  private   Button signupbtn;
    private  TextInputEditText textinputlayoutsignupusername,textinputlayoutsignupemail,textinputlayoutsignuppass;
    private FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signupscreen);

        signupbtn=(Button) findViewById(R.id.signupcustom);
        textinputlayoutsignupusername= (TextInputEditText) findViewById(R.id.textinputsignupusername_edittext);
        textinputlayoutsignupemail=(TextInputEditText) findViewById(R.id.textinputsignupemail_edittext);
        textinputlayoutsignuppass=(TextInputEditText) findViewById(R.id.textinputsignuppass_edittext);
        auth=FirebaseAuth.getInstance();

        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Username=textinputlayoutsignupusername.getText().toString();
                String Email=textinputlayoutsignupemail.getText().toString();
                String Password=textinputlayoutsignuppass.getText().toString();

                if (TextUtils.isEmpty(Username)||TextUtils.isEmpty(Email)||TextUtils.isEmpty(Password)){

                    Toast.makeText(signupscreen.this, "please Fill all details", Toast.LENGTH_SHORT).show();
                }
                else {
                    signup(Username,Email,Password);
                }


            }
        });


    }

    private void signup(String username, String email, String password) {

        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(signupscreen.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){

                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();


                    UserProfileChangeRequest.Builder builder = new UserProfileChangeRequest.Builder()
                            .setDisplayName(username);
                    final UserProfileChangeRequest changeRequest = builder.build();

                    user.updateProfile(changeRequest);

                    Toast.makeText(signupscreen.this, "Sign up Successfully", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(signupscreen.this,loginscreen.class));
                }
                else {
                    Toast.makeText(signupscreen.this, "Sign up Failed", Toast.LENGTH_SHORT).show();

                }
            }
        });

    }
}