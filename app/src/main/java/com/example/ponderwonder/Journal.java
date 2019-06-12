package com.example.ponderwonder;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class Journal extends Fragment {

    public Journal() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment Journal.
     */
    // TODO: Rename and change types and number of parameters
    public static Journal newInstance() {
        Journal fragment = new Journal();
        Bundle args = new Bundle();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_journal, container, false);
        final FloatingActionButton createButton = view.findViewById(R.id.create_journal_button);
        final NavController navController = Navigation.findNavController(super.getActivity(),R.id.mainNavigationFragment);

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.createJournal);
            }
        });

        // Inflate the layout for this fragment
        return view;
    }

}
