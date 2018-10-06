package happygirlzt.com.keydays.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import happygirlzt.com.keydays.R;
import happygirlzt.com.keydays.adapters.DateRecyclerViewAdapter;
import happygirlzt.com.keydays.models.KeyDate;

import static happygirlzt.com.keydays.R2.id.rvDates;

/**
 * A simple {@link Fragment} subclass.
 */
public class PastDaysFragment extends Fragment {

    private List<KeyDate> dates;

    public PastDaysFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // 加载布局文件
        View view = inflater.inflate(R.layout.fragment_past_days, container, false);

        ButterKnife.bind(this, view);

        initializeData();

        LinearLayoutManager llm = new LinearLayoutManager(this.getContext());

        DateRecyclerViewAdapter adapter = new DateRecyclerViewAdapter(dates);

        return view;
    }

    private void initializeData() {
        dates = new ArrayList<>();
        dates.add(new KeyDate("002", "Happy Day", 88, "Nothing serious"));
    }

}
