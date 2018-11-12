package com.happygirlzt.keydays;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class DateAdaper extends BaseAdapter {
    Context c;
    ArrayList<DateItem> dates;

    public DateAdaper() {

    }

    public DateAdaper(Context c, ArrayList<DateItem> dates) {
        this.c = c;
        this.dates = dates;
    }

    @Override
    public int getCount() {
        return dates.size();
    }

    @Override
    public Object getItem(int pos) {
        return dates.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        return pos;
    }

    @Override
    public View getView(int pos, View convertView, ViewGroup parent) {
        // inflate the layout for each list row
        if (convertView == null) {
            convertView = LayoutInflater.from(c).
                    inflate(R.layout.item_date, parent, false);
        }

        // get current item to be displayed
        DateItem item = (DateItem) getItem(pos);

        // get the TextView for item name and item description
        TextView tvDateTitle = convertView.findViewById(R.id.dateTitle);
        TextView tvDateDate = convertView.findViewById(R.id.dateDate);
        TextView tvPastComing = convertView.findViewById(R.id.pastOrComing);
        TextView tvDateSuffix = convertView.findViewById(R.id.dateSuffix);

        //sets the text for item name and item description from the current item object
        tvDateTitle.setText(item.getTitle());
        tvDateDate.setText(item.getmDate());
        String suf = "days";
        tvDateSuffix.setText(suf);

        // Calculate the days between given date and today
        // first formatter
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy MM dd");

        // SimpleDateFormat mFormat = new SimpleDateFormat("yyyy MM dd");
        String d1 = item.getmDate();
        LocalDateTime today = LocalDateTime.now();
        String d2 = formatter.format(today);

        final LocalDate firstDate = LocalDate.parse(d1, formatter);
        final LocalDate secondDate = LocalDate.parse(d2, formatter);
        final long days = ChronoUnit.DAYS.between(firstDate, secondDate);

        if (days >= 0) {
            String str = "Already " + days;
            tvPastComing.setText(str);
        } else {
            String str = "Arrives in " + Math.abs(days);
            tvPastComing.setText(str);
        }

        // returns the view for the current row
        return convertView;
    }
}
