package com.example.ponderwonder.aws;

import android.content.Context;
import android.util.Log;

import com.amazonaws.amplify.generated.graphql.CreateJournalEntryMutation;
import com.amazonaws.amplify.generated.graphql.CreateScheduleMutation;
import com.amazonaws.amplify.generated.graphql.CreateTodoMutation;
import com.amazonaws.amplify.generated.graphql.CreateUserMutation;
import com.amazonaws.amplify.generated.graphql.DeleteJournalEntryMutation;
import com.amazonaws.amplify.generated.graphql.DeleteScheduleMutation;
import com.amazonaws.amplify.generated.graphql.DeleteTodoMutation;
import com.amazonaws.amplify.generated.graphql.DeleteUserMutation;
import com.amazonaws.amplify.generated.graphql.GetJournalEntryQuery;
import com.amazonaws.amplify.generated.graphql.GetScheduleQuery;
import com.amazonaws.amplify.generated.graphql.GetTodoQuery;
import com.amazonaws.amplify.generated.graphql.GetUserQuery;
import com.amazonaws.amplify.generated.graphql.ListJournalEntrysQuery;
import com.amazonaws.amplify.generated.graphql.ListSchedulesQuery;
import com.amazonaws.amplify.generated.graphql.ListTodosQuery;
import com.amazonaws.amplify.generated.graphql.ListUsersQuery;
import com.amazonaws.amplify.generated.graphql.OnCreateTodoSubscription;
import com.amazonaws.amplify.generated.graphql.UpdateJournalEntryMutation;
import com.amazonaws.amplify.generated.graphql.UpdateScheduleMutation;
import com.amazonaws.amplify.generated.graphql.UpdateTodoMutation;
import com.amazonaws.amplify.generated.graphql.UpdateUserMutation;
import com.amazonaws.mobile.config.AWSConfiguration;
import com.amazonaws.mobileconnectors.appsync.AWSAppSyncClient;
import com.amazonaws.mobileconnectors.appsync.AppSyncSubscriptionCall;
import com.amazonaws.mobileconnectors.appsync.fetcher.AppSyncResponseFetchers;
import com.apollographql.apollo.GraphQLCall;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;

import javax.annotation.Nonnull;

import type.CreateJournalEntryInput;
import type.CreateScheduleInput;
import type.CreateTodoInput;
import type.CreateUserInput;
import type.DeleteJournalEntryInput;
import type.DeleteScheduleInput;
import type.DeleteTodoInput;
import type.DeleteUserInput;
import type.ModelJournalEntryFilterInput;
import type.ModelScheduleFilterInput;
import type.ModelTodoFilterInput;
import type.ModelUserFilterInput;
import type.UpdateJournalEntryInput;
import type.UpdateScheduleInput;
import type.UpdateTodoInput;
import type.UpdateUserInput;

public class AWSClient {
    private static volatile AWSAppSyncClient mAWSAppSyncClient;
    private AppSyncSubscriptionCall subscriptionWatcher;

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

    //get, list: user, todo, scheudle, journla

    public void getUser(AWSAppSyncClient client, String uid) {
        client.query(GetUserQuery.builder().id(uid).build())
                .responseFetcher(AppSyncResponseFetchers.CACHE_AND_NETWORK)
                .enqueue(getUserQueryCallback);
    }

    private GraphQLCall.Callback<GetUserQuery.Data> getUserQueryCallback =
            new GraphQLCall.Callback<GetUserQuery.Data>() {
                @Override
                public void onResponse(@Nonnull Response<GetUserQuery.Data> response) {
                    Log.i("Results", response.data().getUser().userId());
                }

                @Override
                public void onFailure(@Nonnull ApolloException e) {

                }
            };

    // TODO: For list querires, need to handle filters
    public void listUsers(AWSAppSyncClient client) {
        ModelUserFilterInput.builder().build();

        client.query(ListUsersQuery.builder().build())
                .responseFetcher(AppSyncResponseFetchers.CACHE_AND_NETWORK)
                .enqueue(listUsersQueryCallback);
    }

    private GraphQLCall.Callback<ListUsersQuery.Data> listUsersQueryCallback =
            new GraphQLCall.Callback<ListUsersQuery.Data>() {
                @Override
                public void onResponse(@Nonnull Response<ListUsersQuery.Data> response) {
                    Log.i("result", response.data().listUsers().items().toString());
                }

                @Override
                public void onFailure(@Nonnull ApolloException e) {

                }
            };

