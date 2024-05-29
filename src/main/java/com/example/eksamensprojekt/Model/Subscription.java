package com.example.eksamensprojekt.Model;

import java.time.LocalDate;

public class Subscription {

    private Long subscriptionId;
    private MyUser user;
    private LocalDate startDate;
    private LocalDate endDate;
    private int status;
    private double subscriptionPrice;

    public Subscription() {
    }

    public Subscription(Long subscriptionId, MyUser user, LocalDate startDate, LocalDate endDate, int status, double subscriptionPrice) {
        this.subscriptionId = subscriptionId;
        this.user = user;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.subscriptionPrice = subscriptionPrice;
    }

    public Long getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(Long subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public MyUser getUser() {
        return user;
    }

    public void setUser(MyUser user) {
        this.user = user;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public double getSubscriptionPrice() {
        return subscriptionPrice;
    }

    public void setSubscriptionPrice(double subscriptionPrice) {
        this.subscriptionPrice = subscriptionPrice;
    }
}
