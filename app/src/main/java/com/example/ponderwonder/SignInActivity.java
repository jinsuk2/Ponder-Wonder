package com.example.ponderwonder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.amazonaws.mobile.client.AWSMobileClient;
import com.amazonaws.mobile.client.Callback;
import com.amazonaws.mobile.client.IdentityProvider;
import com.amazonaws.mobile.client.UserStateDetails;
import com.amazonaws.mobile.config.AWSConfiguration;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import static com.amazonaws.mobile.client.internal.oauth2.OAuth2Client.TAG;

public class SignInActivity extends AppCompatActivity {

    private static final int RC_SIGN_IN = 9001;
    private GoogleSignInClient mGoogleSignInClient;
    private GoogleSignInOptions mGoogleSignInOptions;
    private GoogleSignInAccount mGoogleSignInAccount;
    private AWSConfiguration mAWSConfiguration;
    private AWSMobileClient mAWSMobileClient;

    private View mSignInView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

    }

    private View signIn() {
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
//
//        // Variables Required
//        mSignInView = inflater.inflate(R.layout.fragment_sign_in, container, false);
//        final SignInButton signInButton = mSignInView.findViewById(R.id.googleSignInButton);
//        final Button signOutButton = mSignInView.findViewById(R.id.sign_out_button);
//        final NavController navController = Navigation.findNavController(super.getActivity(), R.id.mainNavigationFragment);
//        final TextView userName = mSignInView.findViewById(R.id.user_name);
//
////        userName.setText(GoogleSignIn.getSignedInAccountFromIntent(mGoogleSignInClient.getSignInIntent()));
//        signInButton.setSize(SignInButton.SIZE_STANDARD);
//
//        // Sign In Option
//        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//                .requestEmail()
//                .build();
//
//        // Init SignInClient
//        mGoogleSignInClient = GoogleSignIn.getClient(super.getActivity(), gso);
//
//        mGoogleSignInAccount = GoogleSignIn.getLastSignedInAccount(super.getActivity());
//
//        // Click button to SignIn with Google
//        signInButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mAWSMobileClient.federatedSignIn(IdentityProvider.GOOGLE.toString(), "GOOGLE_TOKEN_HERE", new Callback<UserStateDetails>() {
//                    @Override
//                    public void onResult(final UserStateDetails result) {
//                        Log.i(TAG, "Signed In!" + result.getUserState().name());
//                    }
//
//                    @Override
//                    public void onError(Exception e) {
//                        Log.e(TAG, "sign-in error", e);
//                    }
//                });
//
//                startActivityForResult(mGoogleSignInClient.getSignInIntent(), RC_SIGN_IN);
//                if ( mGoogleSignInAccount != null) {
//                    userName.setText("Welcome Back! " + mGoogleSignInAccount.getEmail());
//                };
//            }
//        });
//
//        // SignOut Button
//        signOutButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mGoogleSignInClient.signOut()
//                        .addOnCompleteListener(SignIn.super.getActivity(), new OnCompleteListener<Void>() {
//                            @Override
//                            public void onComplete(@NonNull Task<Void> task) {
//                                userName.setText("No Current User");
//                            }
//                        });
//            }
//        });
//
//        return mSignInView;
        return null;
    }
}
