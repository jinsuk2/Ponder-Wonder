package com.example.ponderwonder.schedule;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.ponderwonder.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Calendar;

public class ScheduleDetails extends Fragment {

    EditText scheduleTitle;
    DatePickerDialog startDatePicker;
    DatePickerDialog endDatePicker;
    EditText tempStartDate;
    EditText tempEndDate;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_schedule_details, container, false);

        scheduleTitle = view.findViewById(R.id.schedule_title);
        tempStartDate = view.findViewById(R.id.schedule_start_date);
        tempEndDate = view.findViewById(R.id.schedule_end_date);

        tempStartDate.setShowSoftInputOnFocus(false);
        tempStartDate.setFocusableInTouchMode(false);
        tempStartDate.setFocusable(false);

        tempEndDate.setShowSoftInputOnFocus(false);
        tempEndDate.setFocusableInTouchMode(false);
        tempEndDate.setFocusable(false);

        tempStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);

                tempStartDate.setError(null);

                // date picker dialog
                startDatePicker = new DatePickerDialog(getActivity(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                tempStartDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                startDatePicker.show();
            }
        });

        tempEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);

                tempEndDate.setError(null);

                // date picker dialog
                endDatePicker = new DatePickerDialog(getActivity(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                tempEndDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                endDatePicker.show();
            }
        });


        final FloatingActionButton createButton = view.findViewById(R.id.schedule_save_btn);
        //final NavController navController = Navigation.findNavController(super.getActivity(),R.id.mainNavigationFragment);

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Validate input
                if (isValidInput()) {

                    // TODO: Should validate start and end date period
                    //TODO: add schedule to db
                    //

                    // TODO: onSuccess navigate to Today screen of first day of schedule
                    //navController.navigate(R.id.create_schedule);


                }


            }
        });



        return view;
    }


    private boolean isValidInput() {

        String title = scheduleTitle.getText().toString();
        String startDate = tempStartDate.getText().toString();
        String endDate = tempEndDate.getText().toString();
        boolean isValid = true;

        if(TextUtils.isEmpty(title)) {
            scheduleTitle.setError(getText(R.string.empty_input_msg));
            isValid = false;
        }

        if(TextUtils.isEmpty(startDate)) {
            tempStartDate.setError(getText(R.string.empty_input_msg));
            isValid = false;
        }

        if(TextUtils.isEmpty(endDate)) {
            tempEndDate.setError(getText(R.string.empty_input_msg));
            isValid = false;
        }
        return isValid;
    }
}
