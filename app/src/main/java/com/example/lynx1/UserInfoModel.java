package com.example.lynx1;

public class UserInfoModel {
    String day;
    String downloadUri;
    String FirstName;
    String LastName;
    String month;
    int phone;
    String sex;
    String uid;
    String year;

    public UserInfoModel(){

    }

    public UserInfoModel(String day,String downloadUri, String firstName, String lastName, String month, int phone, String sex, String uid, String year) {
        FirstName = firstName;
        LastName = lastName;
        this.day = day;
        this.month = month;
        this.year = year;
        this.sex = sex;
        this.phone = phone;
        this.uid = uid;
        this.downloadUri = downloadUri;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getDownloadUri() {
        return downloadUri;
    }

    public void setDownloadUri(String downloadUri) {
        this.downloadUri = downloadUri;
    }
}
