package com.ktg.cni.Model;

import java.io.Serializable;

public class Appliance implements Serializable {

    private long id;
    private String name;
    private double amps;
    private int volts;

    public Appliance(long id, String name, double amps, int volts) {
        this.id = id;
        this.name = name;
        this.amps = amps;
        this.volts = volts;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAmps() {
        return amps;
    }

    public void setAmps(double amps) {
        this.amps = amps;
    }

    public int getVolts() {
        return volts;
    }

    public void setVolts(int volts) {
        this.volts = volts;
    }
}
