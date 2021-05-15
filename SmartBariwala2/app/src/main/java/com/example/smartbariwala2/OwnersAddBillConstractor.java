package com.example.smartbariwala2;

public class OwnersAddBillConstractor {

    String month;
    String date;
    String houseBill;
    String gassBill;
    String electronicBill;
    String otherBill;

    public OwnersAddBillConstractor() {
    }

    public String getMonth() {
        return month;
    }

    public String getDate() {
        return date;
    }

    public String getHouseBill() {
        return houseBill;
    }

    public String getGassBill() {
        return gassBill;
    }

    public String getElectronicBill() {
        return electronicBill;
    }

    public String getOtherBill() {
        return otherBill;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setHouseBill(String houseBill) {
        this.houseBill = houseBill;
    }

    public void setGassBill(String gassBill) {
        this.gassBill = gassBill;
    }

    public void setElectronicBill(String electronicBill) {
        this.electronicBill = electronicBill;
    }

    public void setOtherBill(String otherBill) {
        this.otherBill = otherBill;
    }

    public OwnersAddBillConstractor(String month, String date, String houseBill, String gassBill, String electronicBill, String otherBill) {
        this.month = month;
        this.date = date;
        this.houseBill = houseBill;
        this.gassBill = gassBill;
        this.electronicBill = electronicBill;
        this.otherBill = otherBill;
    }
}
