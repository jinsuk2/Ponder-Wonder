package com.example.ponderwonder.journal;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Adapter Class to view elements for list.
 */
public class JournalAdapter extends RecyclerView.Adapter<JournalViewHolder> {
    private ArrayList<Journal> journalList;


    public JournalAdapter(ArrayList<Journal> journalList){

        this.journalList = journalList;
    }

    @Override
    public JournalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(JournalViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}

class JournalViewHolder extends RecyclerView.ViewHolder {

}
