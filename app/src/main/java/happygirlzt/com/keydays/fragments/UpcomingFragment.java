package happygirlzt.com.keydays.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import happygirlzt.com.keydays.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class UpcomingFragment extends Fragment {


    public UpcomingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // 加载布局文件
        View view = inflater.inflate(R.layout.fragment_upcoming, container,false);
        return view;
    }

}
