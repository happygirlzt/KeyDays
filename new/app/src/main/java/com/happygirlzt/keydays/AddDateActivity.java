package com.happygirlzt.keydays;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.auth.TokenData;

import java.util.Calendar;

public class AddDateActivity extends FragmentActivity {

    static EditText dateEdit;
    static Button addButton;
    static EditText titleEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_date);
        addButton = findViewById(R.id.add_date);
        dateEdit = findViewById(R.id.et_date);
        titleEdit = findViewById(R.id.et_title);

        dateEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(v);
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String titleStr = titleEdit.getText().toString();
                String dateStr = dateEdit.getText().toString();

                if (TextUtils.isEmpty(titleStr)) {
                    Toast.makeText(AddDateActivity.this, "Please enter the name", Toast.LENGTH_SHORT).show();
                }

                if (TextUtils.isEmpty(dateStr)) {
                    Toast.makeText(AddDateActivity.this, "Please pick a date", Toast.LENGTH_SHORT).show();
                }

                // store the date
                Toast.makeText(AddDateActivity.this, dateStr, Toast.LENGTH_SHORT).show();
                startActivity(new Intent(AddDateActivity.this, RegisterActivity.class));
            }
        });
    }


    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public static class DatePickerFragment extends DialogFragment implements
            DatePickerDialog.OnDateSetListener {

        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        @SuppressLint("DefaultLocale")
        public void onDateSet(DatePicker view, int year, int month, int day) {
            dateEdit.setText(String.format("%d - %d - %d", year, month + 1, day));
        }
    }
}
