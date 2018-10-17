package happygirlzt.com.keydays.models;


import java.util.Date;

/**
 * Created pn 3 Oct 2018 by happygirlzt
 */

public class KeyDate {
    private int mId;
    private String mName;
    private Long mDate;
    private int mUserId;
    private int mRemainingDays;
    private int mPastDays;

    public KeyDate() {

    }

    public KeyDate(int mId, String mName, Long mDate, int mUserId, int mRemainingDays, int mPastDays) {
        this.mId = mId;
        this.mName = mName;
        this.mDate = mDate;
        this.mUserId = mUserId;
        this.mRemainingDays = mRemainingDays;
        this.mPastDays = mPastDays;
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public Long getmDate() {
        return mDate;
    }

    public void setmDate(Long mDate) {
        this.mDate = mDate;
    }

    public int getmUserId() {
        return mUserId;
    }

    public void setmUserId(int mUserId) {
        this.mUserId = mUserId;
    }

    public int getmRemainingDays() {
        return mRemainingDays;
    }

    public void setmRemainingDays(int mRemainingDays) {
        this.mRemainingDays = mRemainingDays;
    }

    public int getmPastDays() {
        return mPastDays;
    }

    public void setmPastDays(int mPastDays) {
        this.mPastDays = mPastDays;
    }
}
