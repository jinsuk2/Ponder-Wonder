package com.example.ponderwonder.journal;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.ponderwonder.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class JournalView extends Fragment {

    public JournalView() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment Journal.
     */
    // TODO: Rename and change types and number of parameters
    public static JournalView newInstance() {
        JournalView fragment = new JournalView();
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
        View view = inflater.inflate(R.layout.fragment_journal, container, false);

        // Adapter setup
        Context context = view.getContext();
        RecyclerView rvList = view.findViewById(R.id.journal_rv);
        rvList.setLayoutManager(new LinearLayoutManager(context, RecyclerView.VERTICAL, false));

        // Journal Create button display
        final FloatingActionButton createButton = view.findViewById(R.id.create_journal_button);
        final NavController navController = Navigation.findNavController(super.getActivity(),R.id.mainNavigationFragment);

        // Navigates to create journal fragment when clicked
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.createJournal);
            }
        });

        // Instantiate list for journals
        ArrayList<Journal> journalList = new ArrayList<>();

        // dummy data used to display
        // TODO: Grab list from db

        for (int i = 0; i < 10; i++) {
            Journal myJournal = new Journal(
                    "https://cdn.vox-cdn.com/thumbor/XF4LxCPjJf-f1wWVo2pZUPoxOQs=/0x0:6016x4016/920x613/filters:focal(2527x1527:3489x2489):format(webp)/cdn.vox-cdn.com/uploads/chorus_image/image/57265735/shutterstock_321760214.0.jpg",
                    "This is a title!",
                    "This is a content box! This is a content box! This is a content box!");
            journalList.add(i, myJournal);
        }

        // Instantiating adapter for journal
        JournalAdapter myAdapter = new JournalAdapter(context, journalList);

        // Setting adapter
        rvList.setAdapter(myAdapter);

        return view;
    }
}
