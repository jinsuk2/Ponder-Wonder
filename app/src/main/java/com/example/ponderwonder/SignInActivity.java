package com.example.ponderwonder;

import androidx.appcompat.app.AppCompatActivity;

//import android.app.Activity;
//import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
//import android.widget.Toast;

import com.amazonaws.mobile.client.AWSMobileClient;
import com.amazonaws.mobile.client.Callback;
//import com.amazonaws.mobile.client.IdentityProvider;
//import com.amazonaws.mobile.client.SignInUIOptions;
import com.amazonaws.mobile.client.UserStateDetails;
import com.amazonaws.mobile.config.AWSConfiguration;
//import com.google.android.gms.auth.api.signin.GoogleSignIn;
//import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
//import com.google.android.gms.auth.api.signin.GoogleSignInClient;
//import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
//import com.google.android.gms.common.SignInButton;
//import com.google.android.gms.common.api.ApiException;
//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.Task;

//import java.util.zip.Inflater;

//import javax.annotation.Nullable;

import static com.amazonaws.mobile.client.internal.oauth2.OAuth2Client.TAG;

public class SignInActivity extends AppCompatActivity {

    private static final int RC_SIGN_IN = 9001;
//    private GoogleSignInClient mGoogleSignInClient;
//    private GoogleSignInOptions mGoogleSignInOptions;
//    private GoogleSignInAccount mGoogleSignInAccount;
    private AWSConfiguration mAWSConfiguration;
    private AWSMobileClient mAWSMobileClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        setupAWS();
        setupUI();
    }

    private void setupAWS() {
        AWSConfiguration awsConfig = new AWSConfiguration(getApplicationContext());

        AWSMobileClient.getInstance().initialize(getApplicationContext(), awsConfig, new Callback<UserStateDetails>() {
            @Override
            public void onResult(UserStateDetails result) {
                //do things
                switch(result.getUserState()) {
                    case SIGNED_IN:
                   //     Toast.makeText(SignInActivity.super.getApplicationContext(), "wow", Toast.LENGTH_SHORT).show();
                        break;
                    case SIGNED_OUT:
                     //   Toast.makeText(SignInActivity.super.getApplicationContext(), "wow???", Toast.LENGTH_SHORT).show();
                        showSignIn();
                        break;
//                    case SIGNED_OUT_USER_POOLS_TOKENS_INVALID:
//                        break;
//                    case SIGNED_OUT_FEDERATED_TOKENS_INVALID:
//                        break;
                    case GUEST:
                        break;
                    default:
                        break;
                }
                Log.i("INIT", "onResult: " + result.getUserState());
            }

            @Override
            public void onError(Exception e) {
                Log.e(TAG, "onError: " + e);
            }
        });
    }

    private void setupUI() {

//        final SignInButton signInButton = findViewById(R.id.googleSignInButton);
        final Button signUpButton = findViewById(R.id.sign_up_button);
        final TextView userName = findViewById(R.id.user_name);

//        signInButton.setSize(SignInButton.SIZE_STANDARD);
//        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//                .requestEmail()
//                .build();
//
//        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
//        mGoogleSignInAccount = GoogleSignIn.getLastSignedInAccount(this);
//
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                SignInActivity.this.showSignIn();
////                mAWSMobileClient.federatedSignIn(IdentityProvider.GOOGLE.toString(), "GOOGLE_TOKEN_HERE", new Callback<UserStateDetails>() {
////                    @Override
////                    public void onResult(final UserStateDetails result) {
////                        Log.i(TAG, "Signed In!" + result.getUserState().name());
////                    }
////
////                    @Override
////                    public void onError(Exception e) {
////                        Log.e(TAG, "sign-in error", e);
////                    }
////                });
//                startActivityForResult(mGoogleSignInClient.getSignInIntent(), RC_SIGN_IN);

                // TODO: Move this part to MainActivity
                //                    userName.setText("Welcome Back! " + mGoogleSignInAccount.getEmail());

//                if ( mGoogleSignInAccount != null) {
//                    Toast.makeText(SignInActivity.super.getApplicationContext(), "Logged in! " + mGoogleSignInAccount.getEmail(), Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent(SignInActivity.this, MainActivity.class);
//                    startActivity(intent);
//                } else {
//                    Toast.makeText(SignInActivity.super.getApplicationContext(), "You need to log in first", Toast.LENGTH_SHORT).show();
//                }
            }
        });

        // TODO: Move to Main Account Screen
//        signOutButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (mGoogleSignInAccount != null) {
//                    mGoogleSignInClient.signOut()
//                            .addOnCompleteListener(SignInActivity.super.getParent(), new OnCompleteListener<Void>() {
//                                @Override
//                                public void onComplete(@NonNull Task<Void> task) {
//
//                                }
//                            });
//                } else {
//                    Toast.makeText(SignInActivity.super.getApplicationContext(), "No account Signed In", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });

    }
//    @Override
//    public void onStart() {
//        super.onStart();
//        //updateUI(mGoogleSignInAccount);
//    }
//
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
////        switch (requestCode) {
////            case RC_SIGN_IN:
////                handleSignInResult(GoogleSignIn.getSignedInAccountFromIntent(data));
////        }
//    }

//    // Handles whether SignInInfo is valid or not
//    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
//        try {
//            updateUI(completedTask.getResult(ApiException.class));
//        } catch (ApiException e) {
//            error(this.getClass().getName(), "signInResult::failed code= " + e.getStatusCode());
//            updateUI(null);
//        }
//    }
//
//    // Loads Home Screen when SignedIn
//    private void updateUI(GoogleSignInAccount account) {
//        if (account != null) {
//            // Add navigation to Home Screen after login
//            Toast.makeText(this, "Test", Toast.LENGTH_SHORT).show();
//        }
//    }
//
//    // Notifies Error as Toast
//    private void error(String name, String msg) {
//        Log.w(name, msg);
//        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
//    }

    private void showSignIn() {
        if(!this.isFinishing()) {
            AWSMobileClient.getInstance().showSignIn(SignInActivity.this, new Callback<UserStateDetails>() {
                @Override
                public void onResult(UserStateDetails result) {
                    //Toast.makeText(SignInActivity.super.getApplicationContext(), "What?", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "onResult: " + result.getUserState());
                }

                @Override
                public void onError(Exception e) {
                    Log.e(TAG, "onError: " + e);
                }
            });
        }
    }
}