    public void getSchedule(AWSAppSyncClient client, String sid) {
        client.query(GetScheduleQuery.builder().id(sid).build())
                .responseFetcher(AppSyncResponseFetchers.CACHE_AND_NETWORK)
                .enqueue(getScheduleQueryCallback);
    }

    private GraphQLCall.Callback<GetScheduleQuery.Data> getScheduleQueryCallback =
            new GraphQLCall.Callback<GetScheduleQuery.Data>() {
                @Override
                public void onResponse(@Nonnull Response<GetScheduleQuery.Data> response) {
                    Log.i("result", response.data().getSchedule().ScheduleName());
                }

                @Override
                public void onFailure(@Nonnull ApolloException e) {

                }
            };

    public void listSchedules(AWSAppSyncClient client) {
        ModelScheduleFilterInput.builder().build();

        client.query(ListSchedulesQuery.builder().build())
                .responseFetcher(AppSyncResponseFetchers.CACHE_AND_NETWORK)
                .enqueue(listScheduleQueryCallback);
    }

    private GraphQLCall.Callback<ListSchedulesQuery.Data> listScheduleQueryCallback =
            new GraphQLCall.Callback<ListSchedulesQuery.Data>() {
                @Override
                public void onResponse(@Nonnull Response<ListSchedulesQuery.Data> response) {
                    Log.i("result: ", response.data().listSchedules().items().toString());
                }

                @Override
                public void onFailure(@Nonnull ApolloException e) {

                }
            };

    public void getTodo(AWSAppSyncClient client, String tid) {
        client.query(GetTodoQuery.builder().id(tid).build())
                .responseFetcher(AppSyncResponseFetchers.CACHE_AND_NETWORK)
                .enqueue(getTodoQueryCallback);
    }

    private GraphQLCall.Callback<GetTodoQuery.Data> getTodoQueryCallback =
            new GraphQLCall.Callback<GetTodoQuery.Data>() {
                @Override
                public void onResponse(@Nonnull Response<GetTodoQuery.Data> response) {
                    Log.i("result: " , response.data().getTodo().todoId());
                }

                @Override
                public void onFailure(@Nonnull ApolloException e) {

                }
            };

    public void listTodos(AWSAppSyncClient client) {
        ModelTodoFilterInput.builder().build();

        client.query(ListTodosQuery.builder().build())
                .responseFetcher(AppSyncResponseFetchers.CACHE_AND_NETWORK)
                .enqueue(listTodoQueryCallback);
    }

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

    public void getJournal(AWSAppSyncClient client, String jid) {
        client.query(GetJournalEntryQuery.builder().id(jid).build())
                .responseFetcher(AppSyncResponseFetchers.CACHE_AND_NETWORK)
                .enqueue(getJournalQueryCallback);
    }

    private GraphQLCall.Callback<GetJournalEntryQuery.Data> getJournalQueryCallback =
            new GraphQLCall.Callback<GetJournalEntryQuery.Data>() {
                @Override
                public void onResponse(@Nonnull Response<GetJournalEntryQuery.Data> response) {
                    Log.i("Results: ", response.data().getJournalEntry().journalEntryId());
                }

                @Override
                public void onFailure(@Nonnull ApolloException e) {

                }
            };

    public void listJournals(AWSAppSyncClient client) {
        ModelJournalEntryFilterInput.builder().build();

        client.query(ListJournalEntrysQuery.builder().build())
                .responseFetcher(AppSyncResponseFetchers.CACHE_AND_NETWORK)
                .enqueue(listJournalsQueryCallback);
    }

    private GraphQLCall.Callback<ListJournalEntrysQuery.Data> listJournalsQueryCallback =
            new GraphQLCall.Callback<ListJournalEntrysQuery.Data>() {
                @Override
                public void onResponse(@Nonnull Response<ListJournalEntrysQuery.Data> response) {
                    Log.i("Results: ", response.data().listJournalEntrys().items().toString());
                }

                @Override
                public void onFailure(@Nonnull ApolloException e) {

                }
            };

    public void createUser(AWSAppSyncClient client, String uid, String userEmail) {
        CreateUserInput createUserInput = CreateUserInput.builder()
                .userId(uid).userEmail(userEmail).build();

        client.mutate(CreateUserMutation.builder().input(createUserInput).build())
                .enqueue(createUserCallback);
    }

