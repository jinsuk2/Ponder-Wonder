package com.example.ponderwonder.journal;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.ponderwonder.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class JournalListView extends Fragment {

    public JournalListView() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment Journal.
     */
    // TODO: Rename and change types and number of parameters
    public static JournalListView newInstance() {
        JournalListView fragment = new JournalListView();
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
        final FloatingActionButton createButton = view.findViewById(R.id.create_journal_button);
        final NavController navController = Navigation.findNavController(super.getActivity(),R.id.mainNavigationFragment);

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.createJournal);
            }
        });

        return view;
    }
}
