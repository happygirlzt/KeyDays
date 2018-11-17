/**
 * Created by happygirlzt
 *
 * Nov 2018
 */
package com.happygirlzt.keydays;

import java.util.HashMap;
import java.util.Map;

/**
 * Created on 9 Nov 2018 by happygirlzt
 */
public class DateItem {

    public String dateId;
    public String title;
    public String mDate;

    public String getDateId() {
        return dateId;
    }

    public void setDateId(String dateId) {
        this.dateId = dateId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setmDate(String mDate) {
        this.mDate = mDate;
    }


    public String getTitle() {
        return title;
    }

    public String getmDate() {
        return mDate;
    }

    public DateItem() {}

    public DateItem(String title, String mDate, String dateId) {
        this.dateId = dateId;
        this.title = title;
        this.mDate = mDate;
    }

    public Map<String, Object> toFirebaseObj() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("dateId", dateId);
        result.put("title", title);
        result.put("mDate", mDate);

        return result;
    }
}
