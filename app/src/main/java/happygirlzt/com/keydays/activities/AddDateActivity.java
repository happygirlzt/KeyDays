package happygirlzt.com.keydays.activities;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.Button;
import android.util.Log;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import happygirlzt.com.keydays.R;
import happygirlzt.com.keydays.models.KeyDate;

public class AddDateActivity extends AppCompatActivity {

    // Tag
    private static final String TAG = "LoginActivity";

    private Button mButton;  // pick date
    private AppCompatButton mCompatButton;  // add

    private KeyDate k;
    private EditText et_title;
    private TextView detail_days, detail_prompt;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        // This method is used to initialize
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate(Bundle) called");
        setContentView(R.layout.activity_add_date);

        // initialize
        mButton = findViewById(R.id.choose_date);
        mCompatButton = findViewById(R.id.add_date);

        et_title = findViewById(R.id.et_title);
        detail_days = findViewById(R.id.detail_days);
        detail_prompt = findViewById(R.id.detail_prompt);

        Date now = new Date();


        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //实例化对象
                DatePickerFragment datePickerFragment = new DatePickerFragment();
                //调用show方法弹出对话框
                // 第一个参数为FragmentManager对象
                // 第二个为调用该方法的fragment的标签
                datePickerFragment.show(getFragmentManager(), "date picker");
            }
        });


        mCompatButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }

            });
    }


    public static class DatePickerFragment extends DialogFragment {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {

            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            return new DatePickerDialog(getActivity(), dateSetListener, year, month, day);
        }

        private DatePickerDialog.OnDateSetListener dateSetListener =
                new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        // Do something with the chosen date
                        TextView mTextView = getActivity().findViewById(R.id.picked_date);

                        // Create a Date variable/object with user chosen date
                        Calendar cal = Calendar.getInstance();
                        cal.setTimeInMillis(0);
                        cal.set(year, month, day, 0, 0, 0);
                        Date chosenDate = cal.getTime();

                        // Format the date using style and locale
                        DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.US);
                        String formattedDate = df.format(chosenDate);

                        // Display the chosen date to app interface
                        mTextView.setText(formattedDate);
                    }
                };


    }
}
