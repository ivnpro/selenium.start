package com.support.helper;


public class Agency {
    private String title, email, pass;

    public Agency() {}

    public Agency(String _title, String _email, String _pass){
        title = _title;
        email = _email;
        pass = _pass;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

}
