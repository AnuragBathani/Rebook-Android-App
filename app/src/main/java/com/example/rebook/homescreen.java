package com.example.rebook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class homescreen<item> extends AppCompatActivity {


    private FirebaseAuth auth;
    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;
    public NavigationView nv;
     public GoogleSignInClient mGoogleSignInClient;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homescreen);


        drawerLayout = findViewById(R.id.my_drawer_layout);
        nv=(NavigationView)findViewById(R.id.nav_view);


        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
               int menu_id=item.getItemId();

               switch (menu_id){

                   case R.id.nav_account:
                       startActivity(new Intent(homescreen.this,my_account.class));
                       break;

                   case R.id.nav_settings:
                       Toast.makeText(homescreen.this, "This is nav setting", Toast.LENGTH_LONG).show();
                       break;
                   case R.id.nav_logout:
                       mGoogleSignInClient.signOut();
                       auth.signOut();
                       startActivity(new Intent(homescreen.this,first_screen.class));
                       finish();
                       break;
                   case R.id.sell_book:
                       startActivity(new Intent(homescreen.this,Book_list.class));
                       break;

                   default:
                       return true;

               }

                return false;
            }
        });




        auth=FirebaseAuth.getInstance();
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();


        // Build a GoogleSignInClient with the options specified by gso.
         mGoogleSignInClient = GoogleSignIn.getClient(this, gso);



    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem  item1) {

        if (actionBarDrawerToggle.onOptionsItemSelected(item1)) {
            return true;
        }
        return super.onOptionsItemSelected(item1);
    }
}