    private GraphQLCall.Callback<CreateUserMutation.Data> createUserCallback = new GraphQLCall.Callback<CreateUserMutation.Data>() {
        @Override
        public void onResponse(@Nonnull Response<CreateUserMutation.Data> response) {
            Log.i("Results: ", "Added a user :" + response.data().createUser().userId());
        }

        @Override
        public void onFailure(@Nonnull ApolloException e) {

        }
    };

    public void updateUser(AWSAppSyncClient client, String uid, String userEmail) {
        UpdateUserInput updateUserInput = UpdateUserInput.builder()
                .userId(uid).userEmail(userEmail).build();

        client.mutate(UpdateUserMutation.builder().input(updateUserInput).build())
                .enqueue(updateUserCallback);
    }

    private GraphQLCall.Callback<UpdateUserMutation.Data> updateUserCallback =
            new GraphQLCall.Callback<UpdateUserMutation.Data>() {
                @Override
                public void onResponse(@Nonnull Response<UpdateUserMutation.Data> response) {
                    Log.i("Success", response.data().updateUser().userId());
                }

                @Override
                public void onFailure(@Nonnull ApolloException e) {

                }
            };

    public void deleteUser(AWSAppSyncClient client, String uid) {
        DeleteUserInput deleteUserInput = DeleteUserInput.builder()
                .id(uid).build();

        client.mutate(DeleteUserMutation.builder().input(deleteUserInput).build())
                .enqueue(deleteUserCallback);
    }

    private GraphQLCall.Callback<DeleteUserMutation.Data> deleteUserCallback =
            new GraphQLCall.Callback<DeleteUserMutation.Data>() {
                @Override
                public void onResponse(@Nonnull Response<DeleteUserMutation.Data> response) {
                    Log.i("Delete Successful ", response.data().deleteUser().userId());
                }

                @Override
                public void onFailure(@Nonnull ApolloException e) {

                }
            };

    public void createSchedule(AWSAppSyncClient client, String sid, String sName) {
        CreateScheduleInput createScheduleInput = CreateScheduleInput.builder()
                .scheduleId(sid).scheduleName(sName).build();

        client.mutate(CreateScheduleMutation.builder().input(createScheduleInput).build())
                .enqueue(createScheduleCallback);
    }

    private GraphQLCall.Callback<CreateScheduleMutation.Data> createScheduleCallback =
            new GraphQLCall.Callback<CreateScheduleMutation.Data>() {
                @Override
                public void onResponse(@Nonnull Response<CreateScheduleMutation.Data> response) {
                    Log.i("Success: ", response.data().createSchedule().scheduleId());
                }

                @Override
                public void onFailure(@Nonnull ApolloException e) {

                }
            };

    public void updateSchedule(AWSAppSyncClient client, String sid, String sName) {
        UpdateScheduleInput updateScheduleInput = UpdateScheduleInput.builder()
                .scheduleId(sid).scheduleName(sName).build();

        client.mutate(UpdateScheduleMutation.builder().input(updateScheduleInput).build())
                .enqueue(updateScheduleCallback);
    }

    private GraphQLCall.Callback<UpdateScheduleMutation.Data> updateScheduleCallback =
            new GraphQLCall.Callback<UpdateScheduleMutation.Data>() {
                @Override
                public void onResponse(@Nonnull Response<UpdateScheduleMutation.Data> response) {
                    Log.i("Success: ", response.data().updateSchedule().scheduleId());
                }

                @Override
                public void onFailure(@Nonnull ApolloException e) {

                }
            };

    public void deleteSchedule(AWSAppSyncClient client, String sid) {
        DeleteScheduleInput deleteScheduleInput = DeleteScheduleInput.builder()
                .id(sid).build();

        client.mutate(DeleteScheduleMutation.builder().input(deleteScheduleInput).build())
                .enqueue(deleteScheduleCallback);
    }

    private GraphQLCall.Callback<DeleteScheduleMutation.Data> deleteScheduleCallback =
            new GraphQLCall.Callback<DeleteScheduleMutation.Data>() {
                @Override
                public void onResponse(@Nonnull Response<DeleteScheduleMutation.Data> response) {
                    Log.i("Success: ", response.data().deleteSchedule().scheduleId());
                }

                @Override
                public void onFailure(@Nonnull ApolloException e) {

                }
            };

    public void createTodo(AWSAppSyncClient client, String tid, String tDate) {
        CreateTodoInput createTodoInput = CreateTodoInput.builder()
                .todoId(tid).todoDate(tDate).build();

        client.mutate(CreateTodoMutation.builder().input(createTodoInput).build())
                .enqueue(createTodoCallback);
    }

