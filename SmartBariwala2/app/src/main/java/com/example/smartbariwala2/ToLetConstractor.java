package com.example.smartbariwala2;

public class ToLetConstractor {

    private String date;
    private String ownersName;
    private String ownersHomeInfo;
    private String ownersLocation;
    private String ownersAddress;

    public ToLetConstractor() {

    }

    public String getDate() {
        return date;
    }

    public String getOwnersName() {
        return ownersName;
    }

    public String getOwnersHomeInfo() {
        return ownersHomeInfo;
    }

    public String getOwnersLocation() {
        return ownersLocation;
    }

    public String getOwnersAddress() {
        return ownersAddress;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setOwnersName(String ownersName) {
        this.ownersName = ownersName;
    }

    public void setOwnersHomeInfo(String ownersHomeInfo) {
        this.ownersHomeInfo = ownersHomeInfo;
    }

    public void setOwnersLocation(String ownersLocation) {
        this.ownersLocation = ownersLocation;
    }

    public void setOwnersAddress(String ownersAddress) {
        this.ownersAddress = ownersAddress;
    }

    public ToLetConstractor(String date, String ownersName, String ownersHomeInfo, String ownersLocation, String ownersAddress) {
        this.date = date;
        this.ownersName = ownersName;
        this.ownersHomeInfo = ownersHomeInfo;
        this.ownersLocation = ownersLocation;
        this.ownersAddress = ownersAddress;
    }
}
