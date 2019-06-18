package com.example.ponderwonder.aws;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;

import com.amazonaws.mobile.client.AWSMobileClient;
import com.amazonaws.mobile.config.AWSConfiguration;
import com.amazonaws.mobileconnectors.appsync.AWSAppSyncClient;
import com.example.ponderwonder.MainActivity;

public class AWSClient {
    private static volatile AWSAppSyncClient client;

    // Call this function to get the current client instance.
    // Call at onCreateView
    // Ex. AWSAppSyncClient awsAppSyncClient = AWSClient.getInstance(this.getApplicationContext());

    public synchronized static AWSAppSyncClient getInstance(Context context) {
        if (client == null) {
            AWSConfiguration awsConfig = new AWSConfiguration(context);

            client = AWSAppSyncClient.builder()
                    .context(context)
                    .awsConfiguration(awsConfig)
                    .build();
        }
        return client;
    }
}