    private GraphQLCall.Callback<CreateTodoMutation.Data> createTodoCallback =
            new GraphQLCall.Callback<CreateTodoMutation.Data>() {
                @Override
                public void onResponse(@Nonnull Response<CreateTodoMutation.Data> response) {
                    Log.i("Success: ", response.data().createTodo().todoId());
                }

                @Override
                public void onFailure(@Nonnull ApolloException e) {

                }
            };

    public void updateTodo(AWSAppSyncClient client, String tid, String tDate) {
        UpdateTodoInput updateTodoInput = UpdateTodoInput.builder()
                .todoId(tid).todoDate(tDate).build();

        client.mutate(UpdateTodoMutation.builder().input(updateTodoInput).build())
                .enqueue(updateTodoCallback);
    }

    private GraphQLCall.Callback<UpdateTodoMutation.Data> updateTodoCallback =
            new GraphQLCall.Callback<UpdateTodoMutation.Data>() {
                @Override
                public void onResponse(@Nonnull Response<UpdateTodoMutation.Data> response) {
                    Log.i("success: ", response.data().updateTodo().todoId());
                }

                @Override
                public void onFailure(@Nonnull ApolloException e) {

                }
            };

    public void deleteTodo(AWSAppSyncClient client, String tid) {
        DeleteTodoInput deleteTodoInput = DeleteTodoInput.builder()
                .id(tid).build();

        client.mutate(DeleteTodoMutation.builder().input(deleteTodoInput).build())
                .enqueue(deleteTodoCallback);
    }

    private GraphQLCall.Callback<DeleteTodoMutation.Data> deleteTodoCallback =
            new GraphQLCall.Callback<DeleteTodoMutation.Data>() {
                @Override
                public void onResponse(@Nonnull Response<DeleteTodoMutation.Data> response) {
                    Log.i("Success: ", response.data().deleteTodo().todoId());
                }

                @Override
                public void onFailure(@Nonnull ApolloException e) {

                }
            };

    public void createJournalEntry(AWSAppSyncClient client, String jid, String jDate) {
        CreateJournalEntryInput createJournalEntryInput = CreateJournalEntryInput.builder()
                .journalEntryId(jid).journalDate(jDate).build();

        client.mutate(CreateJournalEntryMutation.builder().input(createJournalEntryInput).build())
                .enqueue(createJournalEntryCallback);
    }

    private GraphQLCall.Callback<CreateJournalEntryMutation.Data> createJournalEntryCallback =
            new GraphQLCall.Callback<CreateJournalEntryMutation.Data>() {
                @Override
                public void onResponse(@Nonnull Response<CreateJournalEntryMutation.Data> response) {
                    Log.i("success: ", response.data().createJournalEntry().journalEntryId());
                }

                @Override
                public void onFailure(@Nonnull ApolloException e) {

                }
            };

    public void updateJournalEntry(AWSAppSyncClient client, String jid, String jDate) {
        UpdateJournalEntryInput updateJournalEntryInput = UpdateJournalEntryInput.builder()
                .journalEntryId(jid).journalDate(jDate).build();

        client.mutate(UpdateJournalEntryMutation.builder().input(updateJournalEntryInput).build())
                .enqueue(updateJournalEntryCallback);
    }

    private GraphQLCall.Callback<UpdateJournalEntryMutation.Data> updateJournalEntryCallback =
            new GraphQLCall.Callback<UpdateJournalEntryMutation.Data>() {
                @Override
                public void onResponse(@Nonnull Response<UpdateJournalEntryMutation.Data> response) {
                    Log.i("Success: ", response.data().updateJournalEntry().journalEntryId());
                }

                @Override
                public void onFailure(@Nonnull ApolloException e) {

                }
            };

    public void deleteJournalEntry(AWSAppSyncClient client, String jid) {
        DeleteJournalEntryInput deleteJournalEntryInput = DeleteJournalEntryInput.builder()
                .id(jid).build();

        client.mutate(DeleteJournalEntryMutation.builder().input(deleteJournalEntryInput).build())
                .enqueue(deleteJournalEntryCallback);
    }

    private GraphQLCall.Callback<DeleteJournalEntryMutation.Data> deleteJournalEntryCallback =
            new GraphQLCall.Callback<DeleteJournalEntryMutation.Data>() {
                @Override
                public void onResponse(@Nonnull Response<DeleteJournalEntryMutation.Data> response) {
                    Log.i("Success: ", response.data().deleteJournalEntry().journalEntryId());
                }

                @Override
                public void onFailure(@Nonnull ApolloException e) {

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
