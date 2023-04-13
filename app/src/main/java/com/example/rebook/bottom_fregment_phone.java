package com.example.rebook;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link bottom_fregment_phone#newInstance} factory method to
 * create an instance of this fragment.
 */
public class bottom_fregment_phone extends BottomSheetDialogFragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public bottom_fregment_phone() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment bottom_fregment_phone.
     */
    // TODO: Rename and change types and number of parameters
    public static bottom_fregment_phone newInstance(String param1, String param2) {
        bottom_fregment_phone fragment = new bottom_fregment_phone();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Button updatebtn;
        EditText updateedit;
        View v1=inflater.inflate(R.layout.fragment_bottom_fregment_phone, container, false);

       updatebtn=(Button)v1.findViewById(R.id.dialog_update_address_button1);
       updateedit=(EditText) v1.findViewById(R.id.dialoog_update_address_edittext1);

       updatebtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               task(updateedit);
           }
       });


        return v1;
    }

    private void task(EditText updateedit) {

        String comphone=updateedit.getText().toString();

        GoogleSignInAccount lacc= GoogleSignIn.getLastSignedInAccount(bottom_fregment_phone.super.getContext());

        if(lacc!=null){

            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference("user_detail");

            HashMap<String,Object> addaddress=new HashMap<String,Object>();
            addaddress.put("phone",comphone);
            myRef.child(lacc.getDisplayName()).updateChildren(addaddress);
            dismiss();


        }
    }
}