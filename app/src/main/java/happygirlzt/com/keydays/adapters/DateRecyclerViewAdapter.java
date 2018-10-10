package happygirlzt.com.keydays.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import android.widget.TextView;
import android.content.Context;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import happygirlzt.com.keydays.R;
import happygirlzt.com.keydays.models.KeyDate;

/**
 * Created on 3 Oct 2018 by happygirlzt
 */
public class DateRecyclerViewAdapter extends RecyclerView.Adapter<DateRecyclerViewAdapter.ViewHolder> {

    private List<KeyDate> keyDates;

    public DateRecyclerViewAdapter(List<KeyDate> keyDates) {
        this.keyDates = keyDates;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.item_date, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // Get data by position
        KeyDate dates = keyDates.get(position);

        // Set item views based on your views and data model

    }

    @Override
    public int getItemCount() {
        // return the size of the dataset
        return keyDates.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvTitle)
        TextView tvTitle;

        @BindView(R.id.tvCountdown)
        TextView tvCountdown;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
