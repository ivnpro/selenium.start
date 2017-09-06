package com.support.helper;


public class Girl {
    private String name, login, pass, oper_name;
    private int idGirl;

    public Girl() {oper_name=null;}

    public Girl(String _name, String _id, String _login, String _pass, String _oper_name){
        name = _name;
        idGirl = Integer.parseInt(_id);
        login = _login;
        pass = _pass;
        oper_name = _oper_name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getID() {
        return Integer.toString(idGirl);
    }

    public float getIDinInt() {
        return idGirl;
    }

    public void setID(String _id) {
        this.idGirl = Integer.parseInt(_id);
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getOperName() {
        return oper_name;
    }

    public void setOperName(String oper_name) {
        this.oper_name = oper_name;
    }

    public void changeGirlInfo (Girl writingGirl) {
        if (!this.getLogin().equals(writingGirl.getLogin())) {
            this.setLogin(writingGirl.getLogin());
        }
        if (!this.getPass().equals(writingGirl.getPass())) {
            this.setPass(writingGirl.getPass());
        }
        if (this.getName().equals(writingGirl.getName())) {
            this.setName(writingGirl.getName());
        }
        this.changeOperName(writingGirl);
    }

    public void changeOperName(Girl writingGirl){
        if (this.oper_name!=null) {
            if (writingGirl.getOperName()!=null) {
                this.oper_name = writingGirl.getOperName();
            } else {
                this.oper_name=null;
            }
        } else {
            if (writingGirl.getOperName()!=null) {
                this.oper_name = writingGirl.getOperName();
            } else {
                this.oper_name=null;
            }
        }
    }
}
