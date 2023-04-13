package com.example.rebook;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Objects;

public class my_account extends AppCompatActivity {

    TextView my_acc_user_name,my_acc_email,my_acc_address,my_acc_phone;
    Button update_address,update_phone;
    Button dialog_update;
    EditText dialog_address;

   // BottomSheetDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        // intialization of xml--
        my_acc_user_name=(TextView) findViewById(R.id.my_acc_user_name);
        my_acc_email=(TextView) findViewById(R.id.my_acc_email);
        my_acc_address=(TextView)findViewById(R.id.my_acc_address);
        update_address=(Button)findViewById(R.id.update_address_button);
        update_phone=(Button)findViewById(R.id.update_phone_button);
        my_acc_phone=(TextView)findViewById(R.id.my_acc_phone);



        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);

        if(account!=null)
        {
            my_acc_user_name.setText(account.getDisplayName());
            my_acc_email.setText(account.getEmail());
            final FirebaseDatabase fb=FirebaseDatabase.getInstance();
            DatabaseReference addref=fb.getReference("user_detail/"+account.getDisplayName());
            addref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    data_holder dh=snapshot.getValue(data_holder.class);

                    my_acc_address.setText(dh.getAddress());
                    my_acc_phone.setText(dh.getPhone());

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                    Toast.makeText(my_account.this, "Please add or update your address", Toast.LENGTH_SHORT).show();
                }
            });

        }


          update_address.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {

                  Bottom_sheet_fegment bottomSheetDialogFragment=new Bottom_sheet_fegment();
                  bottomSheetDialogFragment.show(getSupportFragmentManager(),bottomSheetDialogFragment.getTag());

//
              }
          });

        update_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottom_fregment_phone bottomSheetDialogFragmentPhone=new bottom_fregment_phone();
                bottomSheetDialogFragmentPhone.show(getSupportFragmentManager(),bottomSheetDialogFragmentPhone.getTag());


            }
        });


    }

//
}