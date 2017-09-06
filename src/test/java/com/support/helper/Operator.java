package com.support.helper;

import java.util.ArrayList;

public class Operator {
    private String name, login, pass;
    private float rate;
    private ArrayList<String> idGirls;

    public Operator() {}

    public Operator(String _name, String _rate, String _login, String _pass, ArrayList<String> _idGirls){
        name = _name;
        rate = Float.parseFloat(_rate);
        login = _login;
        pass = _pass;
        idGirls = _idGirls;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRate() {
        return Float.toString(rate);
    }

    public float getRateFloat() {
        return rate;
    }

    public void setRate(String _rate) {
        this.rate = Float.parseFloat(_rate);
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

    public ArrayList<String> getListGirls() {
        return idGirls;
    }

    public void setListGirls(ArrayList<String> idGirls) {
        this.idGirls = idGirls;
    }

    public void changeOperatorInfo (Operator writingOperator) {
        if (!this.getName().equals(writingOperator.getName())) {
            this.setName(writingOperator.getName());
        }
        if (!this.getPass().equals(writingOperator.getPass())) {
            this.setPass(writingOperator.getPass());
        }
        if (this.getRate().equals(writingOperator.getRate())) {
            this.setRate(writingOperator.getRate());
        }
        if (this.getListGirls().equals(writingOperator.getListGirls())) {
            this.setListGirls(writingOperator.getListGirls());
        }
    }

}
