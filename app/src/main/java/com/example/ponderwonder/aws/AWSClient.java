package com.example.ponderwonder.aws;

import android.content.Context;
import android.util.Log;

import com.amazonaws.amplify.generated.graphql.CreateTodoMutation;
import com.amazonaws.amplify.generated.graphql.ListTodosQuery;
import com.amazonaws.amplify.generated.graphql.OnCreateTodoSubscription;
import com.amazonaws.mobile.config.AWSConfiguration;
import com.amazonaws.mobileconnectors.appsync.AWSAppSyncClient;
import com.amazonaws.mobileconnectors.appsync.AppSyncSubscriptionCall;
import com.amazonaws.mobileconnectors.appsync.fetcher.AppSyncResponseFetchers;
import com.apollographql.apollo.GraphQLCall;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;

import javax.annotation.Nonnull;

import type.CreateTodoInput;

public class AWSClient {
    private static volatile AWSAppSyncClient mAWSAppSyncClient;
    private AppSyncSubscriptionCall subscriptionWatcher;

    // Call this function to get the current mAWSAppSyncClient instance.
    // Call at onCreateView
    // Ex. AWSAppSyncClient awsAppSyncClient = AWSClient.getInstance(this.getApplicationContext());

    public synchronized static AWSAppSyncClient getInstance(Context context) {

        if (mAWSAppSyncClient == null) {
            AWSConfiguration awsConfig = new AWSConfiguration(context);

            mAWSAppSyncClient = AWSAppSyncClient.builder()
                    .context(context)
                    .awsConfiguration(awsConfig)
                    .build();
        }

        return mAWSAppSyncClient;
    }


    // Sample Query
    // Dont delete this
    public void query(AWSAppSyncClient client) {
        // Insert the wanted query from generated schema.
        // Format is NAMEQuery.builder().build()
        client.query(ListTodosQuery.builder().build())
                .responseFetcher(AppSyncResponseFetchers.CACHE_AND_NETWORK)

                // Put the Callback function here
                .enqueue(listTodoQueryCallback);
    }

    // Sample Callback
    // Dont delete this
    private GraphQLCall.Callback<ListTodosQuery.Data> listTodoQueryCallback =
            new GraphQLCall.Callback<ListTodosQuery.Data>() {
                @Override
                public void onResponse(@Nonnull Response<ListTodosQuery.Data> response) {
                    Log.i("Results", response.data().listTodos().items().toString());
                }

                @Override
                public void onFailure(@Nonnull ApolloException e) {
                    Log.e("ERROR", e.toString());
                }
            };

    // Sample Mutation
    // Dont delete this

    public void mutation(AWSAppSyncClient client){

        // Create an Input you would like mutate.
        // You can find the list of mutations in ./app/src/main/graphql/..../
        CreateTodoInput createTodoInput = CreateTodoInput.builder().
                // Parameters
                todoDate("Sample").
//                todoDescription("Testing description").
//                todoIsComplete(false).
                build();

        // Perform mutation by building mutation with input and enqueue callback
        client.mutate(CreateTodoMutation.builder().input(createTodoInput).build())
                .enqueue(mutationCallback);
    }

    // Sample Mutation Callback
    // Don't delete this
    private GraphQLCall.Callback<CreateTodoMutation.Data> mutationCallback = new GraphQLCall.Callback<CreateTodoMutation.Data>() {
        @Override
        public void onResponse(@Nonnull Response<CreateTodoMutation.Data> response) {
            Log.i("Results", "Added Todo");
        }

        @Override
        public void onFailure(@Nonnull ApolloException e) {
            Log.e("Error", e.toString());
        }
    };

    // Sample Subscription
    // Don't delete this

    private void subscribe(AWSAppSyncClient client){

        // Create Subscription. You can find the list of subscriptions in same folder
        OnCreateTodoSubscription subscription = OnCreateTodoSubscription.builder().build();
        subscriptionWatcher = client.subscribe(subscription);
        subscriptionWatcher.execute(subCallback);
    }

    // Sample Subscription Callback
    // Don't delete this
    private AppSyncSubscriptionCall.Callback subCallback = new AppSyncSubscriptionCall.Callback() {
        @Override
        public void onResponse(@Nonnull Response response) {
            Log.i("Response", response.data().toString());
        }

        @Override
        public void onFailure(@Nonnull ApolloException e) {
            Log.e("Error", e.toString());
        }

        @Override
        public void onCompleted() {
            Log.i("Completed", "Subscription completed");
        }
    };
}
