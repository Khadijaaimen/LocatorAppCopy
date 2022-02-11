package com.example.LatLongRealtime.ModelClass;

public class UserModelClass {

    String phoneNo, name, email, password, latitude, longitude;

    public UserModelClass(String phoneNo, String name, String email, String password, String latitude, String longitude) {
        this.phoneNo = phoneNo;
        this.name = name;
        this.email = email;
        this.password = password;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public UserModelClass() {
    }

    public UserModelClass(String phoneNo, String name, String email, String password) {
        this.phoneNo = phoneNo;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}
