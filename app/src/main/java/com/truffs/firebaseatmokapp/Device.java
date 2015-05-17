package com.truffs.firebaseatmokapp;

/**
 * @author greg
 * @since 6/21/13
 */
public class Device {

    private String address;
    private String name;
    private String timestampIn;
    private String timestampOut;

    // Required default constructor for Firebase object mapping
    @SuppressWarnings("unused")
    private Device() {
    }

    Device(String address, String name, String timestampIn, String timestampOut) {
        this.address = address;
        this.name = name;
        this.timestampIn = timestampIn;
        this.timestampOut = timestampOut;
    }

    Device(String address, String name, String timestampIn) {
        this.address = address;
        this.name = name;
        this.timestampIn = timestampIn;
    }

    public String getAddress() {
        return address;
    }

    public String getName() {
        return name;
    }

    public String getTimestampIn() { return timestampIn; }

    public String getTimestampOut() {
        return timestampOut;
    }
}
