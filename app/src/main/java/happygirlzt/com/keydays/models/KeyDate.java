package happygirlzt.com.keydays.models;

/**
 * Created pn 3 Oct 2018 by happygirlzt
 */

public class KeyDate {
    int id;
    String name;
    int user_id;
    int remaining_days;
    int past_days;

    public KeyDate(int id, String name, int user_id, int remaining_days, int past_days) {
        this.id = id;
        this.name = name;
        this.user_id = user_id;
        this.remaining_days = remaining_days;
        this.past_days = past_days;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getRemaining_days() {
        return remaining_days;
    }

    public void setRemaining_days(int remaining_days) {
        this.remaining_days = remaining_days;
    }

    public int getPast_days() {
        return past_days;
    }

    public void setPast_days(int past_days) {
        this.past_days = past_days;
    }

}
