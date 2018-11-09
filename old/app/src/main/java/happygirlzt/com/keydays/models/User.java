package happygirlzt.com.keydays.models;

public class User {
    private int mId;
    private String mName;
    private String mPassword;
    private String mEmail;

    public User() {

    }

    public User(int mId, String mName, String mPassword, String mEmail) {
        this.mId = mId;
        this.mName = mName;
        this.mPassword = mPassword;
        this.mEmail = mEmail;
    }

    public User(String mName, String mPassword, String mEmail) {
        this.mName = mName;
        this.mPassword = mPassword;
        this.mEmail = mEmail;
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

    public String getmPassword() {
        return mPassword;
    }

    public void setmPassword(String mPassword) {
        this.mPassword = mPassword;
    }

    public String getmEmail() {
        return mEmail;
    }

    public void setmEmail(String mEmail) {
        this.mEmail = mEmail;
    }
}
