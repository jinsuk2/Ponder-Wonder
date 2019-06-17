package com.example.ponderwonder.journal;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ponderwonder.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;


/**
 * Adapter Class to view elements for list.
 */
public class JournalAdapter extends RecyclerView.Adapter<JournalAdapter.JournalViewHolder> {

    private Context mContext;
    private List<Journal> journalList;

    public JournalAdapter(Context context, List<Journal> jList){
        mContext = context;
        journalList = jList;
    }

    @Override
    public JournalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_journal_view, parent, false);
        JournalViewHolder journalViewHolder = new JournalViewHolder(mView);

        return journalViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull JournalViewHolder holder, int position) {
        Journal journal = journalList.get(position);
//        holder.journalImg.setImageResource();
        holder.journalTitle.setText(journal.getJournalTitle());
        holder.journalContent.setText(journal.getJournalContent());

    }

    @Override
    public int getItemCount() {
        return journalList.size();
    }

    /**
     * Journal View Holder that describes journal view.
     */
    static class JournalViewHolder extends RecyclerView.ViewHolder{

        public ImageView journalImg;
        public TextView journalTitle, journalContent;

        public JournalViewHolder(@NonNull View itemView) {
            super(itemView);

            journalImg = itemView.findViewById(R.id.journal_img);
            journalTitle = itemView.findViewById(R.id.journal_title);
            journalContent = itemView.findViewById(R.id.journal_content);
        }
    }
}


