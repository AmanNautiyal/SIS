package com.unisoft;

public class Student {
    private int rollNumber;
    private String name;
    private String gender;
    private String email;
    private String mobileNumber;
    private String course;

    public Student() {
    }

    public Student(int rollNumber, String name, String gender, String email, String mobileNumber, String course) {
        this.rollNumber = rollNumber;
        this.name = name;
        this.gender = gender;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.course = course;
    }

    public int getRollNumber() {
        return rollNumber;
    }

    public void setRollNumber(int rollNumber) {
        this.rollNumber = rollNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }
    
    
}
