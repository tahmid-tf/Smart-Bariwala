package com.example.smartbariwala2;

public class OwnerConstractor {

    public String ownersEmail;
    public String ownersPassword;
    public String ownersPhoneNumber;
    public String rentersPassword;

    public String getOwnersEmail() {
        return ownersEmail;
    }

    public String getOwnersPassword() {
        return ownersPassword;
    }

    public String getOwnersPhoneNumber() {
        return ownersPhoneNumber;
    }

    public String getRentersPassword() {
        return rentersPassword;
    }

    public void setOwnersEmail(String ownersEmail) {
        this.ownersEmail = ownersEmail;
    }

    public void setOwnersPassword(String ownersPassword) {
        this.ownersPassword = ownersPassword;
    }

    public void setOwnersPhoneNumber(String ownersPhoneNumber) {
        this.ownersPhoneNumber = ownersPhoneNumber;
    }

    public void setRentersPassword(String rentersPassword) {
        this.rentersPassword = rentersPassword;
    }

    public OwnerConstractor(String ownersEmail, String ownersPassword, String ownersPhoneNumber, String rentersPassword) {
        this.ownersEmail = ownersEmail;
        this.ownersPassword = ownersPassword;
        this.ownersPhoneNumber = ownersPhoneNumber;
        this.rentersPassword = rentersPassword;
    }
}
