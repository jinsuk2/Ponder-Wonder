package com.example.ponderwonder;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class Schedules extends Fragment {

    public Schedules() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment Schedules.
     */
    // TODO: Rename and change types and number of parameters
    public static Schedules newInstance() {
        Schedules fragment = new Schedules();
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
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_schedules, container, false);

        // Set adapter
        Context context = view.getContext();
//        RecyclerView recyclerView = view.findViewById(R.id.schedule_list);
//        ScheduleListAdapter adapter;
//        recyclerView.setLayoutManager(new LinearLayoutManager(context));
//        adapter = new ScheduleListAdapter(recyclerView, context);
//
//        recyclerView.setAdapter(adapter);

        return view;
    }
}
