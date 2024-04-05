package com.ktg.cni.Model;

public class BillItem {
    private String name;
    private double cost;

    public BillItem(String name, double cost) {
        this.name = name;
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public double getCost() {
        return cost;
    }
}
