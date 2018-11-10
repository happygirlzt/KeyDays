package com.happygirlzt.keydays;

import java.util.HashMap;
import java.util.Map;

public class User {
    private String username;
    private String userid;
    private String useremail;

    public User() {

    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }

    public User(String username, String userid, String useremail) {
        this.username = username;
        this.userid = userid;
        this.useremail = useremail;

    }

    public User(String username, String useremail) {
        this.username = username;
        this.useremail = useremail;
    }

    public Map<String, Object> toFirebaseObj() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("userid", userid);
        result.put("useremail", useremail);
        result.put("username", username);

        return result;
    }
}
