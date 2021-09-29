package com.example.solarguide;

public class state_data {

    String name;
    int rate;


    public state_data()
    {

    }

    public state_data(String name, int rate) {
        this.name = name;
        this.rate = rate;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
