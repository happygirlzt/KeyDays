package happygirlzt.com.keydays.models;

/**
 * Created pn 3 Oct 2018 by happygirlzt
 */
public class keyDate {
    String id;
    String title;
    int countDown;  // day left
    String details;  // details of the date

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

    public void setCountDown(int countDown) {
        this.countDown = countDown;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }


    public keyDate(String id, String title, int countDown, String details) {
        this.id = id;
        this.title = title;
        this.countDown = countDown;
        this.details = details;
    }

}
