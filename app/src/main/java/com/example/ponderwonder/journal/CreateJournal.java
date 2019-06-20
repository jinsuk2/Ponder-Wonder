package com.example.ponderwonder.journal;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import com.example.ponderwonder.R;

import java.util.Calendar;


public class CreateJournal extends Fragment {

    DatePickerDialog journalDatePicker;
    ImageButton dateButton;
    TextView tempJournalDate;
    private EditText mTitleEditText;
    private EditText mContentEditText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_journal, container, false);

        tempJournalDate = view.findViewById(R.id.new_date);
        dateButton = view.findViewById(R.id.date_button);

        // On click listener to display selected date in text
        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                journalDatePicker = new DatePickerDialog(getActivity(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                tempJournalDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                journalDatePicker.show();
            }
        });

        mTitleEditText = view.findViewById(R.id.new_title);
        mContentEditText = view.findViewById(R.id.new_content);

        // Journal save button onclick listener
        Button saveButton = view.findViewById(R.id.journal_save_button);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String titleText = mTitleEditText.getText().toString();
                String contentText = mContentEditText.getText().toString();
                // TODO: get image url to insert
                Journal journal = new Journal(null, titleText, contentText);
            }
        });


        return view;
    }


}
