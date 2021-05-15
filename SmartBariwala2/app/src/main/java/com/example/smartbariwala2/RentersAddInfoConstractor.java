package com.example.smartbariwala2;

public class RentersAddInfoConstractor {

    private String date;
    private String unit;
    private String name;
    private String mobile;
    private String family;
    private String description;

    public RentersAddInfoConstractor() {
    }

    public String getDate() {
        return date;
    }

    public String getUnit() {
        return unit;
    }

    public String getName() {
        return name;
    }

    public String getMobile() {
        return mobile;
    }

    public String getFamily() {
        return family;
    }

    public String getDescription() {
        return description;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public RentersAddInfoConstractor(String date, String unit, String name, String mobile, String family, String description) {
        this.date = date;
        this.unit = unit;
        this.name = name;
        this.mobile = mobile;
        this.family = family;
        this.description = description;
    }
}
