package com.happygirlzt.keydays;

import java.util.HashMap;
import java.util.Map;

/**
 * Created on 9 Nov 2018 by happygirlzt
 */
public class DateItem {

    public String uId;
    public String title;
    public String mDate;

    public void setuId(String uId) {
        this.uId = uId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setmDate(String mDate) {
        this.mDate = mDate;
    }


    public DateItem() {}

    public DateItem(String uId, String title, String mDate) {
        this.uId = uId;
        this.title = title;
        this.mDate = mDate;
    }

    public Map<String, Object> toFirebaseObj() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("uid", uId);
        result.put("title", title);
        result.put("mDate", mDate);

        return result;
    }
}
