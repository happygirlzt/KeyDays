package happygirlzt.com.keydays.models;

public class Record {

    private int UserId;
    private int KeydateId;

    public Record() {

    }

    public Record(int userId, int keydateId) {
        UserId = userId;
        KeydateId = keydateId;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }

    public int getKeydateId() {
        return KeydateId;
    }

    public void setKeydateId(int keydateId) {
        KeydateId = keydateId;
    }
}
