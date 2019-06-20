package com.example.ponderwonder;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
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

import com.amazonaws.mobile.client.AWSMobileClient;
import com.amazonaws.mobile.client.Callback;
import com.amazonaws.mobile.client.IdentityProvider;
import com.amazonaws.mobile.client.UserStateDetails;
import com.amazonaws.mobile.config.AWSConfiguration;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoUserPool;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

import javax.annotation.Nullable;

import static com.amazonaws.mobile.client.internal.oauth2.OAuth2Client.TAG;

public class SignIn extends Fragment {

    private static final int RC_SIGN_IN = 9001;
    private GoogleSignInClient mGoogleSignInClient;
    private GoogleSignInOptions mGoogleSignInOptions;
    private GoogleSignInAccount mGoogleSignInAccount;
    private AWSConfiguration mAWSConfiguration;
    private AWSMobileClient mAWSMobileClient;


    private View mSignInView;

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

        // TODO: Current Error is because AWSMoblieClient's error is regarded as duplicate coz of onError at the bottom for googlesignin
        // Cognito Pool Register

//        AWSMobileClient awsMobileClient = AWSMobileClient.getInstance();
//        awsMobileClient.federatedSignIn(IdentityProvider.GOOGLE.toString(), "GOOGLE_TOKEN_HERE", new Callback<UserStateDetails>() {
//            @Override
//            public void onResult(final UserStateDetails result) {
////                Log.i(TAG, "Signed In!" + result.getUserState().name());
//            }
//
//            @Override
//            public void onError(Exception e) {
//                Log.e(TAG, "sign-in error", e);
////                Toast.makeText(SignIn.super.getContext(), "Error!", Toast.LENGTH_SHORT).show();
//            }
//        });

//        mAWSMobileClient = AWSMobileClient.getInstance();
//        mAWSMobileClient.federatedSignIn(IdentityProvider.GOOGLE.toString(), "GOOGLE_TOKEN_HERE", new Callback<UserStateDetails>() {
//            @Override
//            public void onResult(final UserStateDetails result) {
//                Log.i(TAG, "Signed In!" + result.getUserState().name());
//            }
//
//            @Override
//            public void onError(Exception e) {
//                Log.e(TAG, "sign-in error", e);
//            }
//        });

        // Variables Required
        mSignInView = inflater.inflate(R.layout.fragment_sign_in, container, false);
        final SignInButton signInButton = mSignInView.findViewById(R.id.googleSignInButton);
        final Button signOutButton = mSignInView.findViewById(R.id.sign_out_button);
        final NavController navController = Navigation.findNavController(super.getActivity(), R.id.mainNavigationFragment);
        final TextView userName = mSignInView.findViewById(R.id.user_name);

//        userName.setText(GoogleSignIn.getSignedInAccountFromIntent(mGoogleSignInClient.getSignInIntent()));
        signInButton.setSize(SignInButton.SIZE_STANDARD);

        // Sign In Option
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        // Init SignInClient
        mGoogleSignInClient = GoogleSignIn.getClient(super.getActivity(), gso);

        mGoogleSignInAccount = GoogleSignIn.getLastSignedInAccount(super.getActivity());

        // Click button to SignIn with Google
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAWSMobileClient.federatedSignIn(IdentityProvider.GOOGLE.toString(), "GOOGLE_TOKEN_HERE", new Callback<UserStateDetails>() {
                    @Override
                    public void onResult(final UserStateDetails result) {
                        Log.i(TAG, "Signed In!" + result.getUserState().name());
                    }

                    @Override
                    public void onError(Exception e) {
                        Log.e(TAG, "sign-in error", e);
                    }
                });

                startActivityForResult(mGoogleSignInClient.getSignInIntent(), RC_SIGN_IN);
                if ( mGoogleSignInAccount != null) {
                    userName.setText("Welcome Back! " + mGoogleSignInAccount.getEmail());
                };
            }
        });

        // SignOut Button
        signOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mGoogleSignInClient.signOut()
                        .addOnCompleteListener(SignIn.super.getActivity(), new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                userName.setText("No Current User");
                            }
                        });
            }
        });

        return mSignInView;
    }

    @Override
    public void onStart() {
        super.onStart();
        updateUI(mGoogleSignInAccount);
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

    private String loadJSONFromAsset(Context context, int resourceId) {
        String json = null;
        try {
            InputStream is = context.getResources().openRawResource(resourceId);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            return new String(buffer, "UTF-8");
        } catch (IOException e) {
            Log.e(TAG, "Error loading AWSConfig File : "  + e);
            return null;
        }
    }

}
