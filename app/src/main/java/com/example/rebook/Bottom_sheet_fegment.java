package com.example.rebook;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Bottom_sheet_fegment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Bottom_sheet_fegment extends BottomSheetDialogFragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Bottom_sheet_fegment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Bottom_sheet_fegment.
     */
    // TODO: Rename and change types and number of parameters
    public static Bottom_sheet_fegment newInstance(String param1, String param2) {
        Bottom_sheet_fegment fragment = new Bottom_sheet_fegment();
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


        View v= inflater.inflate(R.layout.fragment_bottom_sheet_fegment, container, false);
        Button updateadd=v.findViewById(R.id.dialog_update_address_button);
        EditText add=v.findViewById(R.id.dialoog_update_address_edittext);

        updateadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                task(add);
            }
        });

        return v;
    }

    private void task(EditText add) {

        String comaddress=add.getText().toString();

        GoogleSignInAccount lacc=GoogleSignIn.getLastSignedInAccount(Bottom_sheet_fegment.super.getContext());

        if(lacc!=null){

            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference("user_detail");

            HashMap<String,Object> addaddress=new HashMap<String,Object>();
            addaddress.put("address",comaddress);
            myRef.child(lacc.getDisplayName()).updateChildren(addaddress);
            dismiss();
        }


    }
}