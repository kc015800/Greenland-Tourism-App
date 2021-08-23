package com.kc.places;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;


public class udupifragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    RecyclerView udupirec;
    myadaptershiv adaptershiv;
    ImageView back;

    public udupifragment() {

    }

    public static udupifragment newInstance(String param1, String param2) {
        udupifragment fragment = new udupifragment();
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
        View view = inflater.inflate(R.layout.fragment_udupifragment, container, false);

        udupirec = (RecyclerView)view.findViewById(R.id.udupirec);
        udupirec.setLayoutManager(new LinearLayoutManager(getContext()));

        FirebaseRecyclerOptions<modelclassshiv> options =
                new FirebaseRecyclerOptions.Builder<modelclassshiv>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Udupi"), modelclassshiv.class)
                        .build();
        adaptershiv = new myadaptershiv(options);
        udupirec.setAdapter(adaptershiv);


        back = view.findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity activity =(AppCompatActivity)view.getContext();
                activity.getSupportFragmentManager().popBackStack();

            }
        });

        return view;

    }
    @Override
    public void onStart() {
        super.onStart();
        adaptershiv.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adaptershiv.stopListening();
    }
}