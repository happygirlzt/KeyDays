package com.happygirlzt.keydays;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toolbar;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class DetailActivity extends AppCompatActivity {

    TextView tv_date;
    TextView tv_title;
    TextView tv_suf;
    TextView tv_days;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tv_date = findViewById(R.id.detail_date);
        tv_title = findViewById(R.id.detail_title);
        tv_suf = findViewById(R.id.detail_suffix);
        tv_days = findViewById(R.id.detail_days);
        toolbar = findViewById(R.id.datedetail_toolBar);

        Intent intent = getIntent();

        String tv_date_s = intent.getStringExtra("detail_date");
        String tv_title_s = intent.getStringExtra("detail_title");

        tv_date.setText(tv_date_s);
        tv_title.setText(tv_title_s);
        toolbar.setTitle("Detail");

        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy MM dd");

        // SimpleDateFormat mFormat = new SimpleDateFormat("yyyy MM dd");
        LocalDateTime today = LocalDateTime.now();
        String d2 = formatter.format(today);

        final LocalDate firstDate = LocalDate.parse(tv_date_s, formatter);
        final LocalDate secondDate = LocalDate.parse(d2, formatter);
        final long days = ChronoUnit.DAYS.between(firstDate, secondDate);

        if (days >= 0) {
            String str = "has past ";
            tv_suf.setText(Long.toString(days));
            tv_days.setText(str);
            tv_suf.setTextColor(Color.rgb(220,20,60));
        } else {
            String str = "will arrive in ";
            tv_suf.setText(Long.toString(Math.abs(days)));
            tv_days.setText(str);
            tv_suf.setTextColor(Color.rgb(46,139,87));
        }
    }
}
