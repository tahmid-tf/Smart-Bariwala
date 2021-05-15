package com.example.smartbariwala2;

public class RenterComplainAddConstractor {

    String unit;
    String date;
    String complain;

    public RenterComplainAddConstractor() {
    }

    public String getUnit() {
        return unit;
    }

    public String getDate() {
        return date;
    }

    public String getComplain() {
        return complain;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setComplain(String complain) {
        this.complain = complain;
    }

    public RenterComplainAddConstractor(String unit, String date, String complain) {
        this.unit = unit;
        this.date = date;
        this.complain = complain;
    }
}
