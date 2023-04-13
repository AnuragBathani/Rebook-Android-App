package com.example.rebook;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;




public class first_screen extends AppCompatActivity {

    Button signupmain,googlesignup;
    TextView loginmain;
    private Object Intent;
    private FirebaseAuth myauth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_screen);

         googlesignup = (Button) findViewById(R.id.googlesignup);
        signupmain=(Button) findViewById(R.id.signupmain);
        loginmain=findViewById(R.id.loginmain);

        if(myauth.getCurrentUser()!=null){

            startActivity(new Intent(first_screen.this,homescreen.class));
            finish();

        }



        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();


        // Build a GoogleSignInClient with the options specified by gso.
        GoogleSignInClient mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        // Check for existing Google Sign In account, if the user is already signed in
        // the GoogleSignInAccount will be non-null.

        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);

        if (account!=null)
        {

            startActivity(new Intent(first_screen.this,homescreen.class));
            finish();
        }


        ActivityResultLauncher<Intent> activityResultLauncher=registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {

                Task<GoogleSignInAccount> task=GoogleSignIn.getSignedInAccountFromIntent(result.getData());
                handalSignInTask(task);
            }
        });



        googlesignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent signInIntent=mGoogleSignInClient.getSignInIntent();

                activityResultLauncher.launch(signInIntent);


            }
        });


        signupmain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              Intent i =new Intent(first_screen.this,signupscreen.class);
              startActivity(i);
              finish();

            }
        });

        loginmain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {




                Intent i =new Intent(first_screen.this,loginscreen.class);
                startActivity(i);
                finish();

            }
        });
    }

    private void handalSignInTask(Task<GoogleSignInAccount> task) {
        try {
            GoogleSignInAccount acc1=task.getResult(ApiException.class);



            final String user_name=acc1.getDisplayName();
            final String Email=acc1.getEmail();
            final Uri getPhotoUrl=acc1.getPhotoUrl();

            DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference("user_detail");
            rootRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot snapshot) {
                    if (snapshot.hasChild(user_name)) {

                    }
                    else {
                        HashMap<String ,Object>mymap=new HashMap<String ,Object>();
                        mymap.put("user_name",user_name);
                        mymap.put("Email",Email);


                        FirebaseDatabase db=FirebaseDatabase.getInstance();
                        DatabaseReference temp=db.getReference("user_detail");

                        temp.child(user_name).setValue(mymap);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });




            // start home screen
            startActivity(new Intent(first_screen.this,homescreen.class));
            finish();

        } catch (ApiException e) {
            e.printStackTrace();
            Toast.makeText(this, "failed or canceled", Toast.LENGTH_SHORT).show();
        }
    }




}