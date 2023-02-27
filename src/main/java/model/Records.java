package model;

import java.time.LocalDate;
import java.util.Date;

public class Records {
    private String  weight;
    private String  temperature;
    private String  low_bp;
    private String  high_bp;
    private String notes;
    private String  date;

    public String  getDate() {
        return date;
    }

    public void setDate(String  date) {
        this.date = date;
    }

    public Records(String  weight, String  temperature, String  low_bp, String  high_bp, String notes, String  date) {
        this.weight = weight;
        this.temperature = temperature;
        this.low_bp = low_bp;
        this.high_bp = high_bp;
        this.notes = notes;
        this.date = date;
    }

    public String  getWeight() {
        return weight;
    }

    public void setWeight(String  weight) {
        this.weight = weight;
    }

    public String  getTemperature() {
        return temperature;
    }

    public void setTemperature(String  temperature) {
        this.temperature = temperature;
    }

    public String  getLow_bp() {
        return low_bp;
    }

    public void setLow_bp(String  low_bp) {
        this.low_bp = low_bp;
    }

    public String  getHigh_bp() {
        return high_bp;
    }

    public void setHigh_bp(String  high_bp) {
        this.high_bp = high_bp;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }


}
