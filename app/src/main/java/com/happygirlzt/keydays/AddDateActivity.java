package com.happygirlzt.keydays;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class AddDateActivity extends FragmentActivity {

    static EditText dateEdit;
    Button addButton;
    EditText titleEdit;
    Toolbar toolbar;

    static String titleStr;
    static String dateStr;

    final static FirebaseDatabase database = FirebaseDatabase.getInstance();

    static FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_date);
        addButton = findViewById(R.id.add_date);
        dateEdit = findViewById(R.id.et_date);
        titleEdit = findViewById(R.id.et_title);
        toolbar = findViewById(R.id.adddate_toolBar);

        toolbar.setTitle("Add New Date");

        dateEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(v);
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                titleStr = titleEdit.getText().toString();
                dateStr = dateEdit.getText().toString();

                if (TextUtils.isEmpty(titleStr)) {
                    Toast.makeText(AddDateActivity.this, "Please enter the name", Toast.LENGTH_SHORT).show();
                }

                if (TextUtils.isEmpty(dateStr)) {
                    Toast.makeText(AddDateActivity.this, "Please pick a date", Toast.LENGTH_SHORT).show();
                }

                Toast.makeText(AddDateActivity.this, dateStr, Toast.LENGTH_SHORT).show();

                String userId = user.getUid();

                String key = database.getReference("dates/" + userId).push().getKey();

                DateItem dateItem = new DateItem();
                // dateItem.setuId(user.getUid());
                dateItem.setTitle(titleStr);
                dateItem.setmDate(dateStr);

                Map<String, Object> childUpdates = new HashMap<>();
                assert key != null;
                childUpdates.put(key, dateItem.toFirebaseObj());

                // Update database
                database.getReference("dates/" + userId).updateChildren(childUpdates, new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
                        if (databaseError == null) {
                            finish();
                        }
                    }
                });

                startActivity(new Intent(AddDateActivity.this, ProfileActivity.class));
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
            if (month < 9 && day >= 10) {
                dateEdit.setText(String.format("%d %d%d %d", year, 0, month + 1, day));
            } else if (month < 9 && day < 10) {
                dateEdit.setText(String.format("%d %d%d %d%d", year, 0, month + 1, 0, day));
            } else if (month >= 9 && day < 10) {
                dateEdit.setText(String.format("%d %d %d%d", year, month + 1, 0, day));
            } else {
                dateEdit.setText(String.format("%d %d %d", year, month + 1, day));
            }
        }
    }

    private void writeNewDate(String userId, String title, String mDate) {

    }
}
