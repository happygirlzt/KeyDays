package happygirlzt.com.keydays.models;

import java.util.ArrayList;

/**
 * Created pn 3 Oct 2018 by happygirlzt
 */
public class KeyDate {
    private String id;
    private String title;
    private int countDown;  // day left
    private String details;  // details of the date

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCountDown() {
        return countDown;
    }



    public KeyDate(String id, String title, int countDown, String details) {
        this.id = id;
        this.title = title;
        this.countDown = countDown;
        this.details = details;
    }

    public static ArrayList<KeyDate> createDatesList(int n) {
        ArrayList<KeyDate> keydates = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            keydates.add(new KeyDate("Birthday ", "ok", 8, "Anyway"));
        }

        return keydates;
    }
}
