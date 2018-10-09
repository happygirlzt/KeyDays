package happygirlzt.com.keydays.activities;

import android.app.DatePickerDialog;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import java.util.Calendar;

import happygirlzt.com.keydays.R;
import happygirlzt.com.keydays.fragments.DatePickerFragment;

public class AddDateActivity extends AppCompatActivity implements DatePickerDialog.onDateSetListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_date);

        Button button = findViewById(R.id.choose_date);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "date picker");
            }
        });
    }

    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        // 使用date
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, year);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);

    }
}
