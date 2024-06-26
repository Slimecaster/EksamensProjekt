package com.example.eksamensprojekt.Model;


import org.springframework.beans.factory.annotation.Autowired;

public class MyUser {
    private Long userId;
    private String fname,sname,password,email,phoneNumber,role;
    private double weight;
    private int height,age,gender,activityLevel,goal;
    private Subscription subscription;





    public MyUser() {

    }

    public MyUser(Long userId, String fname, String sname, String password, String email, String phoneNumber, String role, double weight, int height, int age, int gender, int activityLevel, int goal, Subscription subscription) {
        this.userId = userId;
        this.fname = fname;
        this.sname = sname;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.role = role;
        this.weight = weight;
        this.height = height;
        this.age = age;
        this.gender = gender;
        this.activityLevel = activityLevel;
        this.goal = goal;
        this.subscription = subscription;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getActivityLevel() {
        return activityLevel;
    }

    public void setActivityLevel(int activityLevel) {
        this.activityLevel = activityLevel;
    }

    public int getGoal() {
        return goal;
    }

    public void setGoal(int goal) {
        this.goal = goal;
    }

    public Subscription getSubscription() {
        return subscription;
    }

    public void setSubscription(Subscription subscription) {
        this.subscription = subscription;
    }

    @Override
    public String toString() {
        return "MyUser{" +
                "userId=" + userId +
                ", fname='" + fname + '\'' +
                ", sname='" + sname + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", role='" + role + '\'' +
                ", weight=" + weight +
                ", height=" + height +
                ", age=" + age +
                ", gender=" + gender +
                ", activityLevel=" + activityLevel +
                ", goal=" + goal +
                ", subscription=" + subscription +
                '}';
    }
}
