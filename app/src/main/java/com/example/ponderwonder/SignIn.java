package com.example.ponderwonder;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

import javax.annotation.Nullable;

public class SignIn extends Fragment {

    private static final int RC_SIGN_IN = 9001;
    private GoogleSignInClient mGoogleSignInClient;
    private GoogleSignInOptions mGoogleSignInOptions;

    public SignIn() {
        // Required empty public constructor
    }

    public static SignIn newInstance() {
        SignIn fragment = new SignIn();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Variables Required
        final View signInView = inflater.inflate(R.layout.fragment_sign_in, container, false);
        final SignInButton button = signInView.findViewById(R.id.googleSignInButton);
        final NavController navController = Navigation.findNavController(super.getActivity(), R.id.mainNavigationFragment);
        final TextView userName = signInView.findViewById(R.id.user_name);

        button.setSize(SignInButton.SIZE_STANDARD);

        // Sign In Option
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        // Init SignInClient
        mGoogleSignInClient = GoogleSignIn.getClient(super.getActivity(), gso);


        // Click button to SignIn with Google
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(mGoogleSignInClient.getSignInIntent(), RC_SIGN_IN);
//                navController.navigate();
            }
        });

        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(super.getActivity());
        if ( account != null) {
            userName.setText("Welcome Back! " + account.getEmail());
        };

        return signInView;
    }

    @Override
    public void onStart() {
        super.onStart();
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this.getContext());
        updateUI(account);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case RC_SIGN_IN:
                handleSignInResult(GoogleSignIn.getSignedInAccountFromIntent(data));
        }
    }

    // Handles whether SignInInfo is valid or not
    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            updateUI(completedTask.getResult(ApiException.class));
        } catch (ApiException e) {
            error(this.getClass().getName(), "signInResult::failed code= " + e.getStatusCode());
            updateUI(null);
        }
    }

    // Loads Home Screen when SignedIn
    private void updateUI(GoogleSignInAccount account) {
        if (account != null) {
            // Add navigation to Home Screen after login
            Toast.makeText(super.getActivity(), "Test", Toast.LENGTH_SHORT).show();
        }
    }

    // Notifies Error as Toast
    private void error(String name, String msg) {
        Log.w(name, msg);
        Toast.makeText(super.getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

}
