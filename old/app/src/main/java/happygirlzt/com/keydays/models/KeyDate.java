package happygirlzt.com.keydays.models;


import java.util.Date;

/**
 * Created pn 3 Oct 2018 by happygirlzt
 */

public class KeyDate {
    private int mId;
    private String mTitle;
    private Long mDate;

    // constructors
    public KeyDate() {
    }

    public KeyDate(int mId, String mTitle, Long mDate) {
        this.mId = mId;
        this.mTitle = mTitle;
        this.mDate = mDate;
    }

    public KeyDate(String mTitle, Long mDate) {
        this.mDate = mDate;
        this.mTitle = mTitle;
    }

    // getters and setters
    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public Long getmDate() {
        return mDate;
    }

    public void setmDate(Long mDate) {
        this.mDate = mDate;
    }
}
