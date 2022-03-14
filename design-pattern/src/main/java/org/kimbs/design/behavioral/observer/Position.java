package org.kimbs.design.behavioral.observer;

public class Position {

    // 경도
    private double latitude;

    // 위도
    private double longitude;

    public Position(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
