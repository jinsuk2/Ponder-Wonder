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

public class Today extends Fragment {

    public Today() {
        // Required empty public constructor
    }

    public static Today newInstance() {
        Today fragment = new Today();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        toSignIn();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_today, container, false);
    }

    private void toSignIn() {
        NavController navController = Navigation.findNavController(super.getActivity(), R.id.mainNavigationFragment);
//        navController.navigate(R.id.signIn);
    }
}
