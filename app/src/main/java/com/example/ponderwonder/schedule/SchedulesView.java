package com.example.ponderwonder.schedule;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ponderwonder.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;


public class SchedulesView extends Fragment {

    RecyclerView mRecyclerView;
    List<Schedule> mScheduleList;
    Schedule mSchedule;



    public SchedulesView() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment SchedulesView.
     */
    // TODO: Rename and change types and number of parameters
    public static SchedulesView newInstance() {
        SchedulesView fragment = new SchedulesView();
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
        RecyclerView mRecyclerView = view.findViewById(R.id.schedule_recyclerView);
        TextView noScheduleText = view.findViewById(R.id.no_schedule_msg);
        GridLayoutManager mGridLayoutManager = new GridLayoutManager(getActivity(), 2);
        mRecyclerView.setLayoutManager(mGridLayoutManager);

        final FloatingActionButton createButton = view.findViewById(R.id.create_schedule_btn);
        final NavController navController = Navigation.findNavController(super.getActivity(),R.id.mainNavigationFragment);

        // Navigates to create journal fragment when clicked
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.create_schedule);
            }
        });


        mScheduleList = new ArrayList<>();

        // obtain list from db
        // TODO: currently using dummy cards
        for (int i = 0; i< 20; i++) {
            mSchedule = new Schedule("test", "testDesc");
            mScheduleList.add(mSchedule);
        }

        if (!mScheduleList.isEmpty()) {
            noScheduleText.setVisibility(View.INVISIBLE);
        }

        ScheduleAdapter myAdapter = new ScheduleAdapter(context, mScheduleList);
        mRecyclerView.setAdapter(myAdapter);

        return view;
    }
}
