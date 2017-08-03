package com.example.user.cwk2;


/**
 * Created by USER on 7/30/2017.
 */
public class Employee {

    private String id;
    private String fName;
    private String lName;
    private String mName;
    private String dob;

    private String address;
    private String tel;
    private String email;

    public Employee(){

    }

    public Employee(String id, String fName, String lName, String mName, String dob, String address, String tel, String email) {
        this.id = id;
        this.fName = fName;
        this.lName = lName;
        this.mName = mName;
        this.dob = dob;

        this.address = address;
        this.tel = tel;
        this.email = email;
    }

    public String getId() {
        return id;

    }

    public void setId(String id) {
        this.id = id;
    }

    public String getfName() {
        return fName;

    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
