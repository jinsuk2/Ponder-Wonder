package com.example.ponderwonder.schedule;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ponderwonder.R;

import java.util.List;

public class ScheduleAdapter  extends RecyclerView.Adapter< ScheduleViewHolder > {

    private Context mContext;
    private List< Schedule > mScheduleList;

    ScheduleAdapter(Context mContext, List< Schedule > mScheduleList) {
        this.mContext = mContext;
        this.mScheduleList = mScheduleList;
    }

    @Override
    public ScheduleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.schedule_card_layout, parent, false);
        return new ScheduleViewHolder(mView);

    }

    @Override
    public void onBindViewHolder(ScheduleViewHolder holder, int position) {
//        holder.mImage.setImageResource(mFlowerList.get(position).getFlowerImage());
        holder.mTitle.setText(mScheduleList.get(position).getScheduleTitle());

        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: when clicked, should navigate to Today screen of the selected scheudle's start date
                Toast.makeText(mContext, "Test", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return mScheduleList.size();
    }
}

class ScheduleViewHolder extends RecyclerView.ViewHolder {

    CardView mCardView;
    TextView mTitle;
    TextView mStartDate;
    TextView mEndDate;

    ScheduleViewHolder(View itemView) {
        super(itemView);
        mTitle = itemView.findViewById(R.id.schedule_card_title);
        mCardView = itemView.findViewById(R.id.schedule_card);
    }
}