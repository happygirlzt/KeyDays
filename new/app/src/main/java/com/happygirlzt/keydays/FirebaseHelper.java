package com.happygirlzt.keydays;

import android.content.Context;
import android.widget.ListView;

import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

/**
 * This class provides add, fetch function
 */
public class FirebaseHelper {

    DatabaseReference db;
    Boolean saved;
    ArrayList<DateItem> dates = new ArrayList<>();
    ListView mListView;
    Context c;

}
