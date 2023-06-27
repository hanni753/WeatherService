package com.example.weatherservice;

import java.util.ArrayList;
import java.util.List;

public class WeatherOfWeek {
    private int kalenderWoche;
    private String stadt;
    private List<Day> tage;

    public WeatherOfWeek() {
        tage = new ArrayList<>();
    }


    public int getKalenderWoche() {
        return kalenderWoche;
    }

    public void setKalenderWoche(int kalenderWoche) {
        this.kalenderWoche = kalenderWoche;
    }

    public String getStadt() {
        return stadt;
    }

    public void setStadt(String stadt) {
        this.stadt = stadt;
    }

    public List<Day> getTage() {
        return tage;
    }

    public void setTage(List<Day> tage) {
        this.tage = tage;
    }

    public void addDay(Day tag){
        tage.add(tag);
    }
}
